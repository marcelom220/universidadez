package com.universidadez.tcc.universidade.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;


import com.universidadez.tcc.universidade.model.Estados;
import com.universidadez.tcc.universidade.model.Universidade;
import com.universidadez.tcc.universidade.repository.UniversidadeRepository;
import com.universidadez.tcc.util.JpaUtil;

@ManagedBean
@SessionScoped
public class UniversidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(UniversidadeBean.class);

	private Universidade universidade;
	private List<Universidade> universidades;

	/**
	 * 
	 */
	public UniversidadeBean() {
		this.universidade = new Universidade();
	}
	public Estados[] getEstados() {
		return Estados.values();
	}

	

	/**
	 */
	public void cadastrar() {
		logger.info("iniciou o método cadastrar.");
		EntityManager em = JpaUtil.getEntityManager();
		UniversidadeRepository ur = new UniversidadeRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			ur.insere(universidade);
			FacesMessage mensagem = new FacesMessage("Universidade " + universidade.getNome() + " cadastrada com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.universidade = new Universidade();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problemas para cadastrar a universidade.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	/**
	 */
	public void lista() {
		logger.info("iniciou o método lista.");
		EntityManager em = JpaUtil.getEntityManager();
		UniversidadeRepository ur = new UniversidadeRepository(em);
		this.universidades = ur.lista();
	}

	/**
	 */
	public void alterar() {
		logger.info("iniciou o método alterar.");
		EntityManager em = JpaUtil.getEntityManager();
		UniversidadeRepository ur = new UniversidadeRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			ur.alterar(universidade);
			FacesMessage mensagem = new FacesMessage("Universidade " + universidade.getNome() + " Alterada com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.universidade = new Universidade();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problemas para alterar a universidade.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	public void remover() {
		logger.info("iniciou o método remover.");
		EntityManager em = JpaUtil.getEntityManager();
		UniversidadeRepository ur = new UniversidadeRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			ur.excluir(universidade);
			FacesMessage mensagem = new FacesMessage("Universidade " + universidade.getNome() + " excluida com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.universidade = new Universidade();
			lista();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problemas para remover a universidade.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}




	public Universidade getUniversidade() {
		return universidade;
	}



	public void setUniversidade(Universidade universidade) {
		this.universidade = universidade;
	}



	public List<Universidade> getUniversidades() {
		return universidades;
	}



	public void setUniversidades(List<Universidade> universidades) {
		this.universidades = universidades;
	}
	
	
}
