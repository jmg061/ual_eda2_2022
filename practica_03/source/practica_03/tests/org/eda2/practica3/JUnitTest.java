package org.eda2.practica3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JUnitTest {

	String matrizEnteros="0	0	0	0	0	0	0	0	0	\n" + 
			"0	2	2	2	2	2	2	2	2	\n" + 
			"0	2	5	7	7	7	7	7	7	\n" + 
			"0	2	5	7	7	8	11	13	13	\n" + 
			"0	2	5	7	7	10	12	15	17	\n" + 
			"0	2	5	7	7	10	12	15	17	\n" + 
			"0	2	5	7	7	10	12	15	17	\n";
	
	String matrizReales="0	0	0	0	0	0	0	0	0	\n" + 
			"0	2	2	2	2	2	2	2	2	\n" + 
			"0	2	7	7	7	7	7	7	7	\n" + 
			"0	2	7	7	8	13	13	13	13	\n" + 
			"0	2	7	7	8	13	13	17	17	\n" + 
			"0	2	7	7	8	13	13	17	17	\n" + 
			"0	2	7	7	8	13	13	17	18	\n";
	
	String tesorosEnteros="ID: A\n" + 
			"	Peso: 1\n" + 
			"	Beneficio: 2\n" + 
			"ID: B\n" + 
			"	Peso: 2\n" + 
			"	Beneficio: 5\n" + 
			"ID: D\n" + 
			"	Peso: 5\n" + 
			"	Beneficio: 10\n";
	
	String tesorosReales="ID: A\n" + 
			"	Peso: 0.24\n" + 
			"	Beneficio: 2\n" + 
			"ID: F\n" + 
			"	Peso: 7.75\n" + 
			"	Beneficio: 16\n";
	
	String tesorosGreed="ID: A\n" + 
			"	Peso: 0.24\n" +
			"	Beneficio: 2\n";
	
	@Test
	void ProblemaDeLaMochilaEnteros() {
		
		Dinamica dinamica = new Dinamica(8,6, 16, 8);//Le pasamos los parametros que coinciden con el juego
													 //de pruebas que vamos a usar para comprobar los resultados
		dinamica.pruebas();
		
		String[] resultados = dinamica.resolucion();
		
		assertEquals(resultados[0], matrizEnteros);
		
		assertEquals(resultados[1], tesorosEnteros);
	}
	
	@Test
	void ProblemaDeLaMochilaReales() {
		
		Dinamica dinamica = new Dinamica(8,6, 16, 8);//Le pasamos los parametros que coinciden con el juego
													 //de pruebas que vamos a usar para comprobar los resultados
		dinamica.pruebas2();
		
		String[] resultados = dinamica.resolucion2();
		
		assertEquals(resultados[0], matrizReales);
		
		assertEquals(resultados[1], tesorosReales);
	}
	
	@Test
	void ProblemaDeLaMochilaGreed() {
		
		Dinamica dinamica = new Dinamica(8,6, 16, 8);//Le pasamos los parametros que coinciden con el juego
													 //de pruebas que vamos a usar para comprobar los resultados
		dinamica.pruebas2();
		
		assertEquals(tesorosGreed, dinamica.Greed());

	}

}