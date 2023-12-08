# SQL-Front 5.1  (Build 4.16)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: mysql.purrfect.codes    Database: purrfect06
# ------------------------------------------------------
# Server version 5.5.5-10.6.15-MariaDB-log

DROP DATABASE IF EXISTS `purrfect06`;
CREATE DATABASE `purrfect06` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `purrfect06`;

#
# Source for table Adocao
#

DROP TABLE IF EXISTS `Adocao`;
CREATE TABLE `Adocao` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Adotante_Id` int(11) DEFAULT NULL,
  `Animal_Id` int(11) DEFAULT NULL,
  `Data` timestamp NOT NULL DEFAULT current_timestamp(),
  `apagado` binary(1) DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `Adotante_Id` (`Adotante_Id`),
  KEY `Animal_Id` (`Animal_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

#
# Dumping data for table Adocao
#

INSERT INTO `Adocao` VALUES (1,1,1,'2023-11-08 13:44:36','0');
INSERT INTO `Adocao` VALUES (2,2,6,'2023-11-08 13:44:36','0');
INSERT INTO `Adocao` VALUES (3,4,7,'2023-11-08 13:44:37','0');
INSERT INTO `Adocao` VALUES (4,6,5,'2023-11-08 13:44:37','0');
INSERT INTO `Adocao` VALUES (5,1,2,'2023-11-08 13:44:39','0');
INSERT INTO `Adocao` VALUES (6,1,3,'2023-11-08 13:44:39','0');
INSERT INTO `Adocao` VALUES (7,1,4,'2023-11-08 13:44:39','0');
INSERT INTO `Adocao` VALUES (19,11,12,'2012-12-12 12:12:12','0');

#
# Source for table Adotante
#

DROP TABLE IF EXISTS `Adotante`;
CREATE TABLE `Adotante` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `CPF` varchar(15) DEFAULT NULL,
  `Telefone` varchar(20) DEFAULT NULL,
  `Genero` varchar(100) DEFAULT NULL,
  `Endereco_Id` int(11) DEFAULT NULL,
  `JanelasTeladas` binary(1) DEFAULT NULL,
  `Apagado` binary(1) DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

#
# Dumping data for table Adotante
#

INSERT INTO `Adotante` VALUES (1,'Claudia Cavalcante Fonseca','himehimur@gmail.com','000.000.000-00','(00) 000000-0000','Feminino',1,'1','0');
INSERT INTO `Adotante` VALUES (2,'Maria Nazaré de Holanda Fonseca','vovonazare@gmail.com','000.000.000-00','(00) 000000-0000','Feminino',2,'1','0');
INSERT INTO `Adotante` VALUES (3,'Johann Felipe Voigt','jfvoigt@gmail.com','111.111.111-11','(11) 12300-0000','Masculino',3,'1','0');
INSERT INTO `Adotante` VALUES (4,'Julia Tholl','juliatholl@gmail.com','222.222.222-22','(11) 12345-3400','Feminino',2,'0','0');
INSERT INTO `Adotante` VALUES (5,'Marcia Maria Voigt','marciavoigt@gmail.com','333.333.333-33','(21) 23345-3400','Feminino',3,'1','0');
INSERT INTO `Adotante` VALUES (6,'Kevin Cavalcante Fonseca','kevinfonseca@gmail.com','444.444.444-44','(82) 34224-0202','Masculino',1,'1','0');
INSERT INTO `Adotante` VALUES (7,'Isabelly Luana Silvana Silva','isabelly_silva@sadefem.com.br','661.095.614-60','(88) 2991-1357','Feminino',6,'0','0');
INSERT INTO `Adotante` VALUES (8,'Manuela Francisca Tatiane Assis','manuela_francisca_assis@mega.com.br','370.343.923-82','(71) 2827-3433','Feminino',7,'1','0');
INSERT INTO `Adotante` VALUES (9,'Joaquim Thiago Theo da Conceição','joaquim_thiago_daconceicao@construtoraplaneta.com.br','697.653.671-04','(91) 3846-9253','Masculino',8,'0','0');
INSERT INTO `Adotante` VALUES (11,'Logan','logan@gmail.com','000.111.222-33','1234-121','Masculino',9,'1','0');

