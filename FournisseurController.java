package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import View.FournisseurView;
import View.ArticleView;
import Model.Fournisseur;
import Model.FournisseurDAO;

public class FournisseurController {
    private FournisseurView view;
    private FournisseurDAO fournisseurDAO;
    private ArticleView articleView;
    private ArticleController articleController;

    public FournisseurController(FournisseurView view, FournisseurDAO fournisseurDAO) {
        this.view = view;
        this.fournisseurDAO = fournisseurDAO;
        
        // Listener pour ajouter un fournisseur
        view.addAjouterListener(e -> ajouterFournisseur());
        
        // Listener pour supprimer un fournisseur
        view.addSupprimerListener(e -> supprimerFournisseurSelectionne());
        
        // Listener pour modifier un fournisseur
        view.addModifierListener(e -> modifierFournisseurSelectionne());
        
        // Listener pour naviguer vers ArticleView
        view.addArticleButtonListener(e -> ouvrirArticleView());
        
        // Afficher les fournisseurs APRÈS avoir configuré tous les listeners
        afficherFournisseurs();
    }

    public void afficherFournisseurs() {
        try {
            List<Fournisseur> fournisseurs = fournisseurDAO.getAllFournisseurs();
            System.out.println("Nombre de fournisseurs récupérés: " + fournisseurs.size());
            view.afficherFournisseurs(fournisseurs);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage des fournisseurs: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void ajouterFournisseur() {
        try {
            String nom = view.getNom();
            String telephone = view.getTelephone();
            String email = view.getEmail();
            String adresse = view.getAdresse();

            // Validation basique
            if (nom.isEmpty() || telephone.isEmpty() || email.isEmpty() || adresse.isEmpty()) {
                view.afficherMessage("Veuillez remplir tous les champs !");
                return;
            }

            Fournisseur fournisseur = new Fournisseur(0, nom, telephone, email, adresse);
            fournisseurDAO.ajouterFournisseur(fournisseur);
            
            afficherFournisseurs();
            view.viderChamps();
            view.afficherMessage("Fournisseur ajouté avec succès !");
        } catch (Exception e) {
            view.afficherMessage("Erreur lors de l'ajout : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void supprimerFournisseurSelectionne() {
        int idFournisseur = view.getSelectedFournisseurId();
        String nomFournisseur = view.getSelectedFournisseurNom();
        
        if (idFournisseur == -1) {
            view.afficherMessage("Veuillez sélectionner un fournisseur à supprimer !");
            return;
        }
        
        if (view.confirmerSuppression(nomFournisseur)) {
            fournisseurDAO.supprimerFournisseur(idFournisseur);
            afficherFournisseurs();
            view.viderChamps();
            view.afficherMessage("Fournisseur supprimé avec succès !");
        }
    }

    public void modifierFournisseurSelectionne() {
        Fournisseur fournisseurSelectionne = view.getSelectedFournisseur();
        
        if (fournisseurSelectionne == null) {
            view.afficherMessage("Veuillez sélectionner un fournisseur à modifier !");
            return;
        }
        
        try {
            String nom = view.getNom();
            String telephone = view.getTelephone();
            String email = view.getEmail();
            String adresse = view.getAdresse();

            if (nom.isEmpty() || telephone.isEmpty() || email.isEmpty() || adresse.isEmpty()) {
                view.afficherMessage("Veuillez remplir tous les champs !");
                return;
            }

            Fournisseur fournisseurModifie = new Fournisseur(
                fournisseurSelectionne.getId(), 
                nom, 
                telephone, 
                email, 
                adresse
            );
            
            fournisseurDAO.modifierFournisseur(fournisseurModifie);
            afficherFournisseurs();
            view.viderChamps();
            view.afficherMessage("Fournisseur modifié avec succès !");
        } catch (Exception e) {
            view.afficherMessage("Erreur lors de la modification : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void ouvrirArticleView() {
        // Fermer la vue fournisseur
        view.dispose();
        
        // Créer et afficher la vue article
        articleView = new ArticleView();
        Model.ArticleDAO articleDAO = new Model.ArticleDAO();
        articleController = new ArticleController(articleView, articleDAO);
        
        // Ajouter un listener pour revenir à la vue fournisseur
        articleController.setFournisseurController(this);
    }

    public void ouvrirFournisseurView() {
        // Recréer la vue fournisseur
        FournisseurView newView = new FournisseurView();
        FournisseurController newController = new FournisseurController(newView, fournisseurDAO);
    }
}