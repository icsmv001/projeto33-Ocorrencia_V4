CREATE DATABASE impacta;
GO

CREATE LOGIN aluno WITH PASSWORD = 'java', DEFAULT_DATABASE = impacta;
GO

USE impacta;
GO

CREATE USER aluno FOR LOGIN aluno;
GO

EXEC sp_addrolemember 'db_owner', 'aluno';
GO
