CREATE DATABASE impacta;

CREATE USER aluno IDENTIFIED BY 'java';
CREATE USER 'aluno'@'localhost' IDENTIFIED BY 'java';

GRANT ALL ON impacta.* TO aluno;
