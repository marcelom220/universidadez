package com.universidadez.tcc.universidade.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import com.universidadez.tcc.universidade.model.Turma;



public class TurmaRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(TurmaRepository.class);

	private EntityManager em;

	/*
	 * Construtor com argumentos
	 * 
	 * @param em
	 */
	public TurmaRepository(EntityManager em) {
		this.em = em;
	}

	/**
	 * Insere uma turma no banco de dados.
	 * 
	 */
	public void insere(Turma turma) {
		log.info("iniciou o método insere.");
		EntityTransaction et = em.getTransaction();
		

		
		try {

			et.begin();
			em.persist(turma);
			et.commit();
			log.info("persistiu a turma.");
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			log.error("Não conseguiu persistir a turma.");
		} finally {
			em.close();
		}
		log.info("finalizou o metodo insere.");
	}

	/**
	 * Retorna a lista de turmas no banco de dados.
	 * 

	 */
	public List<Turma> lista() {

		TypedQuery<Turma> query = em.createNamedQuery("Turma.lista", Turma.class);
		return query.getResultList();
	}

	/**
	 * Retorna uma turma do banco de dados, selecionada por id.
	 * 
	 */
	public Turma buscaPorId(Long id) {

		return em.find(Turma.class, id);
	}

	/**
	 */
	public void alterar(Turma turma) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(turma);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}
	public void excluir(Turma turma){
		log.info("iniciou o metodo excluir.");
		EntityTransaction et = em.getTransaction();
		try {

			et.begin();
			em.remove(turma);
			et.commit();
			log.info("excluiu a turma.");
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			log.error("Não conseguiu remover a turma.");
		} finally {
			em.close();
		}
		log.info("finalizou o metodo excluir.");
	}

	
}
