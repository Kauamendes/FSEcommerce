-- MySQL dump 10.13  Distrib 8.0.41, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: fsecommerce
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS ` categoria `;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE ` categoria `
(
    `
    id
    `
    varchar
(
    36
) NOT NULL,
    ` nome ` varchar
(
    255
) NOT NULL,
    ` descricao ` text,
    ` criado_por ` varchar
(
    255
) NOT NULL,
    ` criado_em ` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ` alterado_por ` varchar
(
    255
) NOT NULL,
    ` alterado_em ` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY
(
    `
    id
    `
),
    KEY ` idx_categoria_nome `
(
    `
    nome
    `
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO ` categoria `
VALUES ('4ee56c20-f158-11ef-804f-0242ac120002', 'Eletrônicos', 'Aparelhos e acessórios eletrônicos', 'SISTEMA', '2025-02-22 20:05:05', 'SISTEMA', '2025-02-22 20:05:05'), ('4ee56d9e-f158-11ef-804f-0242ac120002', 'Computadores', 'Desktops, notebooks e componentes', 'SISTEMA', '2025-02-22 20:05:05', 'SISTEMA', '2025-02-22 20:05:05'), ('4ee56e17-f158-11ef-804f-0242ac120002', 'Acessórios', 'Acessórios para eletrônicos, celulares e computadores', 'SISTEMA', '2025-02-22 20:05:05', 'SISTEMA', '2025-02-22 20:05:05');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS ` flyway_schema_history `;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE ` flyway_schema_history `
(
    `
    installed_rank
    `
    int
    NOT
    NULL,
    `
    version
    `
    varchar
(
    50
) DEFAULT NULL,
    ` description ` varchar
(
    200
) NOT NULL,
    ` type ` varchar
(
    20
) NOT NULL,
    ` script ` varchar
(
    1000
) NOT NULL,
    ` checksum ` int DEFAULT NULL,
    ` installed_by ` varchar
(
    100
) NOT NULL,
    ` installed_on ` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ` execution_time ` int NOT NULL,
    ` success ` tinyint
(
    1
) NOT NULL,
    PRIMARY KEY
(
    `
    installed_rank
    `
),
    KEY ` flyway_schema_history_s_idx `
(
    `
    success
    `
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO ` flyway_schema_history `
VALUES (1, '1.0.0', 'estrutura inicial', 'SQL', 'V1_0_0__estrutura_inicial.sql', -2143138205, 'root', '2025-02-22 20:05:05', 552, 1), (2, '1.0.1', 'dados iniciais', 'SQL', 'V1_0_1__dados_iniciais.sql', -1059661785, 'root', '2025-02-22 20:05:05', 5, 1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS ` pedido `;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE ` pedido `
(
    `
    id
    `
    varchar
(
    36
) NOT NULL,
    ` usuario_id ` varchar
(
    36
) NOT NULL,
    ` status ` varchar
(
    50
) NOT NULL,
    ` subtotal ` decimal
(
    10,
    2
) NOT NULL,
    ` criado_por ` varchar
(
    255
) NOT NULL,
    ` criado_em ` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ` alterado_por ` varchar
(
    255
) NOT NULL,
    ` alterado_em ` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY
(
    `
    id
    `
),
    KEY ` idx_pedido_usuario_id `
(
    `
    usuario_id
    `
),
    KEY ` idx_pedido_status `
(
    `
    status
    `
),
    KEY ` idx_pedido_criado_em `
(
    `
    criado_em
    `
),
    KEY ` idx_pedido_usuario_criado_em `
(
    `
    usuario_id
    `,
    `
    criado_em
    `
),
    KEY ` idx_pedido_usuario_id_subtotal `
(
    `
    usuario_id
    `,
    `
    subtotal
    `
),
    KEY ` idx_pedido_criado_em_subtotal `
(
    `
    criado_em
    `,
    `
    subtotal
    `
),
    CONSTRAINT ` pedido_ibfk_1 ` FOREIGN KEY
(
    `
    usuario_id
    `
) REFERENCES ` usuario `
(
    `
    id
    `
)
                                                                 ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_produto`
--

DROP TABLE IF EXISTS ` pedido_produto `;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE ` pedido_produto `
(
    `
    id
    `
    varchar
(
    36
) NOT NULL,
    ` pedido_id ` varchar
(
    36
) NOT NULL,
    ` produto_id ` varchar
(
    36
) NOT NULL,
    ` quantidade ` int NOT NULL,
    ` preco_unitario ` decimal
(
    10,
    2
) NOT NULL,
    PRIMARY KEY
(
    `
    id
    `
),
    KEY ` idx_pedido_produto_pedido_id `
(
    `
    pedido_id
    `
),
    KEY ` idx_pedido_produto_produto_id `
(
    `
    produto_id
    `
),
    CONSTRAINT ` pedido_produto_ibfk_1 ` FOREIGN KEY
(
    `
    pedido_id
    `
) REFERENCES ` pedido `
(
    `
    id
    `
) ON DELETE CASCADE,
    CONSTRAINT ` pedido_produto_ibfk_2 ` FOREIGN KEY
(
    `
    produto_id
    `
) REFERENCES ` produto `
(
    `
    id
    `
)
  ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_produto`
--

LOCK TABLES `pedido_produto` WRITE;
/*!40000 ALTER TABLE `pedido_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS ` produto `;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE ` produto `
(
    `
    id
    `
    varchar
(
    36
) NOT NULL,
    ` nome ` varchar
(
    255
) NOT NULL,
    ` descricao ` text,
    ` preco ` decimal
(
    10,
    2
) NOT NULL,
    ` categoria_id ` varchar
(
    36
) NOT NULL,
    ` quantidade ` int NOT NULL DEFAULT '0',
    ` quantidade_reservada ` int NOT NULL DEFAULT '0',
    ` criado_por ` varchar
(
    255
) NOT NULL,
    ` criado_em ` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ` alterado_por ` varchar
(
    255
) NOT NULL,
    ` alterado_em ` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY
(
    `
    id
    `
),
    KEY ` idx_produto_categoria_id `
(
    `
    categoria_id
    `
),
    KEY ` idx_produto_nome `
(
    `
    nome
    `
),
    CONSTRAINT ` produto_ibfk_1 ` FOREIGN KEY
(
    `
    categoria_id
    `
) REFERENCES ` categoria `
(
    `
    id
    `
)
                                                                 ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO ` produto `
VALUES ('4ee59847-f158-11ef-804f-0242ac120002', 'Smartphone', 'Celular top de linha', 3999.99, '4ee56c20-f158-11ef-804f-0242ac120002', 10, 0, 'SISTEMA', '2025-02-22 20:05:05', 'SISTEMA', '2025-02-22 20:05:05'), ('4ee59c39-f158-11ef-804f-0242ac120002', 'Notebook Gamer', 'Notebook com placa de vídeo dedicada', 5999.99, '4ee56d9e-f158-11ef-804f-0242ac120002', 5, 0, 'SISTEMA', '2025-02-22 20:05:05', 'SISTEMA', '2025-02-22 20:05:05'), ('4ee59e72-f158-11ef-804f-0242ac120002', 'Mouse Óptico', 'Mouse com fio para jogos', 129.90, '4ee56e17-f158-11ef-804f-0242ac120002', 30, 0, 'SISTEMA', '2025-02-22 20:05:05', 'SISTEMA', '2025-02-22 20:05:05'), ('4ee5a0b9-f158-11ef-804f-0242ac120002', 'Teclado Mecânico', 'Teclado mecânico RGB', 349.90, '4ee56e17-f158-11ef-804f-0242ac120002', 25, 0, 'SISTEMA', '2025-02-22 20:05:05', 'SISTEMA', '2025-02-22 20:05:05'), ('4ee5a2ac-f158-11ef-804f-0242ac120002', 'Fone de Ouvido Bluetooth', 'Fone de ouvido sem fio com cancelamento de ruído', 799.90, '4ee56e17-f158-11ef-804f-0242ac120002', 15, 0, 'SISTEMA', '2025-02-22 20:05:05', 'SISTEMA', '2025-02-22 20:05:05'), ('4ee5a4df-f158-11ef-804f-0242ac120002', 'Monitor 4K', 'Monitor ultra HD para jogos e edição de vídeo', 2299.99, '4ee56d9e-f158-11ef-804f-0242ac120002', 10, 0, 'SISTEMA', '2025-02-22 20:05:05', 'SISTEMA', '2025-02-22 20:05:05');
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS ` usuario `;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE ` usuario `
(
    `
    id
    `
    varchar
(
    36
) NOT NULL,
    ` nome ` varchar
(
    255
) NOT NULL,
    ` email ` varchar
(
    255
) NOT NULL,
    ` senha ` varchar
(
    255
) NOT NULL,
    ` role ` varchar
(
    50
) NOT NULL,
    ` criado_por ` varchar
(
    255
) NOT NULL,
    ` criado_em ` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ` alterado_por ` varchar
(
    255
) NOT NULL,
    ` alterado_em ` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY
(
    `
    id
    `
),
    UNIQUE KEY ` email `
(
    `
    email
    `
),
    KEY ` idx_usuario_email `
(
    `
    email
    `
),
    KEY ` idx_usuario_id `
(
    `
    id
    `
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO ` usuario `
VALUES ('4ee54be3-f158-11ef-804f-0242ac120002', 'Admin', 'admin@ecommerce.com', '$2a$12$oeMCFj6aLz8cVXf6R1nWQeM4E8LTdoFvbj6NooZS18W.dMppHLQRq', 'ROLE_ADMIN', 'SISTEMA', '2025-02-22 20:05:05', 'SISTEMA', '2025-02-22 20:05:05'), ('4ee54ede-f158-11ef-804f-0242ac120002', 'Cliente 1', 'cliente1@ecommerce.com', '$2a$10$VQi2iMA9sZ1NJ6ywbUZj3eFXEEQijz1fBJC2YwR4BdOv4LlD.z3Ua', 'ROLE_USUARIO', 'SISTEMA', '2025-02-22 20:05:05', 'SISTEMA', '2025-02-22 20:05:05');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-22 17:05:29
