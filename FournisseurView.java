package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.event.*;
import Model.Fournisseur;
import java.util.List;
import java.awt.*;

public class FournisseurView extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton ajouterButton;
    private JButton supprimerButton;
    private JButton modifierButton;
    private JButton articleButton;
    private JTextField nom;
    private JTextField telephone;
    private JTextField email;
    private JTextField adresse;
    
    // Couleurs modernes
    private final Color PRIMARY_COLOR = new Color(142, 68, 173);
    private final Color SECONDARY_COLOR = new Color(155, 89, 182);
    private final Color SUCCESS_COLOR = new Color(46, 204, 113);
    private final Color DANGER_COLOR = new Color(231, 76, 60);
    private final Color WARNING_COLOR = new Color(241, 196, 15);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private final Color PANEL_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(44, 62, 80);

    public FournisseurView(){
        setTitle("Gestion des Fournisseurs - Systeme de Gestion");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_COLOR);
        setLayout(null);
        
        // Panel de titre
        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(0, 0, 1200, 80);
        titlePanel.setBackground(PRIMARY_COLOR);
        titlePanel.setLayout(null);
        
        JLabel titleLabel = new JLabel(" GESTION DES FOURNISSEURS");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(30, 20, 450, 40);
        titlePanel.add(titleLabel);
        
        // Bouton Articles moderne
        articleButton = createModernButton("Articles", SECONDARY_COLOR);
        articleButton.setBounds(1020, 20, 150, 40);
        titlePanel.add(articleButton);
        
        add(titlePanel);
        
        // Panel de formulaire
        JPanel formPanel = new JPanel();
        formPanel.setBounds(30, 100, 320, 550);
        formPanel.setBackground(PANEL_COLOR);
        formPanel.setLayout(null);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel formTitle = new JLabel("Informations Fournisseur");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        formTitle.setForeground(TEXT_COLOR);
        formTitle.setBounds(20, 10, 280, 30);
        formPanel.add(formTitle);
        
        // Champs de formulaire avec labels
        int yPos = 60;
        nom = createStyledTextField("Nom du fournisseur", formPanel, yPos);
        yPos += 80;
        telephone = createStyledTextField("Telephone", formPanel, yPos);
        yPos += 80;
        email = createStyledTextField("Email", formPanel, yPos);
        yPos += 80;
        adresse = createStyledTextField("Adresse", formPanel, yPos);
        
        // Boutons d'action
        ajouterButton = createModernButton("Ajouter", SUCCESS_COLOR);
        ajouterButton.setBounds(20, 450, 280, 40);
        formPanel.add(ajouterButton);
        
        modifierButton = createModernButton(" Modifier", WARNING_COLOR);
        modifierButton.setBounds(20, 495, 135, 35);
        formPanel.add(modifierButton);
        
        supprimerButton = createModernButton(" Supprimer", DANGER_COLOR);
        supprimerButton.setBounds(165, 495, 135, 35);
        formPanel.add(supprimerButton);
        
        add(formPanel);
        
        // Panel de tableau
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(370, 100, 800, 550);
        tablePanel.setBackground(PANEL_COLOR);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel tableTitle = new JLabel(" Liste des Fournisseurs");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setForeground(TEXT_COLOR);
        tableTitle.setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10));
        tablePanel.add(tableTitle, BorderLayout.NORTH);
        
        // Table stylisée
        tableModel = new DefaultTableModel(
            new String[] {"ID", "Nom", "Telephone", "Email", "Adresse"}, 
            0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        styleTable();
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        add(tablePanel);
        
        setVisible(true);
    }
    
    private JTextField createStyledTextField(String placeholder, JPanel panel, int yPos) {
        JLabel label = new JLabel(placeholder);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setForeground(TEXT_COLOR);
        label.setBounds(20, yPos - 20, 280, 20);
        panel.add(label);
        
        JTextField textField = new JTextField();
        textField.setBounds(20, yPos, 280, 35);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        // Effet focus
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
        });
        
        panel.add(textField);
        return textField;
    }
    
    private JButton createModernButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Effet hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private void styleTable() {
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(35);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(new Color(155, 89, 182, 50));
        table.setSelectionForeground(TEXT_COLOR);
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        
        // Header style
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(236, 240, 241));
        header.setForeground(TEXT_COLOR);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, PRIMARY_COLOR));
        
        // Cell renderer pour centrer l'ID
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        
        // Largeurs de colonnes
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(180);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        
        // Listener pour remplir les champs
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    nom.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    telephone.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    email.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    adresse.setText(tableModel.getValueAt(selectedRow, 4).toString());
                }
            }
        });
    }

    public void addAjouterListener(ActionListener listener) {
        ajouterButton.addActionListener(listener);
    }

    public void addSupprimerListener(ActionListener listener) {
        supprimerButton.addActionListener(listener);
    }

    public void addModifierListener(ActionListener listener) {
        modifierButton.addActionListener(listener);
    }

    public void addArticleButtonListener(ActionListener listener) {
        articleButton.addActionListener(listener);
    }

    public String getNom(){ return nom.getText(); }
    public String getTelephone(){ return telephone.getText(); }
    public String getEmail(){ return email.getText(); }
    public String getAdresse(){ return adresse.getText(); }

    public int getSelectedFournisseurId() {
        int selectedRow = table.getSelectedRow();
        return selectedRow != -1 ? Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString()) : -1;
    }

    public String getSelectedFournisseurNom() {
        int selectedRow = table.getSelectedRow();
        return selectedRow != -1 ? tableModel.getValueAt(selectedRow, 1).toString() : null;
    }

    public Fournisseur getSelectedFournisseur() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return new Fournisseur(
                Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString()),
                tableModel.getValueAt(selectedRow, 1).toString(),
                tableModel.getValueAt(selectedRow, 2).toString(),
                tableModel.getValueAt(selectedRow, 3).toString(),
                tableModel.getValueAt(selectedRow, 4).toString()
            );
        }
        return null;
    }

    public void remplirChamps(Fournisseur fournisseur) {
        if (fournisseur != null) {
            nom.setText(fournisseur.getNom());
            telephone.setText(fournisseur.getTelephone());
            email.setText(fournisseur.getEmail());
            adresse.setText(fournisseur.getAdresse());
        }
    }

    public void viderChamps() {
        nom.setText("");
        telephone.setText("");
        email.setText("");
        adresse.setText("");
    }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean confirmerSuppression(String nomFournisseur) {
        return JOptionPane.showConfirmDialog(
            this, 
            "Voulez-vous vraiment supprimer le fournisseur '" + nomFournisseur + "' ?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        ) == JOptionPane.YES_OPTION;
    }
        
    public void afficherFournisseurs(List<Fournisseur> fournisseurs) {
        tableModel.setRowCount(0);
        System.out.println("Affichage de " + fournisseurs.size() + " fournisseurs dans la vue");
        
        for (Fournisseur fournisseur : fournisseurs) { 
            tableModel.addRow(new Object[] {
                fournisseur.getId(), 
                fournisseur.getNom(), 
                fournisseur.getTelephone(), 
                fournisseur.getEmail(),
                fournisseur.getAdresse()
            });
        }
        
        System.out.println("Nombre de lignes dans le tableau: " + tableModel.getRowCount());
    }
}