#
# Source for table Animal
#

DROP TABLE IF EXISTS `Animal`;
CREATE TABLE `Animal` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Especie_Id` int(11) DEFAULT NULL,
  `Nome` varchar(255) DEFAULT NULL,
  `DataNascimento` date DEFAULT NULL,
  `DataNascimentoAproximada` binary(1) DEFAULT NULL,
  `DataMorte` date DEFAULT NULL,
  `LarTemporario_Id` int(11) DEFAULT NULL,
  `Apagado` binary(1) DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `Especie_Id` (`Especie_Id`),
  KEY `LarTemporario_Id` (`LarTemporario_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

#
# Dumping data for table Animal
#

INSERT INTO `Animal` VALUES (1,1,'Ferris','2014-06-01','1',NULL,NULL,'0');
INSERT INTO `Animal` VALUES (2,1,'Aslan','2011-01-01','1',NULL,NULL,'0');
INSERT INTO `Animal` VALUES (3,1,'Sion','2014-06-01','1',NULL,NULL,'0');
INSERT INTO `Animal` VALUES (4,1,'Nezumi','2014-11-01','1',NULL,NULL,'0');
INSERT INTO `Animal` VALUES (5,1,'Gohann','2019-11-01','1',NULL,NULL,'0');
INSERT INTO `Animal` VALUES (6,1,'Nêga','2017-11-01','1',NULL,NULL,'0');
INSERT INTO `Animal` VALUES (7,2,'Athena','2021-11-01','1',NULL,NULL,'0');
INSERT INTO `Animal` VALUES (8,2,'Neve','2021-11-01','1',NULL,1,'0');
INSERT INTO `Animal` VALUES (9,2,'Estrela','2021-11-01','1',NULL,1,'0');
INSERT INTO `Animal` VALUES (10,2,'Kiara','2020-11-01','0',NULL,3,'0');
INSERT INTO `Animal` VALUES (11,2,'Start','2022-11-01','1',NULL,1,'0');
INSERT INTO `Animal` VALUES (12,3,'Dunas','2020-01-01','0',NULL,NULL,'0');
INSERT INTO `Animal` VALUES (13,1,'Clark','2017-11-01','1',NULL,2,'0');
INSERT INTO `Animal` VALUES (14,1,'Rama','2021-11-01','1',NULL,1,'0');
INSERT INTO `Animal` VALUES (15,9,'Jizji','2018-01-01','1',NULL,2,'0');
INSERT INTO `Animal` VALUES (16,1,'Elba','2021-11-01','0',NULL,3,'1');
INSERT INTO `Animal` VALUES (17,1,'Garfield','2015-11-01','0',NULL,NULL,'0');
INSERT INTO `Animal` VALUES (19,6,'Snape','2020-01-01','1',NULL,3,'0');
INSERT INTO `Animal` VALUES (20,1,'a','2020-01-02','0',NULL,NULL,'1');
INSERT INTO `Animal` VALUES (21,1,'Cravat','2015-01-03','0',NULL,NULL,'0');
INSERT INTO `Animal` VALUES (22,1,'Hamlet','2018-03-04','0',NULL,3,'0');
INSERT INTO `Animal` VALUES (23,1,'Moonlit','2021-03-03','1',NULL,3,'0');
INSERT INTO `Animal` VALUES (24,1,'Levi','2010-01-01','1',NULL,2,'0');
INSERT INTO `Animal` VALUES (29,1,'a','2020-01-01','1',NULL,NULL,'1');

#
# Source for table AtendimentoVeterinario
#

DROP TABLE IF EXISTS `AtendimentoVeterinario`;
CREATE TABLE `AtendimentoVeterinario` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Veterinario_Id` int(11) DEFAULT NULL,
  `Animal_Id` int(11) DEFAULT NULL,
  `DataHorario` datetime DEFAULT NULL,
  `ComentariosVeterinario` text DEFAULT NULL,
  `Apagado` binary(1) DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `Veterinario_Id` (`Veterinario_Id`),
  KEY `Animal_Id` (`Animal_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

#
# Dumping data for table AtendimentoVeterinario
#

