-- MySQL dump 10.13  Distrib 5.5.28, for Win32 (x86)
--
-- Host: localhost    Database: sanger
-- ------------------------------------------------------
-- Server version	5.5.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `automovel`
--

DROP TABLE IF EXISTS `automovel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `automovel` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACESSORIOS` longtext,
  `ANO` int(11) DEFAULT NULL,
  `AVARIASPARTEINTERNA` longtext,
  `CHASSI` varchar(255) DEFAULT NULL,
  `COR` varchar(255) DEFAULT NULL,
  `ENTRADA` date DEFAULT NULL,
  `KILOMETRAGEMENTRADA` double DEFAULT NULL,
  `MARCA` varchar(255) DEFAULT NULL,
  `MODELO` varchar(255) DEFAULT NULL,
  `PLACA` varchar(255) DEFAULT NULL,
  `SEGURO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `automovel`
--

LOCK TABLES `automovel` WRITE;
/*!40000 ALTER TABLE `automovel` DISABLE KEYS */;
/*!40000 ALTER TABLE `automovel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DTYPE` varchar(31) DEFAULT NULL,
  `NOME` varchar(255) DEFAULT NULL,
  `TELEFONE_COMERCIAL` varchar(255) DEFAULT NULL,
  `TELEFONE_MOVEL` varchar(255) DEFAULT NULL,
  `TELEFONE_RESIDENCIAL` varchar(255) DEFAULT NULL,
  `ENDERECO_ID` bigint(20) DEFAULT NULL,
  `CPF` varchar(255) DEFAULT NULL,
  `CNPJ` varchar(255) DEFAULT NULL,
  `INSCRICAO_ESTADUAL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CPF` (`CPF`),
  UNIQUE KEY `UNQ_CLIENTE_0` (`DTYPE`,`NOME`),
  KEY `FK_CLIENTE_ENDERECO_ID` (`ENDERECO_ID`),
  CONSTRAINT `FK_CLIENTE_ENDERECO_ID` FOREIGN KEY (`ENDERECO_ID`) REFERENCES `endereco` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'ClientePessoaFisica','Antônio José','','(99)9999-9999','(11)1111-1111',1,'111.111.111-11',NULL,NULL),(3,'ClientePessoaFisica','João Carlos','','','',3,'222.222.222-22',NULL,NULL),(4,'ClientePessoaJuridica','ACME- Engenharia','(77)7777-7777','(88)8888-8888',NULL,4,NULL,'33.333.333/3333-33','89809-89');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destinatario`
--

DROP TABLE IF EXISTS `destinatario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `destinatario` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(255) DEFAULT NULL,
  `TELEFONE_COMERCIAL` varchar(255) DEFAULT NULL,
  `TELEFONE_MOVEL` varchar(255) DEFAULT NULL,
  `TELEFONE_RESIDENCIAL` varchar(255) DEFAULT NULL,
  `ENDERECO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DESTINATARIO_ENDERECO_ID` (`ENDERECO_ID`),
  CONSTRAINT `FK_DESTINATARIO_ENDERECO_ID` FOREIGN KEY (`ENDERECO_ID`) REFERENCES `endereco` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destinatario`
--

