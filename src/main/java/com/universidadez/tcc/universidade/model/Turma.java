package com.universidadez.tcc.universidade.model;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Column;
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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import com.universidadez.tcc.login.model.Usuario;

@NamedQueries({ @NamedQuery(name = "Turma.lista", query = "SELECT t FROM Turma t ")})
@Entity
@Table(name = "tb_turma")
public class Turma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Usuario professor;
	private String numero;
	private List<Usuario> listaAlunos;      
	private Curso curso;
	
	
	public Turma(){}
	
	public Turma(Long id, Usuario professor, String numero, List<Usuario> listaAlunos, Curso curso) {
		
		this.id = id;
		this.professor = professor;
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
	
	@ManyToOne(optional = true,fetch = FetchType.EAGER)
	public Usuario getProfessor() {
		return professor;
	}
	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}
	
	
	

	
	@Size(min= 1,max= 5)
	@NotEmpty
	@Column(length = 5,name = "numero_turma", unique = true )
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	@ManyToMany(fetch = FetchType.EAGER )
	@JoinTable(name = "turma_aluno_lista", joinColumns = @JoinColumn(name = "codigo_Turma"), inverseJoinColumns = @JoinColumn(name = "codigo_aluno"))
	public List<Usuario> getListaAlunos() {
		return listaAlunos;
	}
	public void setListaAlunos(List<Usuario> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}
	
	
	@ManyToOne
	@JoinColumn(name="curso_id")      
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((listaAlunos == null) ? 0 : listaAlunos.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listaAlunos == null) {
			if (other.listaAlunos != null)
				return false;
		} else if (!listaAlunos.equals(other.listaAlunos))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	
	

	
	
}
