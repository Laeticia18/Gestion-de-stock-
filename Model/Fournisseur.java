package Model;

public class Fournisseur {
    private int id_fournisseur;
    private String nom;
    private String telephone;
    private String email;
    private String adresse;

    // Constructeur avec ID (pour récupération depuis BDD)
    public Fournisseur(int id_fournisseur, String nom, String telephone, String email, String adresse) {
        this.id_fournisseur = id_fournisseur;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    // Constructeur sans ID (pour ajout, l'ID sera auto-généré)
    public Fournisseur(String nom, String telephone, String email, String adresse) {
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    // Getters 
    public int getId() {
        return id_fournisseur;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getAdresse(){
        return adresse;
    }
    
    // Setters 
    public void setId(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}