package com.universidadez.tcc.forum.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.universidadez.tcc.forum.model.Forum;
import com.universidadez.tcc.forum.model.Sala;
import com.universidadez.tcc.forum.repository.SalaRepository;
import com.universidadez.tcc.util.JpaUtil;



@ManagedBean
@SessionScoped
public class SalaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(SalaBean.class);
	private Sala sala = new Sala();
	private List<Sala> salas;
	private Sala salaSelecionada;
	private Forum forum;

	/**
	 * 
	 */
	public SalaBean() {
	}


		public void inserir() {
		logger.info("Entrou no método inserir.");
		EntityManager em = JpaUtil.getEntityManager();
		SalaRepository sr = new SalaRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			sala.setForum(forum);
			sr.insere(sala);
			FacesMessage mensagem = new FacesMessage("Sala " + sala.getNome() + " cadastrada com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.sala = new Sala();

		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problema ao tentar cadastrar nova sala.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	
		public void listaAtivas() {
		logger.info("Entrou no método listaAtivas.");
		EntityManager em = JpaUtil.getEntityManager();
		SalaRepository sr = new SalaRepository(em);
		this.salas = sr.lista(forum);
	}

	
	public void alterar() {
		logger.info("Entrou no método alterar.");
		EntityManager em = JpaUtil.getEntityManager();
		SalaRepository sr = new SalaRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			sr.altera(salaSelecionada);
			FacesMessage mensagem = new FacesMessage("Sala " + salaSelecionada.getNome() + " alterada com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problema ao tentar alterar a sala.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	
		public void desativa() {
		logger.info("Entrou no método desativa.");
		EntityManager em = JpaUtil.getEntityManager();
		SalaRepository sr = new SalaRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			sr.desativa(salaSelecionada);
			FacesMessage mensagem = new FacesMessage("Sala " + salaSelecionada.getNome() + " desativada com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);

		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problema ao tentar desativar a sala.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	/**
	 * @return the sala
	 */
	public Sala getSala() {
		return sala;
	}

	/**
	 * @param sala the sala to set
	 */
	public void setSala(Sala sala) {
		this.sala = sala;
	}

	/**
	 * @return the salas
	 */
	public List<Sala> getSalas() {
		return salas;
	}

	/**
	 * @param salas the salas to set
	 */
	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	/**
	 * @return the salaSelecionada
	 */
	public Sala getSalaSelecionada() {
		return salaSelecionada;
	}

	/**
	 * @param salaSelecionada the salaSelecionada to set
	 */
	public void setSalaSelecionada(Sala salaSelecionada) {
		this.salaSelecionada = salaSelecionada;
	}


	public Forum getForum() {
		return forum;
	}


	public void setForum(Forum forum) {
		this.forum = forum;
	}

	
	

}
