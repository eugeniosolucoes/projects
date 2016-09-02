-- phpMyAdmin SQL Dump
-- version 4.0.10.12
-- http://www.phpmyadmin.net
--
-- Servidor: mysql04.eugeniosolucoes.eti.br
-- Tempo de Geração: 01/09/2016 às 20:14
-- Versão do servidor: 5.1.54-rel12.6-log
-- Versão do PHP: 5.6.24-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de dados: `eugeniosolucoes3`
--
CREATE DATABASE IF NOT EXISTS `eugeniosolucoes3` DEFAULT CHARACTER SET latin1 COLLATE latin1_general_ci;
USE `eugeniosolucoes3`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `usuario` bigint(20) DEFAULT NULL,
  `categoria` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario` (`usuario`,`descricao`,`tipo`),
  KEY `FK_categoria_categoria` (`categoria`),
  KEY `FK_categoria_usuario` (`usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=366 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `frequencia`
--

DROP TABLE IF EXISTS `frequencia`;
CREATE TABLE IF NOT EXISTS `frequencia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `usuario` bigint(20) DEFAULT NULL,
  `frequencia` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario` (`usuario`,`descricao`),
  KEY `FK_frequencia_frequencia` (`frequencia`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `lancamento`
--

DROP TABLE IF EXISTS `lancamento`;
CREATE TABLE IF NOT EXISTS `lancamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tipo` int(11) NOT NULL DEFAULT '0',
  `descricao` varchar(255) CHARACTER SET utf8 NOT NULL,
  `inclusao` date NOT NULL,
  `quantidade` double NOT NULL,
  `realizado` tinyint(1) DEFAULT '0',
  `valor` double DEFAULT '0',
  `frequencia` bigint(20) DEFAULT NULL,
  `usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lancamento_mensalidade` (`usuario`),
  KEY `FK_lancamento_frequencia` (`frequencia`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=8648 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `lancamento_categoria`
--

DROP TABLE IF EXISTS `lancamento_categoria`;
CREATE TABLE IF NOT EXISTS `lancamento_categoria` (
  `lancamentos_id` bigint(20) NOT NULL,
  `categorias_id` bigint(20) NOT NULL,
  PRIMARY KEY (`lancamentos_id`,`categorias_id`),
  KEY `FK_lancamento_categoria_categorias_id` (`categorias_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura para tabela `saldo`
--

DROP TABLE IF EXISTS `saldo`;
CREATE TABLE IF NOT EXISTS `saldo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ano` int(11) DEFAULT NULL,
  `mes` int(11) DEFAULT NULL,
  `valor_inicial` double DEFAULT NULL,
  `usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario` (`mes`,`ano`,`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Estrutura stand-in para view `vw_poupanca`
--
DROP VIEW IF EXISTS `vw_poupanca`;
CREATE TABLE IF NOT EXISTS `vw_poupanca` (
`SUM( valor )` double
);
-- --------------------------------------------------------

--
-- Estrutura para view `vw_poupanca`
--
DROP TABLE IF EXISTS `vw_poupanca`;

CREATE ALGORITHM=UNDEFINED DEFINER=`eugeniosolucoes3`@`%` SQL SECURITY DEFINER VIEW `vw_poupanca` AS select sum(`lancamento`.`valor`) AS `SUM( valor )` from `lancamento` where ((`lancamento`.`descricao` like 'poupan%a') and (`lancamento`.`inclusao` >= now()));

--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `categoria`
--
ALTER TABLE `categoria`
  ADD CONSTRAINT `FK_categoria_categoria` FOREIGN KEY (`categoria`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FK_categoria_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`);

--
-- Restrições para tabelas `frequencia`
--
ALTER TABLE `frequencia`
  ADD CONSTRAINT `FK_frequencia_frequencia` FOREIGN KEY (`frequencia`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FK_frequencia_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`);

--
-- Restrições para tabelas `lancamento_categoria`
--
ALTER TABLE `lancamento_categoria`
  ADD CONSTRAINT `FK_lancamento_categoria_categorias_id` FOREIGN KEY (`categorias_id`) REFERENCES `categoria` (`id`),
  ADD CONSTRAINT `FK_lancamento_categoria_lancamentos_id` FOREIGN KEY (`lancamentos_id`) REFERENCES `lancamento` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
