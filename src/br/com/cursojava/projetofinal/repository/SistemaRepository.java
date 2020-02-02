package br.com.cursojava.projetofinal.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cursojava.projetofinal.model.Sistema;

public class SistemaRepository extends AbstractRepository {

	public void save(Sistema sist) throws RepositoryException {
		// gravar dados
		// alt shit Z para inserir os try cath no bloco, para tratamento de erro

		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = getConnection();
			ps = cn.prepareStatement("INSERT INTO monitoracao_ambientes.sistemas (nm_sistema,datacadastro) VALUES (?,now()) ");
			ps.setString(1, sist.getNome().toUpperCase());
			ps.execute();

		} catch (SQLException e) {
			throw new RepositoryException("Falha ao Gravar o Sistema. ", e);

			
		} finally {

			closeResources(cn, ps, null);

		}

	}

	public List<Sistema> findAll() throws RepositoryException {
		// metodo retorno todo mundo

		/* para simular teste de erro usar o thorw */
		// throw new RepositoryException("Erro teste");
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			cn = getConnection();
			st = cn.createStatement();
			rs = st.executeQuery("select * from monitoracao_ambientes.sistemas ORDER BY nm_sistema");

			/* Criar objeto lista vazio */
			List<Sistema> lista = new ArrayList<>();

			while (rs.next()) {
				/* retorna dados e preenche a lista */
				Sistema sistema = new Sistema();
				sistema.setId(rs.getInt("id_sistema"));
				sistema.setNome(rs.getString("nm_sistema"));
				lista.add(sistema);
			}

			/* retorna lista */
			return lista;

		} catch (SQLException e) {
			throw new RepositoryException("Falha ao carregar lista de Sistema. ", e);

		} finally {

			closeResources(cn, st, rs);

		}

	}
	

	// findByname
	
	
	public List<Sistema> findByname(String nomeSistema) throws RepositoryException {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cn = getConnection();
			ps = cn.prepareStatement("select * from monitoracao_ambientes.sistemas where nm_sistema like ? order by id_sistema ");
			ps.setString(1, "%" + nomeSistema + "%");
			
			// ps.setString(1,nomeCargo );
			
			
			rs = ps.executeQuery();

			/* Criar objeto lista vazio */
			List<Sistema> lista = new ArrayList<>();

			while (rs.next()) {
				/* retorna dados e preenche a lista */
				Sistema sistema = new Sistema();
				sistema.setId(rs.getInt("id_sistema"));
				sistema.setNome(rs.getString("nm_sistema"));
				lista.add(sistema);
			}

			/* retorna lista */
			return lista;

		} catch (SQLException e) {
			throw new RepositoryException("Falha ao pesquisar Sistema. ", e);

		} finally {

			closeResources(cn, ps, rs);

		}

	}

}
