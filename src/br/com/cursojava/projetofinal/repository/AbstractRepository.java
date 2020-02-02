package br.com.cursojava.projetofinal.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractRepository {

	// Usando throws para repassar o erro para a classe que chamou a conexao ao
	// banco.
	protected Connection getConnection() throws SQLException {

		//Connection cn = DriverManager.getConnection("jdbc:mysql://4p25_instrutor:3306/impacta?serverTimezone=UTC&noAccessToProcedureBodies=true",
			//	"aluno", "java");

		Connection cn = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/impacta?serverTimezone=UTC","aluno","java");                        // curso pro_programador
		
		
		
		return cn;
	}

	protected void closeResources(Connection cn, Statement ps, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
			}
		}
		if (cn != null) {
			try {
				cn.close();
			} catch (SQLException e) {
			}

		}
	}

}
