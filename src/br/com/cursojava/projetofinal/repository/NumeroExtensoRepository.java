package br.com.cursojava.projetofinal.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class NumeroExtensoRepository extends AbstractRepository {

	public String numeroExtenso(int numeroExtenso) throws RepositoryException {
		Connection cn = null;
		CallableStatement cs = null;

		
		
		try {
			 cn = getConnection();
			 cs = cn.prepareCall("{call prc_numero_extenso(?,?)}");
			cs.setInt(1, numeroExtenso );
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.executeUpdate();

			String resultado = cs.getString(2);
			//// System.out.println("retorno: " + resultado);
			return resultado;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			//System.out.println("Erro de execução na procedure !! ");

			throw new RepositoryException("erro   ", e);
			
		} finally {

			closeResources(cn, cs,null);

		}

		
		
	}

}