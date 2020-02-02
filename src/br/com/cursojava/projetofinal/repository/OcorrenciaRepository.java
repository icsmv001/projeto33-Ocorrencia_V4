package br.com.cursojava.projetofinal.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.cursojava.projetofinal.model.Ocorrencia;

public class OcorrenciaRepository extends AbstractRepository {

	public void save(Ocorrencia ocorrencia) throws RepositoryException {
		// gravar dados
		// alt shit Z para inserir os try cath no bloco, para tratamento de erro

		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = getConnection();
			ps = cn.prepareStatement(
					"INSERT INTO MONITORACAO_AMBIENTES.OCORRENCIAS (DATA,DATA_ALTERACAO,SINTOMA,CHAMADO,ID_SISTEMA,IMPACTO,ACAO,INICIO,OBSERVACOES,FIM ,CAUSA, STATUS, HRINICIO,HRFIM,CAUSA_EXTERNA_SN) VALUES  ( date(now()), now(),?,?,?,?,?,?,?,?,?,?,?,?,?); ");

			// parametros para insert
			ps.setString(1, ocorrencia.getSintoma().toUpperCase());
			ps.setString(2, ocorrencia.getChamado().toUpperCase());
			ps.setInt(3, ocorrencia.getCodigoSistema());
			ps.setString(4, ocorrencia.getImpacto().toUpperCase());
			ps.setString(5, ocorrencia.getAcao().toUpperCase());

			// data inicio de ocorrencia
			if (ocorrencia.getDataInicio() == null) {
				ps.setNull(6, Types.DATE);
			} else {
				ps.setDate(6, Date.valueOf(ocorrencia.getDataInicio()));
			}

			ps.setString(7, ocorrencia.getObservacao().toUpperCase());

			// data fim de ocorrencia
			if (ocorrencia.getDataFim() == null) {
				ps.setNull(8, Types.DATE);
			} else {
				ps.setDate(8, Date.valueOf(ocorrencia.getDataFim()));
			}

			ps.setString(9, ocorrencia.getCausa().toUpperCase());
			ps.setString(10, ocorrencia.getStatus().toUpperCase());
			ps.setString(11, ocorrencia.getHrInicio().toUpperCase());
			ps.setString(12, ocorrencia.getHrFim().toUpperCase());
			ps.setString(13, ocorrencia.getCausaExterna_SN().toUpperCase());

			ps.execute();

		} catch (SQLException e) {
			throw new RepositoryException("Falha ao Gravar o Ocorrencia. ", e);

		} finally {

			closeResources(cn, ps, null);

		}

	}

	public void update(Ocorrencia ocorrencia, long idocorrencia) throws RepositoryException {
		// gravar dados
		// alt shit Z para inserir os try cath no bloco, para tratamento de erro

		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = getConnection();
			ps = cn.prepareStatement("UPDATE  MONITORACAO_AMBIENTES.OCORRENCIAS O " + "SET " + "O.SINTOMA=?,"
					+ "O.CHAMADO=?," + "O.ID_SISTEMA=?," + "O.IMPACTO=?," + "O.ACAO=?," + "O.INICIO=?,"
					+ "O.OBSERVACOES=?," + "O.FIM=?," + "O.CAUSA=?, " + "O.STATUS=?," + "O.HRINICIO=?," + "O.HRFIM=?,"
					+ "O.CAUSA_EXTERNA_SN=?" + "where ID_OCORRENCIA = ?");

			// parametros para
			ps.setString(1, ocorrencia.getSintoma().toUpperCase());
			ps.setString(2, ocorrencia.getChamado().toUpperCase());
			ps.setInt(3, ocorrencia.getCodigoSistema());
			ps.setString(4, ocorrencia.getImpacto().toUpperCase());
			ps.setString(5, ocorrencia.getAcao().toUpperCase());

			// data inicio de ocorrencia
			if (ocorrencia.getDataInicio() == null) {
				ps.setNull(6, Types.DATE);
			} else {
				ps.setDate(6, Date.valueOf(ocorrencia.getDataInicio()));
			}

			ps.setString(7, ocorrencia.getObservacao().toUpperCase());

			// data fim de ocorrencia
			if (ocorrencia.getDataFim() == null) {
				ps.setNull(8, Types.DATE);
			} else {
				ps.setDate(8, Date.valueOf(ocorrencia.getDataFim()));
			}

			ps.setString(9, ocorrencia.getCausa().toUpperCase());
			ps.setString(10, ocorrencia.getStatus().toUpperCase());

			ps.setString(11, ocorrencia.getHrInicio().toUpperCase());
			ps.setString(12, ocorrencia.getHrFim().toUpperCase());
			ps.setString(13, ocorrencia.getCausaExterna_SN().toUpperCase());
			ps.setLong(14, idocorrencia);

			ps.execute();

		} catch (SQLException e) {
			throw new RepositoryException("Falha ao Gravar o Ocorrencia. ", e);

		} finally {

			closeResources(cn, ps, null);

		}

	}

