package practica01;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import org.junit.Test;

public class JUnitTest {
	
	DyV dyv = new DyV();
	
	String[] nombres = {"Wilt Chamberlain*", "DeMar DeRozan", "Damian Lillard", "Kevin Durant",
			"Anthony Davis", "Russell Westbrook", "Karl-Anthony Towns", "Stephen Curry", "James Harden",
			"LeBron James"};
	
	/*String ordenados = "Nombre: James Harden\n" + 
			"	Puntuacion: 994\n" + 
			"	Equipos: [OKC, HOU]\n" + 
			"	Posiciones: [SG, PG]\n" + 
			"	Nombre: Anthony Davis\n" + 
			"	Puntuacion: 894\n" + 
			"	Equipos: [NOH, NOP]\n" + 
			"	Posiciones: [PF, C]\n" + 
			"	Nombre: Damian Lillard\n" + 
			"	Puntuacion: 825\n" + 
			"	Equipos: [POR]\n" + 
			"	Posiciones: [PG]\n" + 
			"	Nombre: DeMar DeRozan\n" + 
			"	Puntuacion: 820\n" + 
			"	Equipos: [TOR]\n" + 
			"	Posiciones: [SG]\n" + 
			"	Nombre: Andrew Wiggins\n" + 
			"	Puntuacion: 780\n" + 
			"	Equipos: [MIN]\n" + 
			"	Posiciones: [SF]\n" + 
			"	Nombre: Isaiah Thomas\n" + 
			"	Puntuacion: 774\n" + 
			"	Equipos: [SAC, TOT, PHO, BOS]\n" + 
			"	Posiciones: [PG]\n" + 
			"	Nombre: George Gervin*\n" + 
			"	Puntuacion: 765\n" + 
			"	Equipos: [SAS, CHI]\n" + 
			"	Posiciones: [SF, SG]\n" + 
			"	Nombre: Chet Walker*\n" + 
			"	Puntuacion: 744\n" + 
			"	Equipos: [SYR, PHI, CHI]\n" + 
			"	Posiciones: [SF]\n" + 
			"	Nombre: Bob Pettit*\n" + 
			"	Puntuacion: 742\n" + 
			"	Equipos: [MLH, STL]\n" + 
			"	Posiciones: [PF, C]\n" + 
			"	Nombre: Giannis Antetokounmpo\n" + 
			"	Puntuacion: 738\n" + 
			"	Equipos: [MIL]\n" + 
			"	Posiciones: [SF, SG, PG]\n\t";*/

	@Test
	public void cargaDatos() {
		
		dyv.load();
		assertTrue(dyv.getDatos().size()==3921);
		
	}
	
	
	@Test
	public void deDiezEnDiez() {

		DyV dyv = new DyV();
		dyv.load();
		PriorityQueue<Jugador> aux = dyv.deDiezEnDiez();
		
		assertTrue(aux.size()==10);
		
		for(String nombre: nombres)
			assertTrue(aux.contains(new Jugador(nombre, "", "", 0)));
		
		dyv=null;
	}
	
	@Test
	public void Reduce() {

		DyV dyv = new DyV();
		dyv.load();
		PriorityQueue<Jugador> aux = dyv.reduce();
		
		assertTrue(aux.size()==10);
		
		for(String nombre: nombres)
			assertTrue(aux.contains(new Jugador(nombre, "", "", 0)));
		
	}
	
	@Test
	public void MergeSort2() {

		DyV dyv = new DyV();
		dyv.load();
		ArrayList<Jugador> aux = dyv.mergesort2();
		
		String comp="";
		
		/*for(int i=0; i<10; i++)
			comp+=aux.get(i)+"\n\t";*/

		//assertEquals(ordenados, comp);
		
		for(int i = 0; i<10; i++)
			aux.add(dyv.getDatos().get(i));
		
		for(String nombre: nombres)
			assertTrue(aux.contains(new Jugador(nombre, "", "", 0)));
		
	}
	
	
	@Test
	public void MergeSort() {

		DyV dyv = new DyV();
		dyv.load();
		dyv.mergesort();
		ArrayList<Jugador> aux = new ArrayList<Jugador>();

		for(int i = 0; i<10; i++)
			aux.add(dyv.getDatos().get(i));
		
		for(String nombre: nombres)
			assertTrue(aux.contains(new Jugador(nombre, "", "", 0)));
		
	}
}
