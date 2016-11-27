package com.universidadez.tcc.util;

import java.util.Random;


public class CriaString {

	String[] arrayDeCaracteres = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
			"R", "S", "T", "U", "W", "V", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b",
			"c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "w", "v",
			"x", "y", "z" };
	String str;

	/**
	 * Método criado para sortear um número entre 0 e o número de itens do
	 * atributo "arrayDeCarasteres" da classe.
	 * 
	 * @return int - número sorteado.
	 */
	public int sorteiaNumero() {

		int maximo = arrayDeCaracteres.length;
		Random sorteio = new Random();
		return sorteio.nextInt(maximo);
	}

	/**
	 * Método gerador da string.
	 * 
	 * @param quantidadeCaracteres
	 *            int - Quantidade de caracteres que a string a ser criada deve
	 *            ter.
	 * @return String.
	 */
	public String GeraString(int quantidadeCaracteres) {

		str = "";
		for (int i = 0; i < quantidadeCaracteres; i++) {
			str += arrayDeCaracteres[this.sorteiaNumero()];
		}
		return str;
	}
}
