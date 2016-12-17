package com.universidadez.tcc.universidade.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import com.universidadez.tcc.universidade.model.Curso;



public class CursoRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(CursoRepository.class);

	private EntityManager em;

	/*
	 * Construtor com argumentos
	 * 
	 * @param em
	 */
	public CursoRepository(EntityManager em) {
		this.em = em;
	}

	/**
	 * Insere um curso no banco de dados.
	 * 
	 */
	public void insere(Curso curso) {
		log.info("iniciou o método insere.");
		EntityTransaction et = em.getTransaction();
		

		
		try {

			et.begin();
			em.persist(curso);
			et.commit();
			log.info("persistiu o curso.");
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			log.error("Não conseguiu persistir o curso.");
		} finally {
			em.close();
		}
		log.info("finalizou o metodo insere.");
	}

	/**
	 * Retorna a lista de cursos no banco de dados.
	 * 

	 */
	public List<Curso> lista() {

		TypedQuery<Curso> query = em.createNamedQuery("Curso.lista", Curso.class);
		return query.getResultList();
	}

	/**
	 * Retorna um curso do banco de dados, selecionado por id.
	 * 
	 */
	public Curso buscaPorId(Long id) {

		return em.find(Curso.class, id);
	}

	/**
	 */
	public void alterar(Curso curso) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(curso);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}
	public void excluir(Curso curso){
		log.info("iniciou o metodo excluir.");
		EntityTransaction et = em.getTransaction();
		try {

			et.begin();
			curso = em.merge(curso);
			em.remove(curso);
			et.commit();
			log.info("excluiu o curso.");
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			log.error("Não conseguiu remover o curso.");
		} finally {
			em.close();
		}
		log.info("finalizou o metodo excluir.");
	}
	
}
