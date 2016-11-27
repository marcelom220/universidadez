package com.universidadez.tcc.forum.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.universidadez.tcc.forum.model.Topico;


public class TopicoRepository implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private EntityManager em;

	
	public void insere(Topico topico) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(topico);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}


	public void desativa(Topico topico) {
		EntityTransaction et = em.getTransaction();
		topico.setAtivo(false);
		try {
			et.begin();
			em.merge(topico);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}

	
	public void altera(Topico topico) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(topico);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}


	public List<Topico> lista() {
		TypedQuery<Topico> query = em.createNamedQuery("Topicos.listaAtivos", Topico.class);
		return query.getResultList();
	}

	
	public Topico buscaPorId(Long id) {
		return em.find(Topico.class, id);
	}

	/**
	 * @param em
	 */
	public TopicoRepository(EntityManager em) {
		this.em = em;
	}

}
