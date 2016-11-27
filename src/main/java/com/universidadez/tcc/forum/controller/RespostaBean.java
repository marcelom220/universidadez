package com.universidadez.tcc.forum.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import com.universidadez.tcc.forum.model.Resposta;
import com.universidadez.tcc.forum.repository.RespostaRepository;
import com.universidadez.tcc.util.JpaUtil;



@ManagedBean
@SessionScoped
public class RespostaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Resposta resposta = new Resposta();
	private List<Resposta> respostas;
	private Resposta respostaSelecionada;

	/**
	 * 
	 */
	public RespostaBean() {
	}

	
		public void inserir() {
		EntityManager em = JpaUtil.getEntityManager();
		RespostaRepository rr = new RespostaRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
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
		EntityManager em = JpaUtil.getEntityManager();
		RespostaRepository rr =  new RespostaRepository(em);
		this.respostas = rr.lista();
	}

	
		public void alterar() {
		EntityManager em = JpaUtil.getEntityManager();
		RespostaRepository rr =  new RespostaRepository(em);
		rr.altera(respostaSelecionada);
	}

	
		public void desativa() {
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

}
