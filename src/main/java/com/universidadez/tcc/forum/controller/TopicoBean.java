package com.universidadez.tcc.forum.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.universidadez.tcc.forum.model.Sala;
import com.universidadez.tcc.forum.model.Topico;
import com.universidadez.tcc.forum.repository.TopicoRepository;
import com.universidadez.tcc.login.model.Usuario;
import com.universidadez.tcc.util.JpaUtil;


@ManagedBean
@SessionScoped
public class TopicoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(TopicoBean.class);
	private Topico topico = new Topico();
	private List<Topico> topicos;
	private Topico topicoSelecionado;
	private Sala sala;
	private Usuario usuario;
	
	/**
	 * 
	 */
	public TopicoBean() {
	}

	
	public void inserir() {
		logger.info("iniciou o método cadastrar.");
		EntityManager em = JpaUtil.getEntityManager();
		TopicoRepository tr = new TopicoRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			topico.setUsuario(usuario);
			topico.setSala(sala);
			tr.insere(topico);
			FacesMessage mensagem = new FacesMessage("Topico " + topico.getTitulo() + " cadastrado com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.topico = new Topico();

		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problema ao tentar cadastrar novo tópico.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	
	public void listaAtivas() {
		logger.info("Entrou no método listaAtivas.");
		EntityManager em = JpaUtil.getEntityManager();
		TopicoRepository tr =  new TopicoRepository(em);
		this.topicos = tr.lista(sala);
	}

	
		public void alterar() {
		logger.info("Entrou no método alterar.");
		EntityManager em = JpaUtil.getEntityManager();
		TopicoRepository tr =  new TopicoRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();
		
		
		try {
			
			tr.altera(topicoSelecionado);
			FacesMessage mensagem = new FacesMessage("Topico " + topico.getTitulo() + " Alterado com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			

		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problema ao tentar Alterar o tópico.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	
	public void desativa() {
		logger.info("Entrou no método desativa.");
		EntityManager em = JpaUtil.getEntityManager();
		TopicoRepository tr = new TopicoRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			tr.desativa(topicoSelecionado);
			FacesMessage mensagem = new FacesMessage("Topico " + topicoSelecionado.getTitulo() + " desativado com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);

		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problema ao tentar desativar o tópico.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	/**
	 * @return the topico
	 */
	public Topico getTopico() {
		return topico;
	}

	/**
	 * @param topico
	 *            the topico to set
	 */
	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	/**
	 * @return the topicos
	 */
	public List<Topico> getTopicos() {
		return topicos;
	}

	/**
	 * @param topicos
	 *            the topicos to set
	 */
	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

	/**
	 * @return the topicoSelecionado
	 */
	public Topico getTopicoSelecionado() {
		return topicoSelecionado;
	}

	


	/**
	 * @param topicoSelecionado
	 *            the topicoSelecionado to set
	 */
	public void setTopicoSelecionado(Topico topicoSelecionado) {
		this.topicoSelecionado = topicoSelecionado;
	}


	public Sala getSala() {
		return sala;
	}


	public void setSala(Sala sala) {
		this.sala = sala;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
