package br.com.cursojava.projetofinal.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Ocorrencia {

	// private static final DecimalFormat MASK_SALARIO = new DecimalFormat("#,##0.00");
	// criando um maskara para formatar o retorno da data de admissao, tipo
	// dd/mm/yyyy - brasil
	private static final DateTimeFormatter MASK_DATACADASTRO = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DateTimeFormatter MASK_DATAINICIO   = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DateTimeFormatter MASK_DATAFIM      = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	//private static final DateTimeFormatter MASK_HRINICIO   = DateTimeFormatter.ofPattern("%H:%i:%s");
	//private static final DateTimeFormatter MASK_HRFIM      = DateTimeFormatter.ofPattern("%H:%i:%s");
	
	
	
	
	// criando um maskara para formatar o retorno da data de admissao
	// private static final DateTimeFormatter MASK_ADMISSAO =
	// DateTimeFormatter.ISO_DATE;
	private String     idParametro;
	private int        idOcorrencia;
	private String     sintoma;
	private String     chamado;
	private int        codigoSistema;
	private String     Sistema;
	
	private LocalDate  dataCadastro;
	
	
	private LocalDate  dataInicio;
	
	
	
	private LocalDate  dataFim; 
	
	
	private String     impacto; 
	private String     causa ;
	private String     acao; 
	private String     observacao; 
	private String     status ;
	private String     hrInicio;
	private String     hrFim; 
	private String     causaexterna_sn;
	private SimpleBooleanProperty selected;
	

	
	
	 public boolean isSelected() {
	        return selected.get();
	    }

	    public SimpleBooleanProperty selectedProperty() {
	        return selected;
	    }

	    public void setSelected(boolean selected) {
	        this.selected.set(selected);
	    }

	
	
	public int getIdOcorrencia() {
		return idOcorrencia;
	}

	public void setIdOcorrencia(int idOcorrencia) {
		this.idOcorrencia = idOcorrencia;
	}

//
//	public String getDescriaoOcorrencia() {
//		return descricaoOcorrencia;
//	}
//
//	public void setDescriaoOcorrencia(String descricaoOcorrencia) {
//		this.descricaoOcorrencia = descricaoOcorrencia;
//	}
//	
	public String getSintoma() {
		return sintoma;
	}


	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}

	
	public String getChamado() {
		return chamado;
	}

	public void setChamado(String chamado) {
		this.chamado = chamado;
	}
	

	public int getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(int codigoSistema) {
		this.codigoSistema = codigoSistema;
	}



	public String getSistema() {
		return Sistema;
	}

	public void setSistema(String Sistema) {
		this.Sistema = Sistema;
	}

	
	
	
	
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDataCadastroFormatada() {
		if (dataCadastro != null) {
			return MASK_DATACADASTRO.format(dataCadastro);
		} else {
			return "";
		}
	}
	
	
	
	
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}


	public void setDataInicio(LocalDate inicio) {
		this.dataInicio = inicio;
	}

	public String getDataInicioFormatada() {
		if (dataInicio != null) {
			return MASK_DATAINICIO.format(dataInicio);
		} else {
			return "";
		}
	}
	

	public LocalDate getDataFim() {
		return dataFim;
	}


	public void setDataFim(LocalDate datafim) {
		this.dataFim = datafim;
	}

	public String getDataFimFormatada() {
		if (dataFim != null) {
			return MASK_DATAFIM.format(dataFim);
		} else {
			return "";
		}
	}

	
	
	
	
	
	
	
	


	

	

	public String getImpacto() {
		return impacto;
	}


	public void setImpacto(String impacto) {
		this.impacto = impacto;
	}


	public String getCausa() {
		return causa;
	}


	public void setCausa(String causa) {
		this.causa = causa;
	}


	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;

	
	
	
	
	
	
	
	}
	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
	
	public String getHrInicio() {
		return hrInicio;
	}

	public void setHrInicio(String hrinicio) {
		this.hrInicio = hrinicio;
	}

	
 
	public String getHrFim() {
		return hrFim;
	}

	public void setHrFim(String hrfim) {
		this.hrFim = hrfim;
	}

	
	
  // causaexterna_sn
	
	 
	public String getCausaExterna_SN() {
		return causaexterna_sn;
	}

	public void setCausaExterna_SN(String causaexterna_sn) {
		this.causaexterna_sn = causaexterna_sn;
	}

	
	
	
	
	
	
	
//	public double getSalario() {
//		return salario;
//	}
//
//	public void setSalario(double salario) {
//		this.salario = salario;
//	}
//
//	public String getSalarioFormatado() {
//		return MASK_SALARIO.format(salario);
//	}


	

	
	


	@Override
	public String toString() {
		// return idOcorrencia + " - " + chamado + " - " ; //+ salario;
		
		return idOcorrencia    + " - " +  sintoma + " - " +  chamado + " - " + codigoSistema + " - " + dataCadastro + " - " + sintoma + " - " + dataInicio + " - " + hrInicio + " - " + dataFim + " - " + hrFim+ " - " + impacto + " - " +  causa + " - " +  acao + " - " + observacao + " - " + status + " - " ;
		

	}

 

}