INSERT INTO `AtendimentoVeterinario` VALUES (1,1,12,'2020-02-02','Consulta de rotina e vacinação com a V5.','0');
INSERT INTO `AtendimentoVeterinario` VALUES (2,3,13,'2023-11-06 13:00:00','Diagnóstico:\nClark foi examinado e apresenta sinais leves de alergia cutânea, com coceira e vermelhidão na pele, provavelmente devido a uma reação a pulgas. \n1. Realizamos uma limpeza completa do pelo e pele do gatinho.\n2. Prescrevemos um anti-histamínico 1 vez ao dia durante 5 dias.\n3. Recomendamos a aplicação de um tratamento antipulgas tópico.','0');
INSERT INTO `AtendimentoVeterinario` VALUES (3,5,15,'2023-11-08 18:00:00','Rama foi examinada e apresenta sinais leves de diarreia, provavelmente devido a uma dieta desequilibrada. Recomendei a alteração da dieta do gato para uma ração de alta qualidade específica para gatos com sensibilidade gastrointestinal. Prescrevi um probiótico para gatos (GastroGato).\n','0');
INSERT INTO `AtendimentoVeterinario` VALUES (4,2,17,'2023-11-09 18:00:00','Garfield foi examinado e apresenta sintomas leves de resfriado felino, incluindo espirros ocasionais e corrimento nasal. Não há sinais de infecções graves. Recomendei repouso e manter o gatinho em um ambiente aquecido. Prescrevi o uso de um descongestionante para gatos (Felinosin) 2 vezes ao dia.\n','0');
INSERT INTO `AtendimentoVeterinario` VALUES (5,7,11,'2020-01-01 01:01:01','Rotina','1');

#
# Source for table Endereco
#

DROP TABLE IF EXISTS `Endereco`;
CREATE TABLE `Endereco` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CEP` varchar(10) DEFAULT NULL,
  `Cidade` varchar(255) DEFAULT NULL,
  `Estado` varchar(2) DEFAULT NULL,
  `Logradouro` varchar(255) DEFAULT NULL,
  `Numero` int(11) DEFAULT NULL,
  `Bairro` varchar(255) DEFAULT NULL,
  `Complemento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

#
# Dumping data for table Endereco
#

INSERT INTO `Endereco` VALUES (1,'89176-000','Trombudo Central','SC','Avenida Logan',2,'Bairro X','Casa');
INSERT INTO `Endereco` VALUES (2,'00000-111','Cidade Teste','SP','Rua Haru',23,'Cidade Alta','Casa 2');
INSERT INTO `Endereco` VALUES (3,'11111-222','Segundo teste','AL','Estrada Grace',12,'Cidade Universitária','Ap 22');
INSERT INTO `Endereco` VALUES (4,'57072-360','Maceió','AL','Rua Arvio',23,'Vila Y','Fundos');
INSERT INTO `Endereco` VALUES (5,'57072-430','Maceió','AL','Avenida Amirah',43,'Pallet','Casa');
INSERT INTO `Endereco` VALUES (6,'63507-290','Iguatu','CE','Travessa Qi',55,'Veridian','Ap 10');
INSERT INTO `Endereco` VALUES (7,'40015-050','Salvador','BA','Rua Unsure',175,'Van Gogh','Casa');
INSERT INTO `Endereco` VALUES (8,'66810-123','Belém','PA','Avenida Zeke',10,'Butantã','-');
INSERT INTO `Endereco` VALUES (9,'11111-11','Portia','SP','Rua X',1,'Sandrock','Casa');

#
# Source for table Espécie
#

DROP TABLE IF EXISTS `Espécie`;
CREATE TABLE `Espécie` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

#
# Dumping data for table Espécie
#

INSERT INTO `Espécie` VALUES (1,'Gato');
INSERT INTO `Espécie` VALUES (2,'Cachorro');
INSERT INTO `Espécie` VALUES (3,'Vaca/Boi');
INSERT INTO `Espécie` VALUES (4,'Porco');
INSERT INTO `Espécie` VALUES (5,'Galinha');
INSERT INTO `Espécie` VALUES (6,'Cavalo');
INSERT INTO `Espécie` VALUES (7,'Burro');
INSERT INTO `Espécie` VALUES (8,'Lagarto');
INSERT INTO `Espécie` VALUES (9,'Pássaro');
INSERT INTO `Espécie` VALUES (10,'Outra');

