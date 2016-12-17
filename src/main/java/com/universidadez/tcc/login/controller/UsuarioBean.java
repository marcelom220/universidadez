package com.universidadez.tcc.login.controller;


import java.io.IOException;
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
import org.primefaces.model.DualListModel;

import com.universidadez.tcc.login.model.TipoUsuario;
import com.universidadez.tcc.login.model.Usuario;
import com.universidadez.tcc.login.repository.UsuarioRepository;
import com.universidadez.tcc.util.JpaUtil;

/**
 */
@Named
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(UsuarioBean.class);

	private Usuario usuario ;
	private List<Usuario> usuarios;
	private List<Usuario> professores;
	private List<Usuario> alunos;
	
	private Usuario usuarioSelecionado;
	private DualListModel<Usuario> model;
	
	

	/**
	 */
	public TipoUsuario[] getTiposUsuario() {
		return TipoUsuario.values();
	}

	@PostConstruct
	public void init() {
	    usuario = new Usuario();
	}

	
	
	
	
	public void insere() {
		logger.info("iniciou o método cadastrar.");
		EntityManager em = JpaUtil.getEntityManager();
		UsuarioRepository ur = new UsuarioRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			
			ur.insere(usuario);
			FacesMessage mensagem = new FacesMessage("Usuário " + usuario.getNome() + " cadastrado com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.usuario = new Usuario();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problemas para cadastrar o usuario.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	/**
	 */
	public void listaAtivos() {
		logger.info("iniciou o método listaAtivos.");
		EntityManager em = JpaUtil.getEntityManager();
		UsuarioRepository ur = new UsuarioRepository(em);
		this.usuarios = ur.listaAtivos();
	}
	
	public void listaProfessores() {
		logger.info("iniciou o método listaProfessores.");
		EntityManager em = JpaUtil.getEntityManager();
		UsuarioRepository ur = new UsuarioRepository(em);
		this.professores = ur.listaProfessores();
		
	}
	public void listaAlunos() {
		logger.info("iniciou o método listaProfessores.");
		EntityManager em = JpaUtil.getEntityManager();
		UsuarioRepository ur = new UsuarioRepository(em);
		this.alunos = ur.listaAlunos();
		for (Usuario a : alunos) {
			
		
		System.out.println("aluno:" + a);}
	}
	
	


	public void buscaPorId() {
		logger.info("Entrou no método buscaPorId.");
		EntityManager em = JpaUtil.getEntityManager();
		UsuarioRepository sr = new UsuarioRepository(em);
		this.usuario = sr.buscaPorId(usuarioSelecionado.getId());
	}

	
	public void alterar() {
		logger.info("iniciou o método alterar.");
		EntityManager em = JpaUtil.getEntityManager();
		UsuarioRepository ur = new UsuarioRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			ur.alterar(usuario);
			FacesMessage mensagem = new FacesMessage("Usuário " + usuario.getNome() + " Alterado com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.usuario = new Usuario();
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage("Problemas para alterar o usuario.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void voltar() {
	      try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("Usuario.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void desativar() {
		logger.info("iniciou o método desativar.");
		EntityManager em = JpaUtil.getEntityManager();
		UsuarioRepository ur = new UsuarioRepository(em);
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ur.desativar(usuario);
			FacesMessage mensagem = new FacesMessage("Usuário " + usuario.getNome() + " desativado com sucesso.");
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, mensagem);
			this.usuario = new Usuario();
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage mensagem = new FacesMessage("Problema para desativar o usuário, contato e o administrador.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Usuario> professores) {
		this.professores = professores;
	}

	public List<Usuario> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Usuario> alunos) {
		this.alunos = alunos;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public DualListModel<Usuario> getModel() {
		return model;
	}

	public void setModel(DualListModel<Usuario> model) {
		this.model = model;
	}

	

	
	
}