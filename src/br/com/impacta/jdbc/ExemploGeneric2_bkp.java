package br.com.impacta.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// classe mae bstract nao pode ser instanciada

public abstract class ExemploGeneric2_bkp {
	public static Connection getConnection() throws SQLException {
	
			Connection cn = DriverManager.getConnection(
					"jdbc:mysql://4p25_instrutor:3306/impacta?serverTimezone=UTC&noAccessToProcedureBodies=true","aluno","java");
	     	        
			
		return cn;	
	}

}
