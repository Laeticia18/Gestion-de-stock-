-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 11 mai 2026 à 12:14
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `primeur`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id_article` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `type` varchar(20) NOT NULL,
  `prix_unitaire` decimal(6,2) NOT NULL,
  `quantite_stock` int NOT NULL,
  `id_fournisseur` int NOT NULL,
  PRIMARY KEY (`id_article`),
  KEY `fk_article_fournisseur` (`id_fournisseur`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`id_article`, `nom`, `type`, `prix_unitaire`, `quantite_stock`, `id_fournisseur`) VALUES
(1, 'Banane plantain', 'fruit', 2.50, 120, 1),
(2, 'Ananas victoria', 'fruit', 3.20, 60, 1),
(3, 'Tomate pays', 'legume', 1.80, 80, 2),
(4, 'Christophine', 'legume', 1.50, 50, 2),
(5, 'Mangue Julie', 'fruit', 2.00, 100, 3);

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

DROP TABLE IF EXISTS `fournisseur`;
CREATE TABLE IF NOT EXISTS `fournisseur` (
  `id_fournisseur` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_fournisseur`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`id_fournisseur`, `nom`, `telephone`, `email`, `adresse`) VALUES
(1, 'Tropic Frais', '0590 11 22 33', 'contact@tropicfrais.fr', 'Baillif, Basse-Terre'),
(2, 'Jardin Créole', '0590 44 55 77', 'contact@jardincreole.fr', 'Gourbeyre'),
(3, 'Veggie Caraïbes', '0590 77 88 99', 'info@veggiecaraibes.fr', 'Basse-Terre');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
