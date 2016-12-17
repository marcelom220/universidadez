package com.universidadez.tcc.universidade.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.universidadez.tcc.universidade.model.Campus;




public class CampusRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(CampusRepository.class);

	private EntityManager em;

	/*
	 * Construtor com argumentos
	 * 
	 * @param em
	 */
	public CampusRepository(EntityManager em) {
		this.em = em;
	}

	/**
	 * Insere um campus no banco de dados.
	 * 
	 */
	public void insere(Campus campus) {
		log.info("iniciou o método insere.");
		EntityTransaction et = em.getTransaction();
		

		
		try {

			et.begin();
			em.persist(campus);
			et.commit();
			log.info("persistiu o campus.");
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			log.error("Não conseguiu persistir o campus.");
		} finally {
			em.close();
		}
		log.info("finalizou o metodo insere.");
	}

	/**
	 * Retorna a lista de campus no banco de dados.
	 * 

	 */
	public List<Campus> lista() {
		log.info("Entrou no método lista");
		TypedQuery<Campus> query = em.createNamedQuery("Campus.lista", Campus.class);
		return query.getResultList();
		
		
	}

	/**
	 * Retorna um campus do banco de dados, selecionado por id.
	 * 
	 */
	public Campus buscaPorId(Long id) {

		return em.find(Campus.class, id);
	}

	/**
	 */
	public void alterar(Campus campus) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(campus);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}
	public void excluir(Campus campus){
		log.info("iniciou o metodo excluir.");
		EntityTransaction et = em.getTransaction();
		try {

			et.begin();
			campus = em.merge(campus);
			em.remove(campus);
			et.commit();
			log.info("excluiu o campus.");
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			log.error("Não conseguiu remover o campus.");
		} finally {
			em.close();
		}
		log.info("finalizou o metodo excluir.");
	}
	


}
