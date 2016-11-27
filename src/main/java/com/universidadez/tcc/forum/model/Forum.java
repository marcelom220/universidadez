package com.universidadez.tcc.forum.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.universidadez.tcc.universidade.model.Turma;

@NamedQuery(name = "Forum.listaAtivos", query = "SELECT s FROM Forum s WHERE s.ativo = true")
@Entity
@Table(name = "tb_forum")
public class Forum implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	
	
	private Long id;
	@OneToOne
	private Turma turma;
	private String nome;
	private String descricao;
	private Date dataCatastro;
	private boolean ativo;

	/**
	 * 
	 */
	public Forum() {
	}

	/**
	 * @param id
	 * @param nome
	 * @param descricao
	 * @param dataCatastro
	 * @param ativo
	 */
	public Forum(Long id, String nome, String descricao, Date dataCatastro, boolean ativo,Turma turma) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataCatastro = dataCatastro;
		this.ativo = ativo;
		this.turma = turma;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	@NotNull
	@Size(min = 3, max = 255)
	@Column(length = 255, nullable = false)
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the descricao
	 */
	@NotNull
	@Size(min = 3)
	@Lob
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the dataCatastro
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getDataCatastro() {
		return dataCatastro;
	}

	/**
	 * @param dataCatastro
	 *            the dataCatastro to set
	 */
	public void setDataCatastro(Date dataCatastro) {
		this.dataCatastro = dataCatastro;
	}

	/**
	 * @return the ativo
	 */
	@Column(nullable = false)
	public boolean isAtivo() {
		return ativo;
	}

	/**
	 * @param ativo
	 *            the ativo to set
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Forum other = (Forum) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
