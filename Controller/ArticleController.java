package Controller;

import Model.Article;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ArticleView;
import view.FournisseurView;
import DAO.ArticleDAO;
import DAO.FournisseurDAO;

public class ArticleController {
    private ArticleView view;
    private ArticleDAO articleDAO;
    private FournisseurController fournisseurController;

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

    public void setFournisseurController(FournisseurController fournisseurController) {
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

            double prix = Double.parseDouble(prixString.replace(',', '.'));
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
        // Récupérer l'ID de l'article sélectionné
        int idArticle = view.getSelectedArticleId();
        
        if (idArticle == -1) {
            view.afficherMessage("Veuillez sélectionner un article à supprimer !");
            return;
        }
        
        // Demander confirmation
        if (view.confirmerSuppression(view.getSelectedArticleName())) {
            articleDAO.supprimerArticle(idArticle);
            afficherArticles();
            view.afficherMessage("Article supprimé avec succès !");
        }
    }

    public void modifierArticleSelectionne() {
    Article articleSelectionne = view.getSelectedArticle();
    
    if (articleSelectionne == null) {
        view.afficherMessage("Veuillez sélectionner un article à modifier !");
        return;
    }
    
    try {
        // Si le champ est vide, on garde l'ancienne valeur
        String nouveauNom = view.getNom().trim().isEmpty() 
            ? articleSelectionne.getNom() 
            : view.getNom().trim();
            
        String nouveauType = view.gettype().trim().isEmpty() 
            ? articleSelectionne.getType()
            : view.gettype().trim();
            
        double nouveauPrix = view.getPrix().trim().isEmpty() 
            ? articleSelectionne.getPrix_unitaire() 
            : Double.parseDouble(view.getPrix().trim().replace(',', '.'));
            
        int nouvelleQuantite = view.getQuantite().trim().isEmpty() 
            ? articleSelectionne.getQuantite_stock() 
            : Integer.parseInt(view.getQuantite().trim());
            
        int nouveauIdFournisseur = view.getIdFournisseur().trim().isEmpty() 
            ? articleSelectionne.getId_fournisseur() 
            : Integer.parseInt(view.getIdFournisseur().trim());

        int idArticle = articleSelectionne.getId_article();
        
        Article articleModifie = new Article(idArticle, nouveauNom, nouveauType, nouveauPrix, nouvelleQuantite, nouveauIdFournisseur);
        
        articleDAO.modifierArticle(articleModifie);
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
       FournisseurView fournisseurView = new FournisseurView();
        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        FournisseurController fournisseurController = new FournisseurController(fournisseurView, fournisseurDAO);
    }
}