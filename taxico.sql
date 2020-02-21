-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 20 fév. 2020 à 19:40
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `taxico`
--

-- --------------------------------------------------------

--
-- Structure de la table `code_promo`
--

DROP TABLE IF EXISTS `code_promo`;
CREATE TABLE IF NOT EXISTS `code_promo` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `balance` int(11) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `colis`
--

DROP TABLE IF EXISTS `colis`;
CREATE TABLE IF NOT EXISTS `colis` (
  `id_c` int(11) NOT NULL AUTO_INCREMENT,
  `depart` varchar(250) NOT NULL,
  `destination` varchar(250) NOT NULL,
  `nom_expediteur` varchar(250) NOT NULL,
  `nom_destinataire` varchar(250) NOT NULL,
  `poids` float NOT NULL,
  `etat` int(11) NOT NULL,
  `id_v` int(11) NOT NULL,
  `id_u` int(11) NOT NULL,
  PRIMARY KEY (`id_c`),
  KEY `ckuser` (`id_u`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id_commande` int(11) NOT NULL,
  `pt_depart` varchar(250) NOT NULL,
  `pt_arrivee` varchar(250) NOT NULL,
  `prix` float NOT NULL,
  `type_paiement` varchar(250) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `client` int(11) NOT NULL,
  `vehicule` int(11) NOT NULL,
  KEY `clientk` (`client`),
  KEY `karhba` (`vehicule`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contrat`
--

DROP TABLE IF EXISTS `contrat`;
CREATE TABLE IF NOT EXISTS `contrat` (
  `id_contrat` int(11) NOT NULL AUTO_INCREMENT,
  `type_c` varchar(250) NOT NULL,
  `duree` int(11) NOT NULL,
  `id_chauff` int(11) NOT NULL,
  PRIMARY KEY (`id_contrat`),
  KEY `chiffour` (`id_chauff`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `evennement`
--

DROP TABLE IF EXISTS `evennement`;
CREATE TABLE IF NOT EXISTS `evennement` (
  `id_event` int(11) NOT NULL AUTO_INCREMENT,
  `nom_event` varchar(250) NOT NULL,
  `date_event` date NOT NULL,
  `duree_event` int(11) NOT NULL,
  `capacite` int(11) NOT NULL,
  `emplacement` int(11) NOT NULL,
  `etat_event` varchar(250) NOT NULL,
  PRIMARY KEY (`id_event`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fidelite`
--

DROP TABLE IF EXISTS `fidelite`;
CREATE TABLE IF NOT EXISTS `fidelite` (
  `id_fid` int(11) NOT NULL AUTO_INCREMENT,
  `point_fid` int(11) NOT NULL,
  `code_fid` int(11) NOT NULL,
  PRIMARY KEY (`id_fid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `id_r` int(11) NOT NULL AUTO_INCREMENT,
  `message` text NOT NULL,
  `etat` varchar(250) NOT NULL,
  `date_rec` datetime NOT NULL,
  `reponse` text NOT NULL,
  `idch` int(11) NOT NULL,
  `idclient` int(11) NOT NULL,
  PRIMARY KEY (`id_r`),
  KEY `fkuser` (`idch`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id_u` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(250) NOT NULL,
  `prenom` varchar(250) NOT NULL,
  `tel` int(11) NOT NULL,
  `mail` varchar(250) NOT NULL,
  `naissance` varchar(250) NOT NULL,
  `creation` varchar(250) NOT NULL,
  `active` int(11) NOT NULL,
  `image` varchar(250) NOT NULL,
  `type` varchar(250) NOT NULL,
  `cin` int(11) NOT NULL,
  `permis` int(11) NOT NULL,
  `nom_compte` varchar(250) NOT NULL,
  `rib_compte` int(11) NOT NULL,
  `experience` int(11) NOT NULL,
  `nbr_course` int(11) NOT NULL,
  `point_fidelite` int(11) NOT NULL,
  `id_event` int(11) NOT NULL,
  PRIMARY KEY (`id_u`),
  KEY `id_eventfk` (`id_event`),
  KEY `ptfidid` (`point_fidelite`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE IF NOT EXISTS `vehicule` (
  `id_v` int(11) NOT NULL AUTO_INCREMENT,
  `matricule` varchar(250) NOT NULL,
  `marque` varchar(250) NOT NULL,
  `modele` varchar(250) NOT NULL,
  `cartegrise` varchar(250) NOT NULL,
  `couleur` varchar(250) NOT NULL,
  `type` varchar(250) NOT NULL,
  `dispo` int(11) NOT NULL,
  `position` varchar(250) NOT NULL,
  `destination` varchar(250) NOT NULL,
  `etat` int(11) NOT NULL,
  `places` int(11) NOT NULL,
  `dateco` varchar(250) NOT NULL,
  `archive` int(250) NOT NULL,
  PRIMARY KEY (`id_v`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `colis`
--
ALTER TABLE `colis`
  ADD CONSTRAINT `ckuser` FOREIGN KEY (`id_u`) REFERENCES `user` (`id_u`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `clientk` FOREIGN KEY (`client`) REFERENCES `user` (`id_u`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `karhba` FOREIGN KEY (`vehicule`) REFERENCES `vehicule` (`id_v`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `contrat`
--
ALTER TABLE `contrat`
  ADD CONSTRAINT `chiffour` FOREIGN KEY (`id_chauff`) REFERENCES `user` (`id_u`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `fkuser` FOREIGN KEY (`idch`) REFERENCES `user` (`id_u`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `id_eventfk` FOREIGN KEY (`id_event`) REFERENCES `evennement` (`id_event`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ptfidid` FOREIGN KEY (`point_fidelite`) REFERENCES `fidelite` (`id_fid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `userid` FOREIGN KEY (`id_v`) REFERENCES `user` (`id_u`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
