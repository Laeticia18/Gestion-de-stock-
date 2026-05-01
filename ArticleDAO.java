package Model;
import static Model.connectionDB.getConnection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    
    // Récupérer tous les articles
    public List<Article> getAllArticles() {
        List<Article> Articles = new ArrayList<>();

        String sql = "SELECT * FROM article";
        try (Connection cnx = connectionDB.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Article article = new Article(
                        rs.getString("nom"),
                        rs.getString("type"),
                        rs.getDouble("prix_unitaire"),
                        rs.getInt("quantite_stock"),
                        rs.getInt("id_fournisseur")
                );
                // Définir l'id_article si vous en avez besoin plus tard
                article.setId_article(rs.getInt("id_article"));
                Articles.add(article);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des articles: " + e.getMessage());
            e.printStackTrace();
        }

        return Articles;
    }
    
    // Ajouter un article
    public void AjouterArticle(Article article){ 
        String nom = article.getNom();
        String type = article.getType();
        double prix_unitaire = article.getPrix_unitaire();
        int quantite_stock = article.getQuantite_stock();
        int id_fournisseur = article.getId_fournisseur();

        String sql = "INSERT INTO article (nom, type, prix_unitaire, quantite_stock, id_fournisseur) VALUES (?, ?, ?, ?, ?)";
        
        try(
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ){
            pstmt.setString(1, nom);
            pstmt.setString(2, type);
            pstmt.setDouble(3, prix_unitaire);
            pstmt.setInt(4, quantite_stock);
            pstmt.setInt(5, id_fournisseur);
            pstmt.executeUpdate();
            System.out.println("Article ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'article: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Supprimer un article par nom
    public void supprimerArticle(String nomArticle){
        String sql = "DELETE FROM article WHERE nom = ?";
        try(
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ){
            pstmt.setString(1, nomArticle);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Article supprimé avec succès !");
            } else {
                System.out.println("Aucun article trouvé avec ce nom.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'article: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Modifier un article
    public void modifierArticle(Article article){
        String sql = "UPDATE article SET type = ?, prix_unitaire = ?, quantite_stock = ?, id_fournisseur = ? WHERE nom = ?";
        try(
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ){
            pstmt.setString(1, article.getType());
            pstmt.setDouble(2, article.getPrix_unitaire());
            pstmt.setInt(3, article.getQuantite_stock());
            pstmt.setInt(4, article.getId_fournisseur());
            pstmt.setString(5, article.getNom());
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Article modifié avec succès !");
            } else {
                System.out.println("Aucun article trouvé avec ce nom.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de l'article: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
   
    
   
    
    
}