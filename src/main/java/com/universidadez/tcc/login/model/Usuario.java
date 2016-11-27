package com.universidadez.tcc.login.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

/**
 * Classe que representa a entidade usuario, utilizada para fazer login no
 * sistema (controle de acesso).
 * 
 */
@NamedQueries({ @NamedQuery(name = "Usuario.listaAtivos", query = "SELECT u FROM Usuario u WHERE u.ativo = true"),
		@NamedQuery(name = "Usuario.login", query = "SELECT u FROM Usuario u WHERE u.matricula = :matricula AND u.password = :password AND u.ativo = true") })
@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String matricula;
	private String password;
	private String nome;
	private String email;
	private TipoUsuario tipoUsuario;
	private Date dataCadastro;
	private boolean ativo;
	private String telefone;
	private String sexo;
	
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dataNascimento;
	private String cpf;

	@CPF
	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	/**
	 * 
	 */
	public Usuario() {
	
	}

	
	public Usuario(Long id, String matricula, String password, String nome, String email, TipoUsuario tipoUsuario,
			Date dataCadastro, boolean ativo,String telefone,String sexo, String cpf) {
		this.id = id;
		this.matricula = matricula;
		this.password = password;
		this.nome = nome;
		this.email = email;
		this.tipoUsuario = tipoUsuario;
		this.dataCadastro = dataCadastro;
		this.ativo = ativo;
		this.telefone = telefone;
		this.sexo = sexo;
		this.cpf = cpf;
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

	
	@NotEmpty(message="A matricula deve ser informada")
	@Column(length = 255, nullable = false, unique = true)
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return the password
	 */
	
	@NotEmpty(message="Por favor informe a senha")
	@Size(min = 5, max = 255)
	@Column(length = 255, nullable = false)
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the nome
	 */
	@NotEmpty(message = "O nome deve ser informado")
	@Size(message ="O Nome deve ter entre 5 e 255 caracters")
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
	 * @return the email
	 */
	@Email
	@NotEmpty(message="O e-mail deve ser informado")
	@Column(length = 255, nullable = false)
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tipoUsuario
	 */
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * @param tipoUsuario
	 *            the tipoUsuario to set
	 */
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * @return the dataCadastro
	 */
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro
	 *            the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the ativo
	 */
	
	@NotNull
	@Column(nullable = false)
	public boolean getAtivo() {
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
	 * @return dataNascimento
	 */
	
	@Column(nullable = false)
	public Date getDataNascimento()
	 {
	 return dataNascimento;
	 }
	/**
	 * @param dataNascimento
	 *            dataNascimento to set
	 */
	
	public void setDataNascimento(Date dataNascimento)
	 {
	 this.dataNascimento =  dataNascimento;
	 }
	@NotEmpty(message = "Escolha o sexo")

	public String getSexo()
	 {
	 return sexo;
	 }
	
	public void setSexo(String sexo)
	 {
	 this.sexo = sexo;
	 }
	
	
	
	@NotEmpty(message="O telefone deve ser informado")
	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
		
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		Usuario other = (Usuario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

}

