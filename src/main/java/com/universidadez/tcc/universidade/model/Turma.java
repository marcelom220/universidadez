package com.universidadez.tcc.universidade.model;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.universidadez.tcc.login.model.Usuario;

@NamedQueries({ @NamedQuery(name = "Turma.lista", query = "SELECT u FROM Turma u")})
@Entity
@Table(name = "tb_turma")
public class Turma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@ManyToOne
	private Usuario professor;
	private String descrição;
	private String numero;
	
	private List<Usuario> listaAlunos;
	//@OneToOne
	//private Forum forum;
	@ManyToOne
	@JoinColumn(name="curso_id")             
	private Curso curso;
	
	
	public Turma(){}
	
	public Turma(Long id, Usuario professor, String descrição, String numero, List<Usuario> listaAlunos, Curso curso) {
		
		this.id = id;
		this.professor = professor;
		this.descrição = descrição;
		this.numero = numero;
		this.listaAlunos = (List<Usuario>)listaAlunos;
		
		this.curso = curso;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Usuario getProfessor() {
		return professor;
	}
	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}
	

	public String getDescrição() {
		return descrição;
	}
	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}
	
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "turma_aluno_usuario", joinColumns = @JoinColumn(name = "codigo_Turma"), inverseJoinColumns = @JoinColumn(name = "codigo_aluno"))
	public List<Usuario> getListaAlunos() {
		return listaAlunos;
	}
	public void setListaAlunos(List<Usuario> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}
	
	

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrição == null) ? 0 : descrição.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turma other = (Turma) obj;
		if (descrição == null) {
			if (other.descrição != null)
				return false;
		} else if (!descrição.equals(other.descrição))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	
	
}
