package com.universidadez.tcc.forum.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.universidadez.tcc.login.model.Usuario;


@NamedQuery(name = "Resposta.listaAtivos", query = "SELECT s FROM Resposta s WHERE s.topico = :topico and s.ativo = true")
@Entity
@Table(name = "tb_resposta")
public class Resposta implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;
	private Date dataCatastro;
	private boolean ativo;
	private Topico topico;
	private Usuario usuario;

	/**
	 * 
	 */
	public Resposta() {
	}

	/**
	 * @param id
	 * @param descricao
	 * @param dataCatastro
	 * @param ativo
	 * @param topico
	 * @param usuario
	 */
	public Resposta(Long id, String descricao, Date dataCatastro, boolean ativo, Topico topico, Usuario usuario) {
		this.id = id;
		this.descricao = descricao;
		this.dataCatastro = dataCatastro;
		this.ativo = ativo;
		this.topico = topico;
		this.usuario = usuario;
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
	 * @return the descricao
	 */
	@Lob
	@Column(nullable = false)
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

	/**
	 * @return the topico
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_topico")
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
	 * @return the usuario
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_usuario")
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Resposta other = (Resposta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
