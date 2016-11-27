package com.universidadez.tcc.forum.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.universidadez.tcc.forum.model.Forum;
import com.universidadez.tcc.forum.model.Sala;



public class SalaRepository implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(SalaRepository.class);

	private EntityManager em;

	
	public void insere(Sala sala) {
		logger.info("Entrou no método insere.");
		EntityTransaction et = em.getTransaction();
		
		sala.setDataCatastro(new Date());
		sala.setAtivo(true);
		
		try {
			et.begin();
			em.persist(sala);
			logger.info("persistiu a sala.");
			et.commit();
		} catch (Exception e) {
			logger.error("Deu erro, não persistiu a sala.");
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}

	
	public void desativa(Sala sala) {
		EntityTransaction et = em.getTransaction();
		sala.setAtivo(false);
		try {
			et.begin();
			em.merge(sala);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}

	
	public void altera(Sala sala) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(sala);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			em.close();
		}
	}

	
	public List<Sala> lista(Forum forum) {
		logger.info("Entrou no método listaAtivas.");
		TypedQuery<Sala> query = em.createNamedQuery("Sala.listaAtivos", Sala.class);
		query.setParameter("forum", forum);
		return query.getResultList();
	}

	
	public Sala buscaPorId(Long id) {
		return em.find(Sala.class, id);
	}

	/**
	 * @param em
	 */
	public SalaRepository(EntityManager em) {
		this.em = em;
	}

}
