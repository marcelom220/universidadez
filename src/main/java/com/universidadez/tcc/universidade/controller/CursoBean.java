package com.universidadez.tcc.universidade.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.universidadez.tcc.universidade.model.Curso;
import com.universidadez.tcc.universidade.repository.CursoRepository;
import com.universidadez.tcc.util.JpaUtil;

@ManagedBean
@SessionScoped
public class CursoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CursoBean.class);

	private Curso curso;
	private List<Curso> cursos;

	/**
	 * 
	 */
	public CursoBean() {
		this.curso = new Curso();
	}

	

	/**
	 */
	public void cadastrar() {
		logger.info("iniciou o método cadastrar.");
		EntityManager em = JpaUtil.getEntityManager();
		CursoRepository cr = new CursoRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			cr.insere(curso);
			FacesMessage mensagem = new FacesMessage("Curso " + curso.getNome() + " cadastrado com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.curso = new Curso();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problemas para cadastrar o curso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	/**
	 */
	public void lista() {
		logger.info("iniciou o método lista.");
		EntityManager em = JpaUtil.getEntityManager();
		CursoRepository cr = new CursoRepository(em);
		this.cursos = cr.lista();
	}

	/**
	 */
	public void alterar() {
		logger.info("iniciou o método alterar.");
		EntityManager em = JpaUtil.getEntityManager();
		CursoRepository cr = new CursoRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			cr.alterar(curso);
			FacesMessage mensagem = new FacesMessage("Curso " + curso.getNome() + " Alterado com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.curso = new Curso();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problemas para alterar o curso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	public void remover() {
		logger.info("iniciou o método remover.");
		EntityManager em = JpaUtil.getEntityManager();
		CursoRepository cr = new CursoRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			cr.excluir(curso);
			FacesMessage mensagem = new FacesMessage("Curso " + curso.getNome() + " excluido com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.curso = new Curso();
			lista();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problemas para remover o curso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}


	public Curso getCurso() {
		return curso;
	}



	public void setCurso(Curso curso) {
		this.curso = curso;
	}



	public List<Curso> getCursos() {
		return cursos;
	}



	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}


}
