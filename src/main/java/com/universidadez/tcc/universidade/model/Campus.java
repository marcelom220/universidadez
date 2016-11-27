package com.universidadez.tcc.universidade.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@NamedQueries({ @NamedQuery(name = "Campus.lista", query = "SELECT u FROM Campus u")})
@Entity
@Table(name = "tb_campus")
public class Campus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idCampus;
	private String nome;
	private String rua;
	private String numero;
	private String telefone;
	private String dd;
	
	@ManyToOne
	@JoinColumn(name="universidade_id")
	 private Universidade universidade;
	
	
	public Campus(){}
	
	public Campus(Long idCampus, String nome, String rua, String numero, String telefone, String dd, Universidade universidade) {
		
		this.idCampus = idCampus;
		this.nome = nome;
		this.rua = rua;
		this.numero = numero;
		this.telefone = telefone;
		this.dd = dd;
		this.universidade = universidade;
	}
	
	
	
	@NotEmpty
	@Size(min = 5, max = 255)
	@Column(length = 255, nullable = false, unique = true)
	public String getNome() {
		return nome;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdCampus() {
		return idCampus;
	}

	public void setIdCampus(Long idCampus) {
		this.idCampus = idCampus;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	public String getDd() {
		return dd;
	}
	public void setDd(String dd) {
		this.dd = dd;
	}
	

	public Universidade getUniversidade() {
		return universidade;
	}

	public void setUniversidade(Universidade universidade) {
		this.universidade = universidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCampus == null) ? 0 : idCampus.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Campus other = (Campus) obj;
		if (idCampus == null) {
			if (other.idCampus != null)
				return false;
		} else if (!idCampus.equals(other.idCampus))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	
	

	
	
	
}
