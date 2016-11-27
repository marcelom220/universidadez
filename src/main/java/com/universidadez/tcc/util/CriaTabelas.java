package com.universidadez.tcc.util;

import javax.persistence.Persistence;

/**
 * Classe utilizada para inicialização do banco de dados da aplicação.
 * 
 */
public class CriaTabelas {

	public static void main(String[] args) {

		Persistence.createEntityManagerFactory("universidadeZ");
	}
}
	