#
# Source for table LarTemporario
#

DROP TABLE IF EXISTS `LarTemporario`;
CREATE TABLE `LarTemporario` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `VagasTotais` int(11) DEFAULT NULL,
  `VagasOcupadas` int(11) DEFAULT NULL,
  `Endereco_Id` int(11) DEFAULT NULL,
  `Apagado` binary(1) DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `Endereco_CEP_Id` (`Endereco_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

#
# Dumping data for table LarTemporario
#

INSERT INTO `LarTemporario` VALUES (1,6,4,2,'0');
INSERT INTO `LarTemporario` VALUES (2,3,2,3,'0');
INSERT INTO `LarTemporario` VALUES (3,2,2,4,'0');
INSERT INTO `LarTemporario` VALUES (5,10,3,0,'0');

#
# Source for table ResponsavelLarTemporario
#

DROP TABLE IF EXISTS `ResponsavelLarTemporario`;
CREATE TABLE `ResponsavelLarTemporario` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `CPF` varchar(15) DEFAULT NULL,
  `Telefone` varchar(15) DEFAULT NULL,
  `Genero` varchar(30) DEFAULT NULL,
  `LarTemporario_Id` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `Apagado` binary(1) DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `LarTemporario_Id` (`LarTemporario_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

#
# Dumping data for table ResponsavelLarTemporario
#

INSERT INTO `ResponsavelLarTemporario` VALUES (1,'Charles K. Cordero','000.111.222-33','(84) 11278-9532','Masculino',1,'charles@gmail.com','0');
INSERT INTO `ResponsavelLarTemporario` VALUES (2,'Kaua Gomes Cunha','111.333.444-55','(61) 93085-9457','Masculino',1,'kaua@gmail.com','0');
INSERT INTO `ResponsavelLarTemporario` VALUES (3,'Aline Dias Carvalho','222.444.555-88','(61) 9034-9457','Feminino',2,'line@gmail.com','0');
INSERT INTO `ResponsavelLarTemporario` VALUES (4,'Gustavo Sousa Araujo','444.555.999-11','(84) 42378-9532','Masculino',1,'gusta@gmail.com','0');
INSERT INTO `ResponsavelLarTemporario` VALUES (5,'Victor Costa Ribeiro','342.456.678-11','(22) 42238-9532','Não-binário',2,'vic@gmail.com','0');
INSERT INTO `ResponsavelLarTemporario` VALUES (6,'Carlos Rodrigues Almeida','345.678.908-00','(11) 42238-9234','Masculino',3,'CarlosRA@gmail.com','0');
INSERT INTO `ResponsavelLarTemporario` VALUES (7,'Aslan Lynx','012.123.123-11','(12)123-1233','Masculino',5,'aslan@aslan.com','0');
INSERT INTO `ResponsavelLarTemporario` VALUES (8,'Eiji','123.123.123-12','(12)123-1231','Masculino',5,'eiji@eiji.com','0');
INSERT INTO `ResponsavelLarTemporario` VALUES (11,'Carla R. A.','123.123.123-12','123-4564','Feminino',3,'carla@gmail.com','1');

#
# Source for table Veterinario
#

DROP TABLE IF EXISTS `Veterinario`;
CREATE TABLE `Veterinario` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `CPF` varchar(15) DEFAULT NULL,
  `CRV` varchar(50) DEFAULT NULL,
  `CNPJ` varchar(30) DEFAULT NULL,
  `Telefone` varchar(20) DEFAULT NULL,
  `Genero` varchar(100) DEFAULT NULL,
  `Apagado` binary(1) DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

#
# Dumping data for table Veterinario
#

INSERT INTO `Veterinario` VALUES (1,'Renan Almeida Goncalves','RenanAlmeidaGoncalves@dayrep.com','822.115.916-63','06046442','18.142.594/0001-30','(81) 5767-6381','Masculino','0');
INSERT INTO `Veterinario` VALUES (2,'Luca Goncalves Barros','luangb@gmail.com','526.565.625-11','18169574','76.654.472/0001-20','(41) 9698-7304','Não binário','0');
INSERT INTO `Veterinario` VALUES (3,'Mariana Carvalho Araujo','MarianaCarvalhoAraujo@rhyta.com','540.563.523-56','74808604','04.718.831/0001-54','(11) 3322-3288','Feminino','0');
INSERT INTO `Veterinario` VALUES (4,'Guilherme Correia Pereira','(61) 7026-6540','723.174.521-01','71195841','52.442.931/0001-43','(61) 7026-6540','Masculino','0');
INSERT INTO `Veterinario` VALUES (5,'Emilly Correia Araujo','(11) 4066-6965','429.730.825-87','38771574','26.557.733/0001-15','(11) 4066-6965','Feminino','0');
INSERT INTO `Veterinario` VALUES (7,'Fang','fang@fang.com','123.123.123-11','92929','1234.1234.1234/22-0001','123-1233','Masculino','0');

#
# Source for table Veterinario_HorariosDeTrabalho
#

DROP TABLE IF EXISTS `Veterinario_HorariosDeTrabalho`;
CREATE TABLE `Veterinario_HorariosDeTrabalho` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `veterinario_id` int(255) DEFAULT NULL,
  `dia_semana` int(11) DEFAULT NULL,
  `hora_inicio` time DEFAULT NULL,
  `hora_fim` time DEFAULT NULL,
  `Apagado` binary(1) DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `veterinario_id` (`veterinario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

#
# Dumping data for table Veterinario_HorariosDeTrabalho
#

INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (2,1,1,'08:00:00','12:00:00','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (3,1,2,'08:00:00','12:00:00','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (4,1,3,'08:00:00','12:00:00','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (5,1,4,'08:00:00','12:00:00','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (6,1,5,'08:00:00','12:00:00','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (7,2,1,'13:00:00','18:00:00','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (8,2,3,'13:00:00','18:00:00','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (9,2,5,'13:00:00','18:00:00','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (10,3,1,'19:00:00','22:00:00','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (11,3,7,'15:00:00','20:00:00','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (12,3,6,'19:00:00','22:00:00','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (13,4,2,'13:00:00','18:00:00','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (14,4,4,'13:00:00','18:00:00','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (15,5,6,'08:00:00','16:00:00','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (16,7,1,'00:00:00','10:10:10','0');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (17,7,7,'05:05:05','10:10:10','1');
INSERT INTO `Veterinario_HorariosDeTrabalho` VALUES (18,7,1,'18:00:00','22:00:00','0');

#
#  Foreign keys for table Adocao
#

ALTER TABLE `Adocao`
ADD CONSTRAINT `Adocao_ibfk_1` FOREIGN KEY (`Adotante_Id`) REFERENCES `Adotante` (`Id`),
ADD CONSTRAINT `Adocao_ibfk_2` FOREIGN KEY (`Animal_Id`) REFERENCES `Animal` (`Id`);

#
#  Foreign keys for table Animal
#

ALTER TABLE `Animal`
ADD CONSTRAINT `Animal_ibfk_1` FOREIGN KEY (`Especie_Id`) REFERENCES `Espécie` (`Id`),
ADD CONSTRAINT `Animal_ibfk_2` FOREIGN KEY (`LarTemporario_Id`) REFERENCES `LarTemporario` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table AtendimentoVeterinario
#

ALTER TABLE `AtendimentoVeterinario`
ADD CONSTRAINT `AtendimentoVeterinario_ibfk_1` FOREIGN KEY (`Veterinario_Id`) REFERENCES `Veterinario` (`Id`),
ADD CONSTRAINT `AtendimentoVeterinario_ibfk_2` FOREIGN KEY (`Animal_Id`) REFERENCES `Animal` (`Id`);

#
#  Foreign keys for table Veterinario_HorariosDeTrabalho
#

ALTER TABLE `Veterinario_HorariosDeTrabalho`
ADD CONSTRAINT `Veterinario_HorariosDeTrabalho_ibfk_1` FOREIGN KEY (`veterinario_id`) REFERENCES `Veterinario` (`Id`);


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