LOCK TABLES `destinatario` WRITE;
/*!40000 ALTER TABLE `destinatario` DISABLE KEYS */;
INSERT INTO `destinatario` VALUES (1,'Carlos Sampaio','(33)3333-3333','(99)9999-9999','(22)2222-2222',17);
/*!40000 ALTER TABLE `destinatario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direito`
--

DROP TABLE IF EXISTS `direito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direito` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRICAO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DESCRICAO` (`DESCRICAO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direito`
--

LOCK TABLES `direito` WRITE;
/*!40000 ALTER TABLE `direito` DISABLE KEYS */;
/*!40000 ALTER TABLE `direito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direito_perfil`
--

DROP TABLE IF EXISTS `direito_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direito_perfil` (
  `direitos_ID` int(11) NOT NULL,
  `perfis_ID` int(11) NOT NULL,
  PRIMARY KEY (`direitos_ID`,`perfis_ID`),
  KEY `FK_DIREITO_PERFIL_perfis_ID` (`perfis_ID`),
  CONSTRAINT `FK_DIREITO_PERFIL_direitos_ID` FOREIGN KEY (`direitos_ID`) REFERENCES `direito` (`ID`),
  CONSTRAINT `FK_DIREITO_PERFIL_perfis_ID` FOREIGN KEY (`perfis_ID`) REFERENCES `perfil` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direito_perfil`
--

LOCK TABLES `direito_perfil` WRITE;
/*!40000 ALTER TABLE `direito_perfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `direito_perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BAIRRO` varchar(255) DEFAULT NULL,
  `CEP` varchar(255) DEFAULT NULL,
  `CIDADE` varchar(255) DEFAULT NULL,
  `COMPLEMENTO` varchar(255) DEFAULT NULL,
  `ESTADO` int(11) DEFAULT NULL,
  `LOGRADOURO` varchar(255) DEFAULT NULL,
  `NUMERO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,'Centro','22.222-222','Rio de Janeiro','casa 101',18,'Rua Bravo','200'),(3,'Centro','11.111-111','Rio de Janeiro','casa 01',18,'Rua Alfa','100'),(4,'Centro','33.333-333','Rio de Janeiro','sala 501',18,'Rua Echo','400'),(5,'','','','',18,'',''),(6,'','','','',18,'',''),(7,'','','','',18,'',''),(8,'','','','',18,'',''),(9,'','','','',18,'',''),(10,'','','','',18,'',''),(11,'','','','',18,'',''),(12,'','','','',18,'',''),(13,'Centro','00.000-000','Rio de Janeiro','ap. 300',18,'Rua Ciclano','56'),(14,'Centro','00.000-000','Rio de Janeiro','ap. 300',18,'Rua Ciclano','56'),(15,'Centro','00.000-000','Rio de Janeiro','ap. 300',18,'Rua Ciclano','56'),(16,'Centro','00.000-000','Rio de Janeiro','ap. 300',18,'Rua Ciclano','56'),(17,'Centro','00.000-000','Rio de Janeiro','ap. 300',18,'Rua Ciclano','56');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DTYPE` varchar(31) DEFAULT NULL,
  `IDENTIDADE` varchar(255) DEFAULT NULL,
  `TELEFONE_COMERCIAL` varchar(255) DEFAULT NULL,
  `TELEFONE_MOVEL` varchar(255) DEFAULT NULL,
  `TELEFONE_RESIDENCIAL` varchar(255) DEFAULT NULL,
  `ENDERECO_ID` bigint(20) DEFAULT NULL,
  `ADMISSAO` date DEFAULT NULL,
  `CPF` varchar(255) DEFAULT NULL,
  `DEMISSAO` date DEFAULT NULL,
  `DESCRICAO` longtext,
  `NOME` varchar(255) DEFAULT NULL,
  `HABILITACAO` varchar(255) DEFAULT NULL,
  `PRONTURARIO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `IDENTIDADE` (`IDENTIDADE`),
  UNIQUE KEY `CPF` (`CPF`),
  KEY `FK_FUNCIONARIO_ENDERECO_ID` (`ENDERECO_ID`),
  CONSTRAINT `FK_FUNCIONARIO_ENDERECO_ID` FOREIGN KEY (`ENDERECO_ID`) REFERENCES `endereco` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,'Autonomo','3573763','','','',5,'2010-06-01','55.555.555-55',NULL,'','Carlos Antonio',NULL,NULL),(2,'Autonomo','768768','','','',6,'2010-06-02','99.999.999-99',NULL,'','André Santos',NULL,NULL),(3,'Autonomo','788788789','','','',7,'2010-06-09','44.444.444-44',NULL,'teste','Ronaldo dos Santos',NULL,NULL),(4,'Autonomo','555555-88','','','',8,'2010-06-08','88.888.888-88',NULL,'','Paulo Roberto',NULL,NULL),(5,'Motorista','57575757','','','',9,NULL,'17.171.717-17',NULL,NULL,'João Alberto ','7777779898',''),(6,'Motorista','6868868','','','',10,NULL,'36.363.636-36',NULL,NULL,'Claudio Antônio','87989','');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventario` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRICAO` varchar(255) DEFAULT NULL,
  `NUMERO` int(11) DEFAULT NULL,
  `QUANTIDADE` int(11) DEFAULT NULL,
  `SEGURO` double DEFAULT NULL,
  `LOCALIZACAO_ID` bigint(20) DEFAULT NULL,
  `SIMBOLO_ID` bigint(20) DEFAULT NULL,
  `TRANSPORTEINTERESTADUAL_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_INVENTARIO_TRANSPORTEINTERESTADUAL_ID` (`TRANSPORTEINTERESTADUAL_ID`),
  KEY `FK_INVENTARIO_LOCALIZACAO_ID` (`LOCALIZACAO_ID`),
  KEY `FK_INVENTARIO_SIMBOLO_ID` (`SIMBOLO_ID`),
  CONSTRAINT `FK_INVENTARIO_SIMBOLO_ID` FOREIGN KEY (`SIMBOLO_ID`) REFERENCES `simbolo` (`ID`),
  CONSTRAINT `FK_INVENTARIO_LOCALIZACAO_ID` FOREIGN KEY (`LOCALIZACAO_ID`) REFERENCES `localizacao` (`ID`),
  CONSTRAINT `FK_INVENTARIO_TRANSPORTEINTERESTADUAL_ID` FOREIGN KEY (`TRANSPORTEINTERESTADUAL_ID`) REFERENCES `transporteinterestadual` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localizacao`
--

DROP TABLE IF EXISTS `localizacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localizacao` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRICAO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DESCRICAO` (`DESCRICAO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localizacao`
--

LOCK TABLES `localizacao` WRITE;
/*!40000 ALTER TABLE `localizacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `localizacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRICAO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DESCRICAO` (`DESCRICAO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proprietario`
--

DROP TABLE IF EXISTS `proprietario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proprietario` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(255) DEFAULT NULL,
  `TELEFONE_COMERCIAL` varchar(255) DEFAULT NULL,
  `TELEFONE_MOVEL` varchar(255) DEFAULT NULL,
  `TELEFONE_RESIDENCIAL` varchar(255) DEFAULT NULL,
  `ENDERECO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PROPRIETARIO_ENDERECO_ID` (`ENDERECO_ID`),
  CONSTRAINT `FK_PROPRIETARIO_ENDERECO_ID` FOREIGN KEY (`ENDERECO_ID`) REFERENCES `endereco` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proprietario`
--

LOCK TABLES `proprietario` WRITE;
/*!40000 ALTER TABLE `proprietario` DISABLE KEYS */;
INSERT INTO `proprietario` VALUES (1,'','','','',11),(2,'','','','',12);
/*!40000 ALTER TABLE `proprietario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibo`
--

DROP TABLE IF EXISTS `recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recibo` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EMISSAO` date DEFAULT NULL,
  `FORMADEPAGAMENTO` varchar(255) DEFAULT NULL,
  `QUANTIA` double DEFAULT NULL,
  `QUANTIAPOREXTENSO` varchar(255) DEFAULT NULL,
  `REFERENTE` varchar(255) DEFAULT NULL,
  `CLIENTE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_RECIBO_CLIENTE_ID` (`CLIENTE_ID`),
  CONSTRAINT `FK_RECIBO_CLIENTE_ID` FOREIGN KEY (`CLIENTE_ID`) REFERENCES `cliente` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibo`
--

LOCK TABLES `recibo` WRITE;
/*!40000 ALTER TABLE `recibo` DISABLE KEYS */;
/*!40000 ALTER TABLE `recibo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `simbolo`
--

DROP TABLE IF EXISTS `simbolo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `simbolo` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRICAO` varchar(255) DEFAULT NULL,
  `SIGLA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DESCRICAO` (`DESCRICAO`),
  UNIQUE KEY `SIGLA` (`SIGLA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `simbolo`
--

LOCK TABLES `simbolo` WRITE;
/*!40000 ALTER TABLE `simbolo` DISABLE KEYS */;
/*!40000 ALTER TABLE `simbolo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transporteautomovel`
--

DROP TABLE IF EXISTS `transporteautomovel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transporteautomovel` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `REALIZADO` date DEFAULT NULL,
  `CLIENTE_ID` bigint(20) DEFAULT NULL,
  `AUTOMOVEL_ID` bigint(20) DEFAULT NULL,
  `DESTINATARIO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TRANSPORTEAUTOMOVEL_AUTOMOVEL_ID` (`AUTOMOVEL_ID`),
  KEY `FK_TRANSPORTEAUTOMOVEL_DESTINATARIO_ID` (`DESTINATARIO_ID`),
  KEY `FK_TRANSPORTEAUTOMOVEL_CLIENTE_ID` (`CLIENTE_ID`),
  CONSTRAINT `FK_TRANSPORTEAUTOMOVEL_CLIENTE_ID` FOREIGN KEY (`CLIENTE_ID`) REFERENCES `cliente` (`ID`),
  CONSTRAINT `FK_TRANSPORTEAUTOMOVEL_AUTOMOVEL_ID` FOREIGN KEY (`AUTOMOVEL_ID`) REFERENCES `automovel` (`ID`),
  CONSTRAINT `FK_TRANSPORTEAUTOMOVEL_DESTINATARIO_ID` FOREIGN KEY (`DESTINATARIO_ID`) REFERENCES `destinatario` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transporteautomovel`
--

LOCK TABLES `transporteautomovel` WRITE;
/*!40000 ALTER TABLE `transporteautomovel` DISABLE KEYS */;
/*!40000 ALTER TABLE `transporteautomovel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transporteinterestadual`
--

DROP TABLE IF EXISTS `transporteinterestadual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transporteinterestadual` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NUMERO` int(11) DEFAULT NULL,
  `REALIZADO` date DEFAULT NULL,
  `CLIENTE_ID` bigint(20) DEFAULT NULL,
  `DESTINATARIO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TRANSPORTEINTERESTADUAL_DESTINATARIO_ID` (`DESTINATARIO_ID`),
  KEY `FK_TRANSPORTEINTERESTADUAL_CLIENTE_ID` (`CLIENTE_ID`),
  CONSTRAINT `FK_TRANSPORTEINTERESTADUAL_CLIENTE_ID` FOREIGN KEY (`CLIENTE_ID`) REFERENCES `cliente` (`ID`),
  CONSTRAINT `FK_TRANSPORTEINTERESTADUAL_DESTINATARIO_ID` FOREIGN KEY (`DESTINATARIO_ID`) REFERENCES `destinatario` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transporteinterestadual`
--

LOCK TABLES `transporteinterestadual` WRITE;
/*!40000 ALTER TABLE `transporteinterestadual` DISABLE KEYS */;
/*!40000 ALTER TABLE `transporteinterestadual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transportelocal`
--

DROP TABLE IF EXISTS `transportelocal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transportelocal` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CHEGADACLIENTE` time DEFAULT NULL,
  `MINIMODEHORAS` double DEFAULT NULL,
  `OBSERVACOES` longtext,
  `PRECOPORCAIXA` double DEFAULT NULL,
  `PRECOPORHORA` double DEFAULT NULL,
  `PRECORETORNO` double DEFAULT NULL,
  `REALIZADO` date DEFAULT NULL,
  `RETORNO` time DEFAULT NULL,
  `SAIDA` time DEFAULT NULL,
  `SAIDACLIENTE` time DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  `CLIENTE_ID` bigint(20) DEFAULT NULL,
  `INVENTARIANTE_ID` bigint(20) DEFAULT NULL,
  `MOTORISTA_ID` bigint(20) DEFAULT NULL,
  `DESTINATARIO_ID` bigint(20) DEFAULT NULL,
  `VEICULODETRANSPORTE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TRANSPORTELOCAL_DESTINATARIO_ID` (`DESTINATARIO_ID`),
  KEY `FK_TRANSPORTELOCAL_VEICULODETRANSPORTE_ID` (`VEICULODETRANSPORTE_ID`),
  KEY `FK_TRANSPORTELOCAL_INVENTARIANTE_ID` (`INVENTARIANTE_ID`),
  KEY `FK_TRANSPORTELOCAL_MOTORISTA_ID` (`MOTORISTA_ID`),
  KEY `FK_TRANSPORTELOCAL_CLIENTE_ID` (`CLIENTE_ID`),
  CONSTRAINT `FK_TRANSPORTELOCAL_CLIENTE_ID` FOREIGN KEY (`CLIENTE_ID`) REFERENCES `cliente` (`ID`),
  CONSTRAINT `FK_TRANSPORTELOCAL_DESTINATARIO_ID` FOREIGN KEY (`DESTINATARIO_ID`) REFERENCES `destinatario` (`ID`),
  CONSTRAINT `FK_TRANSPORTELOCAL_INVENTARIANTE_ID` FOREIGN KEY (`INVENTARIANTE_ID`) REFERENCES `funcionario` (`ID`),
  CONSTRAINT `FK_TRANSPORTELOCAL_MOTORISTA_ID` FOREIGN KEY (`MOTORISTA_ID`) REFERENCES `funcionario` (`ID`),
  CONSTRAINT `FK_TRANSPORTELOCAL_VEICULODETRANSPORTE_ID` FOREIGN KEY (`VEICULODETRANSPORTE_ID`) REFERENCES `veiculodetransporte` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transportelocal`
--

LOCK TABLES `transportelocal` WRITE;
/*!40000 ALTER TABLE `transportelocal` DISABLE KEYS */;
INSERT INTO `transportelocal` VALUES (1,'10:00:00',6,'',20,50,100,'2013-06-12','18:00:00','09:00:00','12:00:00',1000,4,1,6,1,2);
/*!40000 ALTER TABLE `transportelocal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transportelocal_funcionario`
--

DROP TABLE IF EXISTS `transportelocal_funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transportelocal_funcionario` (
  `ajudantes_ID` bigint(20) NOT NULL,
  `TransporteLocal_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ajudantes_ID`,`TransporteLocal_ID`),
  KEY `FK_TRANSPORTELOCAL_FUNCIONARIO_TransporteLocal_ID` (`TransporteLocal_ID`),
  CONSTRAINT `FK_TRANSPORTELOCAL_FUNCIONARIO_TransporteLocal_ID` FOREIGN KEY (`TransporteLocal_ID`) REFERENCES `transportelocal` (`ID`),
  CONSTRAINT `FK_TRANSPORTELOCAL_FUNCIONARIO_ajudantes_ID` FOREIGN KEY (`ajudantes_ID`) REFERENCES `funcionario` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transportelocal_funcionario`
--

LOCK TABLES `transportelocal_funcionario` WRITE;
/*!40000 ALTER TABLE `transportelocal_funcionario` DISABLE KEYS */;
INSERT INTO `transportelocal_funcionario` VALUES (2,1),(3,1),(4,1);
/*!40000 ALTER TABLE `transportelocal_funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOGIN` varchar(255) NOT NULL,
  `NOME` varchar(255) NOT NULL,
  `SENHA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LOGIN` (`LOGIN`),
  UNIQUE KEY `NOME` (`NOME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_perfil`
--

DROP TABLE IF EXISTS `usuario_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_perfil` (
  `perfis_ID` int(11) NOT NULL,
  `usuarios_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`perfis_ID`,`usuarios_ID`),
  KEY `FK_USUARIO_PERFIL_usuarios_ID` (`usuarios_ID`),
  CONSTRAINT `FK_USUARIO_PERFIL_perfis_ID` FOREIGN KEY (`perfis_ID`) REFERENCES `perfil` (`ID`),
  CONSTRAINT `FK_USUARIO_PERFIL_usuarios_ID` FOREIGN KEY (`usuarios_ID`) REFERENCES `usuario` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_perfil`
--

LOCK TABLES `usuario_perfil` WRITE;
/*!40000 ALTER TABLE `usuario_perfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veiculodetransporte`
--

DROP TABLE IF EXISTS `veiculodetransporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `veiculodetransporte` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ANO` int(11) DEFAULT NULL,
  `CHASSI` varchar(255) DEFAULT NULL,
  `CIDADE` varchar(255) DEFAULT NULL,
  `COR` varchar(255) DEFAULT NULL,
  `ESTADO` int(11) DEFAULT NULL,
  `MARCA` varchar(255) DEFAULT NULL,
  `MODELO` varchar(255) DEFAULT NULL,
  `PLACA` varchar(255) DEFAULT NULL,
  `PROPRIETARIO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_VEICULODETRANSPORTE_PROPRIETARIO_ID` (`PROPRIETARIO_ID`),
  CONSTRAINT `FK_VEICULODETRANSPORTE_PROPRIETARIO_ID` FOREIGN KEY (`PROPRIETARIO_ID`) REFERENCES `proprietario` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veiculodetransporte`
--

LOCK TABLES `veiculodetransporte` WRITE;
/*!40000 ALTER TABLE `veiculodetransporte` DISABLE KEYS */;
INSERT INTO `veiculodetransporte` VALUES (1,NULL,NULL,'','',18,'Volks','Caminhão Baú','HJH-7879',1),(2,NULL,NULL,'','',18,'Volks','Furgão','YTR-7898',2);
/*!40000 ALTER TABLE `veiculodetransporte` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-03 20:21:19
