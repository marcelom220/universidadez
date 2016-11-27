package com.universidadez.tcc.login.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.universidadez.tcc.login.model.Usuario;
import com.universidadez.tcc.util.Criptografia;

/**
 * Realiza operações com objeto do tipo Usuario.
 * 
 */
public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UsuarioRepository.class);

	private EntityManager em;

	/*
	 * Construtor com argumentos
	 * 
	 * @param em
	 */
	public UsuarioRepository(EntityManager em) {
		this.em = em;
	}

	/**
	 * Insere um usuário no banco de dados.
	 * 
	 */
	public void insere(Usuario usuario) {
		log.info("iniciou o método insere.");
		EntityTransaction et = em.getTransaction();
		Criptografia c = new Criptografia();

		
		String senha = usuario.getPassword();
		usuario.setPassword(c.criptografiaSha256(senha));
		usuario.setDataCadastro(new Date());
		usuario.setAtivo(true);
		
		try {

			et.begin();
			em.persist(usuario);
			et.commit();
			log.info("persistiu o usuario.");
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			log.error("Não conseguiu persistir o usuario.");
		} finally {
			em.close();
		}
		log.info("finalizou o metodo insere.");
	}

	/**
	 * Retorna a lista de usuarios ativos no banco de dados.
	 * 

	 */
	public List<Usuario> listaAtivos() {

		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.listaAtivos", Usuario.class);
		return query.getResultList();
	}

	/**
	 * Retorna um usuário do banco de dados, selecionado por id.
	 * 
	 */
	public Usuario buscaPorId(Long id) {

		return em.find(Usuario.class, id);
	}

	/**
	 */
	public void alterar(Usuario usuario) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(usuario);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}

	/**
	 * Desativa um usuario, selecionado por id.
	 * 
	 */
	public void desativar(Usuario usuario) {
		usuario.setAtivo(false);
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(usuario);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}

	/**
	 * Compara os dados digitados com os dados gravados no bando de dados.
	 * 
	 * @param usuario
	 *            Usuario.
	 * @return Usuario usuario.
	 */
	public Usuario login(Usuario usuario) {

		Criptografia c = new Criptografia();

		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.login", Usuario.class);
		query.setParameter("matricula", usuario.getMatricula());
		query.setParameter("password", c.criptografiaSha256(usuario.getPassword()));

		Usuario usuarioAutenticado = null;
		try {

			usuarioAutenticado = query.getSingleResult();
			return usuarioAutenticado;

		} catch (NoResultException e) {
			usuarioAutenticado = null;
			return usuarioAutenticado;
		}
	}
}