// criar metodo pesquisar por nome sistema
	// findByname
	public List<Ocorrencia> findByname(String nomeSistema) throws RepositoryException {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cn = getConnection();
			// ps = cn.prepareStatement("SELECT FUNC_NAME, FUNC_CODE,
			// FUNC_DATE,FUNC_RMNT_VAL FROM IMPACTA.TAB_FUNC where FUNC_NAME like ? order by
			// FUNC_CODE ");

			ps = cn.prepareStatement(
					"SELECT o.ID_OCORRENCIA,o.CHAMADO,s.NM_SISTEMA,o.DATA,o.ID_SISTEMA,o.SINTOMA,o.INICIO,o.FIM,o.IMPACTO,o.CAUSA,o.ACAO,o.OBSERVACOES,HRINICIO,HRFIM,o.STATUS, o.CAUSA_EXTERNA_SN  FROM monitoracao_ambientes.ocorrencias o LEFT JOIN monitoracao_ambientes.sistemas s on (o.id_sistema = s.id_sistema) WHERE  s.NM_SISTEMA like ? order by o.DATA DESC");

			ps.setString(1, "%" + nomeSistema + "%");

			rs = ps.executeQuery();

			/* Criar objeto lista vazio */
			List<Ocorrencia> lista = new ArrayList<>();

			while (rs.next()) {
				/* retorna dados e preenche a lista */
				Ocorrencia ocorrencia = new Ocorrencia();

				// recuperando campos do registro
				ocorrencia.setIdOcorrencia(rs.getInt("ID_OCORRENCIA"));
				ocorrencia.setSintoma(rs.getString("SINTOMA"));

				ocorrencia.setChamado(rs.getString("CHAMADO"));
				ocorrencia.setSistema(rs.getString("NM_SISTEMA"));

				// trata campos tipo data
				Date admissao = rs.getDate("DATA");
				if (admissao != null) {
					ocorrencia.setDataCadastro(admissao.toLocalDate());
				}

				// trata campos tipo data
				Date dataInicio = rs.getDate("INICIO");
				if (dataInicio != null) {
					ocorrencia.setDataInicio(dataInicio.toLocalDate());
				}

				// trata campos tipo data
				Date dataFim = rs.getDate("FIM");

				if (dataFim != null) {
					ocorrencia.setDataFim(dataFim.toLocalDate());
				}
				ocorrencia.setImpacto(rs.getString("IMPACTO"));
				ocorrencia.setCausa(rs.getString("CAUSA"));

				ocorrencia.setAcao(rs.getString("ACAO"));
				ocorrencia.setObservacao(rs.getString("OBSERVACOES"));
				ocorrencia.setStatus(rs.getString("STATUS"));

				ocorrencia.setHrInicio(rs.getString("HRINICIO"));
				ocorrencia.setHrFim(rs.getString("HRFIM"));

				ocorrencia.setCausaExterna_SN("CAUSA_EXTERNA_SN");

				// ocorrencia.setSalario(rs.getDouble("FUNC_RMNT_VAL"));

				lista.add(ocorrencia);
			}

			/* retorna lista */
			return lista;

		} catch (SQLException e) {
			throw new RepositoryException("Falha ao pesquisar Ocorrencia. ", e);

		} finally {

			closeResources(cn, ps, rs);

		}

	}

	// criar metodo pesquisar por nome sistema
	// findByname
	public List<Ocorrencia> findByint(int ID_OCORRENCIA) throws RepositoryException {

		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cn = getConnection();
			// ps = cn.prepareStatement("SELECT FUNC_NAME, FUNC_CODE,
			// FUNC_DATE,FUNC_RMNT_VAL FROM IMPACTA.TAB_FUNC where FUNC_NAME like ? order by
			// FUNC_CODE ");

			ps = cn.prepareStatement(
					"SELECT o.ID_OCORRENCIA,o.CHAMADO,s.NM_SISTEMA,ADDDATE(o.DATA, INTERVAL 1 DAY) as DATA,o.ID_SISTEMA,o.SINTOMA,ADDDATE(o.INICIO, INTERVAL 1 DAY) as INICIO,ADDDATE(o.FIM, INTERVAL 1 DAY) as FIM,o.IMPACTO,o.CAUSA,o.ACAO,o.OBSERVACOES,HRINICIO,HRFIM,o.STATUS, o.CAUSA_EXTERNA_SN  FROM monitoracao_ambientes.ocorrencias o LEFT JOIN monitoracao_ambientes.sistemas s on (o.id_sistema = s.id_sistema) WHERE  o.ID_OCORRENCIA = ? order by ADDDATE(o.DATA, INTERVAL 1 DAY) DESC");

			ps.setLong(1, ID_OCORRENCIA);

			rs = ps.executeQuery();

			/* Criar objeto listaItemItem vazio */
			List<Ocorrencia> listaItem = new ArrayList<>();

			while (rs.next()) {
				/* retorna dados e preenche a listaItemItem */
				Ocorrencia ocorrencia = new Ocorrencia();

				// recuperando campos do registro
				ocorrencia.setIdOcorrencia(rs.getInt("ID_OCORRENCIA"));
				ocorrencia.setSintoma(rs.getString("SINTOMA"));

				ocorrencia.setChamado(rs.getString("CHAMADO"));
				ocorrencia.setSistema(rs.getString("NM_SISTEMA"));

				// trata campos tipo data
				Date admissao = rs.getDate("DATA");
				if (admissao != null) {
					ocorrencia.setDataCadastro(admissao.toLocalDate());
				}

				Date dataInicio = rs.getDate("INICIO");
				if (dataInicio != null) {
					ocorrencia.setDataInicio(dataInicio.toLocalDate());
				}

				// trata campos tipo data
				Date dataFim = rs.getDate("FIM");
				if (dataFim != null) {
					ocorrencia.setDataFim(dataFim.toLocalDate());
				}

				ocorrencia.setImpacto(rs.getString("IMPACTO"));
				ocorrencia.setCausa(rs.getString("CAUSA"));

				ocorrencia.setAcao(rs.getString("ACAO"));
				ocorrencia.setObservacao(rs.getString("OBSERVACOES"));
				ocorrencia.setStatus(rs.getString("STATUS"));

				ocorrencia.setHrInicio(rs.getString("HRINICIO"));
				ocorrencia.setHrFim(rs.getString("HRFIM"));

				ocorrencia.setCausaExterna_SN(rs.getString("CAUSA_EXTERNA_SN"));

				// ocorrencia.setSalario(rs.getDouble("FUNC_RMNT_VAL"));

				listaItem.add(ocorrencia);
			}

			/* retorna listaItemItem */
			return listaItem;

		} catch (SQLException e) {
			throw new RepositoryException("Falha ao pesquisar Ocorrencia. ", e);

		} finally {

			closeResources(cn, ps, rs);

		}

	}

	// criar metodo pesquisar por nome sistema
	// findByname
	public List<Ocorrencia> findAll(int i) throws RepositoryException {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			cn = getConnection();
			// ps = cn.prepareStatement("SELECT FUNC_NAME, FUNC_CODE,
			// FUNC_DATE,FUNC_RMNT_VAL FROM IMPACTA.TAB_FUNC where FUNC_NAME like ? order by
			// FUNC_CODE ");

			ps = cn.prepareStatement(
					"SELECT o.ID_OCORRENCIA,o.CHAMADO,s.NM_SISTEMA,o.DATA,o.ID_SISTEMA,o.SINTOMA,o.INICIO,o.FIM,o.IMPACTO,o.CAUSA,o.ACAO,o.OBSERVACOES,HRINICIO,HRFIM,o.STATUS, o.CAUSA_EXTERNA_SN  FROM monitoracao_ambientes.ocorrencias o LEFT JOIN monitoracao_ambientes.sistemas s on (o.id_sistema = s.id_sistema) WHERE  o.ID_OCORRENCIA like ? order by o.DATA DESC");

			ps.setInt(1, i); /// ID_OCORRENCIA );

			rs = ps.executeQuery();

			/* Criar objeto listaItem vazio */
			List<Ocorrencia> listaItem = new ArrayList<>();

			while (rs.next()) {
				/* retorna dados e preenche a listaItem */
				Ocorrencia ocorrencia = new Ocorrencia();

				// recuperando campos do registro
				ocorrencia.setIdOcorrencia(rs.getInt("ID_OCORRENCIA"));
				ocorrencia.setSintoma(rs.getString("SINTOMA"));

				ocorrencia.setChamado(rs.getString("CHAMADO"));
				ocorrencia.setSistema(rs.getString("NM_SISTEMA"));

				// trata campos tipo data
				Date admissao = rs.getDate("DATA");
				if (admissao != null) {
					ocorrencia.setDataCadastro(admissao.toLocalDate());
				}

				// trata campos tipo data
				Date dataInicio = rs.getDate("INICIO");
				if (dataInicio != null) {
					ocorrencia.setDataInicio(dataInicio.toLocalDate());
				}

				// trata campos tipo data
				Date dataFim = rs.getDate("FIM");

				if (dataFim != null) {
					ocorrencia.setDataFim(dataFim.toLocalDate());
				}
				ocorrencia.setImpacto(rs.getString("IMPACTO"));
				ocorrencia.setCausa(rs.getString("CAUSA"));

				ocorrencia.setAcao(rs.getString("ACAO"));
				ocorrencia.setObservacao(rs.getString("OBSERVACOES"));
				ocorrencia.setStatus(rs.getString("STATUS"));

				ocorrencia.setHrInicio(rs.getString("HRINICIO"));
				ocorrencia.setHrFim(rs.getString("HRFIM"));

				ocorrencia.setCausaExterna_SN("CAUSA_EXTERNA_SN");

				// ocorrencia.setSalario(rs.getDouble("FUNC_RMNT_VAL"));

				listaItem.add(ocorrencia);
			}

			/* retorna listaItem */
			return listaItem;

		} catch (SQLException e) {
			throw new RepositoryException("Falha ao pesquisar Ocorrencia por item. ", e);

		} finally {

			closeResources(cn, ps, rs);

		}

	}

}

// delete
// public void delete(int matricula) {
//
// try {
// Connection cn = getConnection();
// PreparedStatement ps = cn.prepareStatement("DELETE FROM TAB_FUNC WHERE
// func_code=? ");
//
// ps.setInt(1, matricula);
// ps.execute();
//
// ps.close();
// cn.close();
//
// } catch (SQLException e) {
// e.printStackTrace();
//
// }

// }