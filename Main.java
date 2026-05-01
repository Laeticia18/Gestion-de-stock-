import View.ArticleView;
import View.FournisseurView;
import Controller.ArticleController;
import Controller.FournisseurController;
import Model.ArticleDAO;
import Model.FournisseurDAO;

public class Main {
    public static void main(String[] args) {
        // Démarrer avec la vue des fournisseurs
        // Vous pouvez choisir de démarrer avec ArticleView ou FournisseurView
        
        // Option 1: Démarrer avec la gestion des fournisseurs
        FournisseurView fournisseurView = new FournisseurView();
        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        FournisseurController fournisseurController = new FournisseurController(fournisseurView, fournisseurDAO);
        
        // Option 2: Démarrer avec la gestion des articles (commenté)
        /*
        ArticleView articleView = new ArticleView();
        ArticleDAO articleDAO = new ArticleDAO();
        ArticleController articleController = new ArticleController(articleView, articleDAO);
        */
    }
}