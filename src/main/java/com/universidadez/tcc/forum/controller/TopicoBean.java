package com.universidadez.tcc.forum.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import com.universidadez.tcc.forum.model.Topico;
import com.universidadez.tcc.forum.repository.TopicoRepository;
import com.universidadez.tcc.util.JpaUtil;


@ManagedBean
@SessionScoped
public class TopicoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Topico topico = new Topico();
	private List<Topico> topicos;
	private Topico topicoSelecionado;

	/**
	 * 
	 */
	public TopicoBean() {
	}

	
	public void inserir() {
		EntityManager em = JpaUtil.getEntityManager();
		TopicoRepository tr = new TopicoRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
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
		EntityManager em = JpaUtil.getEntityManager();
		TopicoRepository tr =  new TopicoRepository(em);
		this.topicos = tr.lista();
	}

	
		public void alterar() {
		EntityManager em = JpaUtil.getEntityManager();
		TopicoRepository tr =  new TopicoRepository(em);
		tr.altera(topicoSelecionado);
	}

	
	public void desativa() {
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

}
