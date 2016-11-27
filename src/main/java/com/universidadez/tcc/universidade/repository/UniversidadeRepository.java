package com.universidadez.tcc.universidade.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;



import com.universidadez.tcc.universidade.model.Universidade;

public class UniversidadeRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UniversidadeRepository.class);

	private EntityManager em;

	/*
	 * Construtor com argumentos
	 * 
	 * @param em
	 */
	public UniversidadeRepository(EntityManager em) {
		this.em = em;
	}

	/**
	 * Insere uma universidade no banco de dados.
	 * 
	 */
	public void insere(Universidade universidade) {
		log.info("iniciou o método insere.");
		EntityTransaction et = em.getTransaction();
		

		try {

			et.begin();
			em.persist(universidade);
			et.commit();
			log.info("persistiu a universidade.");
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			log.error("Não conseguiu persistir a universidade.");
		} finally {
			em.close();
		}
		log.info("finalizou o metodo insere.");
	}

	/**
	 * Retorna a lista de universidades no banco de dados.
	 * 

	 */
	public List<Universidade> lista() {

		TypedQuery<Universidade> query = em.createNamedQuery("Universidade.lista", Universidade.class);
		return query.getResultList();
	}

	/**
	 * Retorna uma universidade do banco de dados, selecionada por id.
	 * 
	 */
	public Universidade buscaPorId(Long id) {

		return em.find(Universidade.class, id);
	}

	/**
	 */
	public void alterar(Universidade universidade) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(universidade);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}

	public void excluir(Universidade universidade){
		log.info("iniciou o metodo excluir.");
		EntityTransaction et = em.getTransaction();
		try {

			et.begin();
			em.remove(universidade);
			et.commit();
			log.info("excluiu a universidade.");
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			log.error("Não conseguiu remover a universidade.");
		} finally {
			em.close();
		}
		log.info("finalizou o metodo excluir.");
	}

	
}
