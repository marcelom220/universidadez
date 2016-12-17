package com.universidadez.tcc.forum.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.universidadez.tcc.forum.model.Resposta;
import com.universidadez.tcc.forum.model.Topico;


public class RespostaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	
	public void insere(Resposta resposta) {
		
		resposta.setAtivo(true);
		resposta.setDataCadastro(new Date());
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(resposta);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}

	
	public void desativa(Resposta resposta) {
		EntityTransaction et = em.getTransaction();
		resposta.setAtivo(false);
		try {
			et.begin();
			em.merge(resposta);
			
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}

	
	public void altera(Resposta resposta) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(resposta);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}

	
	public List<Resposta> lista(Topico topico) {
		TypedQuery<Resposta> query = em.createNamedQuery("Resposta.listaAtivos", Resposta.class);
		query.setParameter("topico", topico);
		return query.getResultList();
	}

	
	public Resposta buscaPorId(Long id) {
		return em.find(Resposta.class, id);
	}

	/**
	 * @param em
	 */
	public RespostaRepository(EntityManager em) {
		this.em = em;
	}
}
