/**
 * 
 */
package com.universidadez.tcc.login.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.universidadez.tcc.login.model.TipoUsuario;
import com.universidadez.tcc.login.model.Usuario;
import com.universidadez.tcc.util.JpaUtil;


public class UsuarioInicializaBanco {

	public void insere(Usuario usuario) {
		EntityManager em = JpaUtil.getEntityManager();
		UsuarioRepository ur = new UsuarioRepository(em);
		ur.insere(usuario);
	}

	/**
	 * 
	 */
	public UsuarioInicializaBanco() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<Usuario> usuarios = new ArrayList<Usuario>();
Date d= new Date();

		Usuario u1 = new Usuario();
		u1.setNome("Francisco da Silva");
		u1.setEmail("fds@gmail.com");
		u1.setMatricula("123452011");
		u1.setPassword("fdsilva");
		u1.setDataNascimento(d);
		u1.setSexo("m");
		u1.setTipoUsuario(TipoUsuario.PROFESSOR);
		u1.setDataCadastro(new Date());
		u1.setAtivo(false);
		u1.setTelefone("(21)2441-2200");
		

		usuarios.add(u1);

		Usuario u2 = new Usuario();
		u2.setNome("Valter dos Santos");
		u2.setEmail("vds@gmail.com");
		u2.setMatricula("123452012");
		u2.setPassword("vdsantos");
		u2.setTipoUsuario(TipoUsuario.ALUNO);
		u2.setDataCadastro(new Date());
		u2.setAtivo(true);
		u2.setDataNascimento(d);
		u2.setSexo("m");
		u2.setTelefone("(21)2441-2200");

		usuarios.add(u2);

		Usuario u3 = new Usuario();
		u3.setMatricula("123452013");
		u3.setPassword("fercosmig");
		u3.setNome("Fernando Costa Migliorini");
		u3.setEmail("fercosmig@gmail.com");
		u3.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
		u3.setDataCadastro(new Date());
		u3.setAtivo(true);
		u3.setDataNascimento(d);
		u3.setSexo("m");
		u3.setTelefone("(21)2441-2200");
		usuarios.add(u3);

		Usuario u4 = new Usuario();
		u4.setMatricula("123452014");
		u4.setPassword("admin");
		u4.setNome("Administrador do sistema");
		u4.setEmail("fercosmig@gmail.com");
		u4.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
		u4.setDataCadastro(new Date());
		u4.setAtivo(true);
		u4.setDataNascimento(d);
		u4.setSexo("m");
		u4.setTelefone("(21)2441-2200");
		usuarios.add(u4);

		for (Usuario usuario : usuarios) {
			UsuarioInicializaBanco uib = new UsuarioInicializaBanco();
			uib.insere(usuario);
		}
	}
}
