package com.universidadez.tcc.login.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class IndexBean {
	
	
	private String classePrincipal = ""; 
	private String classeUniversidade = "";
	private String classeCampus = "";
	private String classeCurso = "";
	private String classeTurma = "";
	private String classeForum = "";
	private String classeUsuario = "";
	private String paginaAtual = "";
	 
	
	
	
	@PostConstruct
	public void setPrincipal() {
		setPaginaAtual("Inicio.xhtml");
		setClassePrincipal("is-selected");
		setClasseUniversidade("");
		setClasseCampus("");
		setClasseCurso("");
		setClasseForum("");
		setClasseUsuario("");
	}
	@PostConstruct
	public void setUniversidade() {
		setPaginaAtual("/universidade/Universidade.xhtml");
		setClassePrincipal("");
		setClasseUniversidade("is-selected");
		setClasseCampus("");
		setClasseCurso("");
		setClasseForum("");
		setClasseUsuario("");
		
	}
	@PostConstruct
	public void setCampus(){
		setPaginaAtual("campus/Campus.xhtml");
		setClassePrincipal("");
		setClasseUniversidade("");
		setClasseCampus("is-selected");
		setClasseTurma("");
		setClasseCurso("");
		setClasseForum("");
		setClasseUsuario("");
	}
	@PostConstruct
	public void setCurso(){
		setPaginaAtual("campus/Campus.xhtml");
		setClassePrincipal("");
		setClasseUniversidade("");
		setClasseCampus("");
		setClasseCurso("is-selected");
		setClasseTurma("");
		setClasseForum("");
		setClasseUsuario("");
	}
	@PostConstruct
	public void setTurma(){
		setPaginaAtual("turma/Turma.xhtml");
		setClassePrincipal("");
		setClasseUniversidade("");
		setClasseCampus("");
		setClasseCurso("");
		setClasseTurma("is-selected");
		setClasseForum("");
		setClasseUsuario("");
	}
	@PostConstruct
	public void setForum(){
		setPaginaAtual("forum/ForumListaAtivos.xhtml");
		setClassePrincipal("");
		setClasseUniversidade("");
		setClasseCampus("");
		setClasseCurso("");
		setClasseTurma("");
		setClasseForum("is-selected");
		setClasseUsuario("");
	}
	@PostConstruct
	public void setUsuario(){
		setPaginaAtual("usuario/Usuario.xhtml");
		setClassePrincipal("");
		setClasseUniversidade("");
		setClasseCampus("");
		setClasseCurso("");
		setClasseTurma("");
		setClasseForum("");
		setClasseUsuario("is-selected");
	}
	
	
	
	
	
	
	
	
	
	
	public String getPaginaAtual() {
		return paginaAtual;
	}
	 
	public void setPaginaAtual(String paginaAtual) {
		this.paginaAtual = paginaAtual;
	}
	public String getClassePrincipal() {
		return classePrincipal;
	}
	public void setClassePrincipal(String classePrincipal) {
		this.classePrincipal = classePrincipal;
	}
	public String getClasseUniversidade() {
		return classeUniversidade;
	}
	public void setClasseUniversidade(String classeUniversidade) {
		this.classeUniversidade = classeUniversidade;
	}
	public String getClasseCampus() {
		return classeCampus;
	}
	public void setClasseCampus(String classeCampus) {
		this.classeCampus = classeCampus;
	}
	public String getClasseCurso() {
		return classeCurso;
	}
	public void setClasseCurso(String classeCurso) {
		this.classeCurso = classeCurso;
	}
	public String getClasseTurma() {
		return classeTurma;
	}
	public void setClasseTurma(String classeTurma) {
		this.classeTurma = classeTurma;
	}
	public String getClasseForum() {
		return classeForum;
	}
	public void setClasseForum(String classeForum) {
		this.classeForum = classeForum;
	}

	public String getClasseUsuario() {
		return classeUsuario;
	}

	public void setClasseUsuario(String classeUsuario) {
		this.classeUsuario = classeUsuario;
	}
	 
	

	
	
}

