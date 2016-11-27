package com.universidadez.tcc.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Esta classe é uma fabrica de objetos do tipo EntityManager.
 * 
 */
public class JpaUtil {
	private static EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory("universidadeZ");
	}

	/**
	 * Este método retorna um objeto do tipo EntityManager.
	 * 
	 * @return EntityManager - Retorna uma instância do EntityManager.
	 */
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	/**
	 * Este método fecha a fábrica de EntityManager.
	 * 
	 */
	public static void close() {
		factory.close();
	}
}

