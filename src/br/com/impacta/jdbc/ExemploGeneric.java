package br.com.impacta.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

// classe mae bstract nao pode ser instanciada

public abstract class ExemploGeneric {
	public static Connection getConnection() throws SQLException {
	
			Connection cn = DriverManager.getConnection(
					//"jdbc:mysql://4p25_instrutor:3306/impacta?serverTimezone=UTC&noAccessToProcedureBodies=true","aluno","java");
			
			         "jdbc:mysql://4p25_instrutor:3306/impacta?serverTimezone=America\\Sao_Paulo&noAccessToProcedureBodies=true","aluno","java");
			
			        //"jdbc:mysql://4p25_instrutor:3306/impacta?useSSL=false&useTimezone=true&serverTimezone=" + TimeZone.getDefault().getID(),"aluno","java");
			        
	     	       // /America\Sao_Paulo
			
		return cn;	
	}

}
