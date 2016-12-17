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
import com.universidadez.tcc.forum.repository.ForumRepository;
import com.universidadez.tcc.util.JpaUtil;


@ManagedBean
@SessionScoped
public class ForumBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ForumBean.class);

	private Forum forum = new Forum();
	private List<Forum> forums;
	private Forum forumSelecionado;

	/**
	 * 
	 */
	public ForumBean() {
	}

	
	public void inserir() {
		logger.info("Entrou no método inserir.");
		EntityManager em = JpaUtil.getEntityManager();
		ForumRepository fr = new ForumRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			fr.insere(forum);
			FacesMessage mensagem = new FacesMessage("Forum " + forum.getNome() + " cadastrado com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.forum = new Forum();

		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problema ao tentar cadastrar novo forum.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	
	public void listaAtivas() {
		logger.info("Entrou no método listaAtivas.");
		EntityManager em = JpaUtil.getEntityManager();
		ForumRepository fr = new ForumRepository(em);
		this.forums = fr.lista();
	}

	
	public void buscaPorId() {
		logger.info("Entrou no método buscaPorId.");
		EntityManager em = JpaUtil.getEntityManager();
		ForumRepository fr = new ForumRepository(em);
		this.forum = fr.buscaPorId(forumSelecionado.getId());
	}

	
	public void alterar() {
		logger.info("Entrou no método alterar.");
		EntityManager em = JpaUtil.getEntityManager();
		ForumRepository fr = new ForumRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();
			try {
				fr.altera(forumSelecionado);
				FacesMessage mensagem = new FacesMessage("Secao " + forumSelecionado.getNome() + " alterado com sucesso.");
				mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
				context.addMessage(null, mensagem);
	    
			} catch (Exception e) {
				FacesMessage mensagem = new FacesMessage("Problemas para alterar o forum.");
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null, mensagem);
	}
	}

	
	public void desativa() {
		logger.info("Entrou no método desativa.");
		EntityManager em = JpaUtil.getEntityManager();
		ForumRepository fr = new ForumRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			fr.desativa(forumSelecionado);
			FacesMessage mensagem = new FacesMessage(
					"Forum " + forumSelecionado.getNome() + " desativado com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			

		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problema ao tentar desativar o forum.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}


	public Forum getForum() {
		return forum;
	}


	public void setForum(Forum forum) {
		this.forum = forum;
	}


	public List<Forum> getForums() {
		return forums;
	}


	public void setForums(List<Forum> forums) {
		this.forums = forums;
	}


	public Forum getForumSelecionado() {
		return forumSelecionado;
	}


	public void setForumSelecionado(Forum forumSelecionado) {
		this.forumSelecionado = forumSelecionado;
	}

	
}
