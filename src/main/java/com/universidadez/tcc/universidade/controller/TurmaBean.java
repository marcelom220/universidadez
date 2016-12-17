package com.universidadez.tcc.universidade.controller;

import java.io.Serializable;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import com.universidadez.tcc.login.model.Usuario;

import com.universidadez.tcc.universidade.model.Turma;
import com.universidadez.tcc.universidade.repository.TurmaRepository;
import com.universidadez.tcc.util.JpaUtil;

@ManagedBean
@SessionScoped
public class TurmaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(TurmaBean.class);

	private Turma turma;
	private List<Turma> turmas;
	
	

	
	
	
	public TurmaBean() {
		this.turma = new Turma();
		
	}
	
	

	/**
	 */
	public void cadastrar() {
		logger.info("iniciou o método cadastrar.");
		EntityManager em = JpaUtil.getEntityManager();
		TurmaRepository tr = new TurmaRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			tr.insere(turma);
			FacesMessage mensagem = new FacesMessage("Turma " + turma.getNumero() + " cadastrada com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.turma = new Turma();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problemas para cadastrar a Turma.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	
	
	
	public void lista() {
		logger.info("iniciou o método lista.");
		EntityManager em = JpaUtil.getEntityManager();
		TurmaRepository tr = new TurmaRepository(em);
		this.turmas = tr.lista();
		
		
	}
   
	
	public void alterar() {
		logger.info("iniciou o método alterar.");
		EntityManager em = JpaUtil.getEntityManager();
		TurmaRepository tr = new TurmaRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			tr.alterar(turma);
			FacesMessage mensagem = new FacesMessage("Turma " + turma.getNumero() + " Alterada com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.turma = new Turma();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problemas para alterar a Turma.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	public void remover() {
		logger.info("iniciou o método remover.");
		EntityManager em = JpaUtil.getEntityManager();
		TurmaRepository tr = new TurmaRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			tr.excluir(turma);
			FacesMessage mensagem = new FacesMessage("Turma " + turma.getNumero() + " excluida com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.turma = new Turma();
			lista();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problemas para remover a turma.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}



	public Turma getTurma() {
		return turma;
	}



	public void setTurma(Turma turma) {
		this.turma = turma;
	}



	public List<Turma> getTurmas() {
		return turmas;
	}



	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}



	

	

	


}
