package Controller;
import Model.Article;
import Model.ArticleDAO;
import View.ArticleView;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArticleController {
    private ArticleView view;
    private ArticleDAO articleDAO;
    private Controller.FournisseurController fournisseurController;

    public ArticleController(ArticleView view, ArticleDAO articleDAO) {
        this.view = view;
        this.articleDAO = articleDAO;
        
        afficherArticles();
        
        // Listener pour ajouter un article
        view.addAjouterListener(e -> AjouterArticle());
        
        // Listener pour supprimer un article
        view.addSupprimerListener(e -> supprimerArticleSelectionne());
        
        // Listener pour modifier un article
        view.addModifierListener(e -> modifierArticleSelectionne());
        
        // Listener pour naviguer vers FournisseurView
        view.addFournisseurButtonListener(e -> ouvrirFournisseurView());
    }

    public void setFournisseurController(Controller.FournisseurController fournisseurController) {
        this.fournisseurController = fournisseurController;
    }
   
    
    public void afficherArticles() {
        try{
            List<Article> articles = articleDAO.getAllArticles();
            view.afficherArticles(articles);
        } catch (Exception e){
            e.printStackTrace();    
        }
    }
    
    public void AjouterArticle() {
        try {
            String nom = view.getNom();
            String type = view.gettype();
            String prixString = view.getPrix();
            String quantiString = view.getQuantite();
            String idFournisseurString = view.getIdFournisseur();

            double prix = Double.parseDouble(prixString);
            int quantite = Integer.parseInt(quantiString);
            int idFournisseur = Integer.parseInt(idFournisseurString);
            
            articleDAO.AjouterArticle(new Article(nom, type, prix, quantite, idFournisseur));
            afficherArticles();
            view.viderChamps();
            view.afficherMessage("Article ajouté avec succès !");
        } catch (NumberFormatException e) {
            view.afficherMessage("Erreur : Veuillez entrer des valeurs valides !");
            System.out.println("Erreur de format des données : " + e.getMessage());
        }
    }

    public void supprimerArticleSelectionne() {
        // Récupérer le nom de l'article sélectionné
        String nomArticle = view.getSelectedArticleName();
        
        if (nomArticle == null) {
            view.afficherMessage("Veuillez sélectionner un article à supprimer !");
            return;
        }
        
        // Demander confirmation
        if (view.confirmerSuppression(nomArticle)) {
            articleDAO.supprimerArticle(nomArticle);
            afficherArticles();
            view.afficherMessage("Article supprimé avec succès !");
        }
    }

    public void modifierArticleSelectionne() {
        // Récupérer l'article sélectionné (pour avoir le nom original)
        Article articleSelectionne = view.getSelectedArticle();
        
        if (articleSelectionne == null) {
            view.afficherMessage("Veuillez sélectionner un article à modifier !");
            return;
        }
        
        try {
            // Récupérer les nouvelles données depuis les champs de texte
            String nouveauNom = view.getNom();
            String nouveauType = view.gettype();
            double nouveauPrix = Double.parseDouble(view.getPrix());
            int nouvelleQuantite = Integer.parseInt(view.getQuantite());
            int nouveauIdFournisseur = Integer.parseInt(view.getIdFournisseur());
            
            // Créer l'article avec les nouvelles données
            Article articleModifie = new Article(nouveauNom, nouveauType, nouveauPrix, nouvelleQuantite, nouveauIdFournisseur);
            
            // Modifier l'article dans la base de données
            articleDAO.modifierArticle(articleModifie);
            
            // Rafraîchir l'affichage et vider les champs
            afficherArticles();
            view.viderChamps();
            view.afficherMessage("Article modifié avec succès !");
            
        } catch (NumberFormatException e) {
            view.afficherMessage("Erreur : Veuillez entrer des valeurs valides pour le prix, la quantité et l'ID fournisseur !");
        } catch (Exception e) {
            view.afficherMessage("Erreur lors de la modification : " + e.getMessage());
        }
    }

    private void ouvrirFournisseurView() {
        // Fermer la vue article
        view.dispose();
        
        // Créer et afficher la vue fournisseur
        View.FournisseurView fournisseurView = new View.FournisseurView();
        Model.FournisseurDAO fournisseurDAO = new Model.FournisseurDAO();
        Controller.FournisseurController fournisseurController = new Controller.FournisseurController(fournisseurView, fournisseurDAO);
    }
}