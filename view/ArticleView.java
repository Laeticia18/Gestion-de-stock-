package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.event.*;
import Model.Article;
import java.util.List;
import java.awt.*;

public class ArticleView extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton ajouterButton;
    private JButton supprimerButton;
    private JButton modifierButton;
    private JButton fournisseurButton;
    private JTextField nom;
    private JTextField type;
    private JTextField prix;
    private JTextField quantite;
    private JTextField idFournisseur;
    
    // Couleurs modernes
    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color SUCCESS_COLOR = new Color(46, 204, 113);
    private final Color DANGER_COLOR = new Color(231, 76, 60);
    private final Color WARNING_COLOR = new Color(241, 196, 15);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private final Color PANEL_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(44, 62, 80);
    
    public ArticleView(){
        setTitle("Gestion des Articles - Systeme de Gestion");
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
        
        JLabel titleLabel = new JLabel("GESTION DES ARTICLES");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(30, 20, 400, 40);
        titlePanel.add(titleLabel);
        
        // Bouton Fournisseurs moderne
        fournisseurButton = createModernButton(" Fournisseurs", SECONDARY_COLOR);
        fournisseurButton.setBounds(1020, 20, 150, 40);
        titlePanel.add(fournisseurButton);
        
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
        
        JLabel formTitle = new JLabel("Informations Article");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        formTitle.setForeground(TEXT_COLOR);
        formTitle.setBounds(20, 10, 280, 30);
        formPanel.add(formTitle);
        
        // Champs de formulaire avec labels
        int yPos = 60;
        nom = createStyledTextField("Nom de l'article", formPanel, yPos);
        yPos += 70;
        type = createStyledTextField("Type", formPanel, yPos);
        yPos += 70;
        prix = createStyledTextField("Prix", formPanel, yPos);
        yPos += 70;
        quantite = createStyledTextField("Quantite", formPanel, yPos);
        yPos += 70;
        idFournisseur = createStyledTextField("ID Fournisseur", formPanel, yPos);
        
        // Boutons d'action
        ajouterButton = createModernButton(" Ajouter", SUCCESS_COLOR);
        ajouterButton.setBounds(20, 450, 280, 40);
        formPanel.add(ajouterButton);
        
        modifierButton = createModernButton("Modifier", WARNING_COLOR);
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
        
        JLabel tableTitle = new JLabel(" Liste des Articles");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setForeground(TEXT_COLOR);
        tableTitle.setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10));
        tablePanel.add(tableTitle, BorderLayout.NORTH);
        
        // Table stylisée
        tableModel = new DefaultTableModel(
            new String[] {"ID", "Nom", "Type", "Prix", "Quantite", "ID Fournisseur"}, 
            0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        styleTable();
        hideIdColumn();
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Listener pour remplir les champs
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    nom.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    type.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    prix.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    quantite.setText(tableModel.getValueAt(selectedRow, 4).toString());
                    idFournisseur.setText(tableModel.getValueAt(selectedRow, 5).toString());
                }
            }
        });
        
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
        table.setSelectionBackground(new Color(52, 152, 219, 50));
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
        
        // Cell renderer
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 3; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void hideIdColumn() {
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);
        table.getColumnModel().getColumn(0).setResizable(false);
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

    public void addFournisseurButtonListener(ActionListener listener) {
        fournisseurButton.addActionListener(listener);
    }

    public int getSelectedArticleId() {
        int selectedRow = table.getSelectedRow();
        return selectedRow != -1 ? Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString()) : -1;
    }
    public String getNom(){ return nom.getText(); }
    public String gettype(){ return type.getText(); }
    public String getPrix(){ return prix.getText(); }
    public String getQuantite(){ return quantite.getText(); }
    public String getIdFournisseur(){ return idFournisseur.getText(); }

    public String getSelectedArticleName() {
        int selectedRow = table.getSelectedRow();
        return selectedRow != -1 ? tableModel.getValueAt(selectedRow, 1).toString() : null;
    }

    public Article getSelectedArticle() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String prixValue = tableModel.getValueAt(selectedRow, 3).toString().replace(',', '.');
            return new Article(
                Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString()),
                tableModel.getValueAt(selectedRow, 1).toString(),
                tableModel.getValueAt(selectedRow, 2).toString(),
                Double.parseDouble(prixValue),
                Integer.parseInt(tableModel.getValueAt(selectedRow, 4).toString()),
                Integer.parseInt(tableModel.getValueAt(selectedRow, 5).toString())
            );
        }
        return null;
    }

    public void remplirChamps(Article article) {
        if (article != null) {
            nom.setText(article.getNom());
            type.setText(article.getType());
            prix.setText(String.valueOf(article.getPrix_unitaire()));
            quantite.setText(String.valueOf(article.getQuantite_stock()));
            idFournisseur.setText(String.valueOf(article.getId_fournisseur()));
        }
    }

    public void viderChamps() {
        nom.setText("");
        type.setText("");
        prix.setText("");
        quantite.setText("");
        idFournisseur.setText("");
    }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean confirmerSuppression(String nomArticle) {
        return JOptionPane.showConfirmDialog(
            this, 
            "Voulez-vous vraiment supprimer l'article '" + nomArticle + "' ?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        ) == JOptionPane.YES_OPTION;
    }
        
    public void afficherArticles(List<Article> articles) {
        tableModel.setRowCount(0);
        for (Article article : articles) { 
            tableModel.addRow(new Object[] {
                article.getId_article(),
                article.getNom(), 
                article.getType(), 
                String.format("%.2f", article.getPrix_unitaire()),
                article.getQuantite_stock(),
                article.getId_fournisseur()
            });
        }
    }
}