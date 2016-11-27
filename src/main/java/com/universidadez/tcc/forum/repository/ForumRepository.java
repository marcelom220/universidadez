package com.universidadez.tcc.forum.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.universidadez.tcc.forum.model.Forum;


public class ForumRepository implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	
	public void insere(Forum forum) {
		EntityTransaction et = em.getTransaction();
		forum.setDataCatastro(new Date());
		forum.setAtivo(true);
		try {
			et.begin();
			em.persist(forum);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}

	
	public void desativa(Forum forum) {
		EntityTransaction et = em.getTransaction();
		forum.setAtivo(false);
		try {
			et.begin();
			em.merge(forum);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}

	
	public void altera(Forum forum) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(forum);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}

	
	public List<Forum> lista() {
		TypedQuery<Forum> query = em.createNamedQuery("Forum.listaAtivos", Forum.class);
		return query.getResultList();
	}

	
	public Forum buscaPorId(Long id) {
		return em.find(Forum.class, id);
	}

	/**
	 * @param em
	 */
	public ForumRepository(EntityManager em) {
		this.em = em;
	}

}
