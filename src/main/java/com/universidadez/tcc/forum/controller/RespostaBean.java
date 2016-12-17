package com.universidadez.tcc.forum.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.universidadez.tcc.forum.model.Resposta;
import com.universidadez.tcc.forum.model.Topico;
import com.universidadez.tcc.forum.repository.RespostaRepository;
import com.universidadez.tcc.login.model.Usuario;
import com.universidadez.tcc.util.JpaUtil;



@ManagedBean
@SessionScoped
public class RespostaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(RespostaBean.class);
	private Resposta resposta = new Resposta();
	private List<Resposta> respostas;
	private Resposta respostaSelecionada;
	private Topico topico;
	private Usuario usuario;

	/**
	 * 
	 */
	public RespostaBean() {
	}

	
		public void inserir() {
		logger.info("Entrou no método inserir.");
		EntityManager em = JpaUtil.getEntityManager();
		RespostaRepository rr = new RespostaRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			resposta.setUsuario(usuario);
			resposta.setTopico(topico);
			rr.insere(resposta);
			FacesMessage mensagem = new FacesMessage("Resposta cadastrada com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.resposta = new Resposta();

		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problema ao tentar cadastrar a resposta.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	
		public void listaAtivas() {
		logger.info("Entrou no método listaAtivas.");
		EntityManager em = JpaUtil.getEntityManager();
		RespostaRepository rr =  new RespostaRepository(em);
		this.respostas = rr.lista(topico);
		
	}

	
		public void alterar() {
		logger.info("Entrou no método alterar.");
		EntityManager em = JpaUtil.getEntityManager();
		RespostaRepository rr =  new RespostaRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			rr.altera(respostaSelecionada);
			FacesMessage mensagem = new FacesMessage(
					"Resposta " + " alterada com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problema ao tentar alterar a resposta.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
			
	}

	
		public void desativa() {
		logger.info("Entrou no método desativa.");
		EntityManager em = JpaUtil.getEntityManager();
		RespostaRepository rr = new RespostaRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			rr.desativa(respostaSelecionada);
			FacesMessage mensagem = new FacesMessage("Resposta desativada com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);

		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problema ao tentar desativar a resposta.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	/**
	 * @return the resposta
	 */
	public Resposta getResposta() {
		return resposta;
	}

	/**
	 * @param resposta
	 *            the resposta to set
	 */
	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	/**
	 * @return the respostas
	 */
	public List<Resposta> getRespostas() {
		return respostas;
	}

	/**
	 * @param respostas
	 *            the respostas to set
	 */
	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	/**
	 * @return the respostaSelecionado
	 */
	public Resposta getRespostaSelecionado() {
		return respostaSelecionada;
	}

	/**
	 * @param respostaSelecionado
	 *            the respostaSelecionado to set
	 */
	public void setRespostaSelecionado(Resposta respostaSelecionado) {
		this.respostaSelecionada = respostaSelecionado;
	}


	public Resposta getRespostaSelecionada() {
		return respostaSelecionada;
	}


	public void setRespostaSelecionada(Resposta respostaSelecionada) {
		this.respostaSelecionada = respostaSelecionada;
	}


	public Topico getTopico() {
		return topico;
	}


	public void setTopico(Topico topico) {
		this.topico = topico;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
