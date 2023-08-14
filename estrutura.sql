CREATE DATABASE recursos;
USE recursos;

-- Tabela status
CREATE TABLE status (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL
);

-- Tabela recurso
CREATE TABLE recurso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    notificacao TEXT NOT NULL,
    bloco ENUM('a', 'b', 'c', 'd', 'e', 'f') NOT NULL,
    unidade INT NOT NULL,
    tipo ENUM('multa', 'advertencia') NOT NULL,
    status_id INT,
    email VARCHAR(255),
    nome VARCHAR(255),
    FOREIGN KEY (status_id) REFERENCES status(id)
);

-- Tabela interacao
CREATE TABLE interacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    recurso_id INT,
    mensagem TEXT,
    FOREIGN KEY (recurso_id) REFERENCES recurso(id)
);

-- Tabela voto
CREATE TABLE voto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    recurso_id INT,
    conselheiro_id INT,
    FOREIGN KEY (recurso_id) REFERENCES recurso(id),
    -- Aqui você pode adicionar uma chave estrangeira para a tabela conselheiro
);

-- Tabela conselho
CREATE TABLE conselho (
    id INT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(255),
    usuario VARCHAR(255),
    senha VARCHAR(255),
    email VARCHAR(255)
);
