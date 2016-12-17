package com.universidadez.tcc.universidade.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;


import com.universidadez.tcc.universidade.model.Campus;
import com.universidadez.tcc.universidade.repository.CampusRepository;

import com.universidadez.tcc.util.JpaUtil;
@Named
@ManagedBean
@SessionScoped
public class CampusBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CampusBean.class);

	private Campus campus;
	private List<Campus> campusc;
	
	
	
	/**
	 * 
	 */
	
	@PostConstruct
	public void init() {
	    campus = new Campus();
	    
	   
	}

	
	
	
	
	public void cadastrar() {
		logger.info("iniciou o método cadastrar.");
		EntityManager em = JpaUtil.getEntityManager();
		CampusRepository cr = new CampusRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			cr.insere(campus);
			FacesMessage mensagem = new FacesMessage("Campus " + campus.getNome() + " cadastrado com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.campus = new Campus();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problemas para cadastrar o campus.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	/**
	 */
	public void lista() {
		logger.info("iniciou o método lista.");
		EntityManager em = JpaUtil.getEntityManager();
		CampusRepository cr = new CampusRepository(em);
		this.campusc = cr.lista();
	}

	/**
	 */
	public void alterar() {
		logger.info("iniciou o método alterar.");
		EntityManager em = JpaUtil.getEntityManager();
		CampusRepository cr = new CampusRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			cr.alterar(campus);
			FacesMessage mensagem = new FacesMessage("Campus " + campus.getNome() + " Alterado com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.campus = new Campus();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problemas para alterar o campus.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	public void remover() {
		logger.info("iniciou o método remover.");
		EntityManager em = JpaUtil.getEntityManager();
		CampusRepository cr = new CampusRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			cr.excluir(campus);
			FacesMessage mensagem = new FacesMessage("Campus " + campus.getNome() + " excluido com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.campus = new Campus();
			lista();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problemas para remover o campus.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}


	public Campus getCampus() {
		return campus;
	}



	public void setCampus(Campus campus) {
		this.campus = campus;
	}



	public List<Campus> getCampusc() {
		return campusc;
	}



	public void setCampusc(List<Campus> campusc) {
		this.campusc = campusc;
	}


	


}
