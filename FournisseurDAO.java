package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static Model.connectionDB.getConnection;

public class FournisseurDAO {
    
    // Récupérer tous les fournisseurs
    public List<Fournisseur> getAllFournisseurs() {
        List<Fournisseur> fournisseurs = new ArrayList<>();

        String sql = "SELECT * FROM fournisseur";
        try (Connection cnx = connectionDB.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                fournisseurs.add(new Fournisseur(
                        rs.getInt("id_fournisseur"),
                        rs.getString("nom"),
                        rs.getString("telephone"),
                        rs.getString("email"),
                        rs.getString("adresse")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des fournisseurs: " + e.getMessage());
            e.printStackTrace();
        }

        return fournisseurs;
    }

    // Ajouter un fournisseur
    public void ajouterFournisseur(Fournisseur fournisseur) {
        String nom = fournisseur.getNom();
        String telephone = fournisseur.getTelephone();
        String email = fournisseur.getEmail();
        String adresse = fournisseur.getAdresse();

        String sql = "INSERT INTO fournisseur (nom, telephone, email, adresse) VALUES (?, ?, ?, ?)";
        
        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, nom);
            pstmt.setString(2, telephone);
            pstmt.setString(3, email);
            pstmt.setString(4, adresse);
            pstmt.executeUpdate();
            System.out.println("Fournisseur ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du fournisseur: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Supprimer un fournisseur par ID
    public void supprimerFournisseur(int idFournisseur) {
        String sql = "DELETE FROM fournisseur WHERE id_fournisseur = ?";
        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, idFournisseur);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Fournisseur supprimé avec succès !");
            } else {
                System.out.println("Aucun fournisseur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du fournisseur: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Modifier un fournisseur
    public void modifierFournisseur(Fournisseur fournisseur) {
        String sql = "UPDATE fournisseur SET nom = ?, telephone = ?, email = ?, adresse = ? WHERE id_fournisseur = ?";
        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, fournisseur.getNom());
            pstmt.setString(2, fournisseur.getTelephone());
            pstmt.setString(3, fournisseur.getEmail());
            pstmt.setString(4, fournisseur.getAdresse());
            pstmt.setInt(5, fournisseur.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Fournisseur modifié avec succès !");
            } else {
                System.out.println("Aucun fournisseur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du fournisseur: " + e.getMessage());
            e.printStackTrace();
        }
    }

    

    
}