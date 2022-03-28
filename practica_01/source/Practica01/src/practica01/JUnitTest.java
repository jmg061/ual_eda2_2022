package practica01;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import org.junit.Test;

public class JUnitTest {
	
	//DyV dyv = new DyV();
	
	/*String[] nombres = {"Wilt Chamberlain*", "DeMar DeRozan", "Damian Lillard", "Kevin Durant",
			"Anthony Davis", "Russell Westbrook", "Karl-Anthony Towns", "Stephen Curry", "James Harden",
			"LeBron James"};*/
	
	String ordenados = "Nombre: LeBron James\n" + 
			"	Puntuacion: 1031\n" + 
			"	Equipos: [CLE, MIA]\n" + 
			"	Posiciones: [SG, SF, PF]\n" + 
			"	Nombre: James Harden\n" + 
			"	Puntuacion: 994\n" + 
			"	Equipos: [OKC, HOU]\n" + 
			"	Posiciones: [SG, PG]\n" + 
			"	Nombre: Stephen Curry\n" + 
			"	Puntuacion: 975\n" + 
			"	Equipos: [GSW]\n" + 
			"	Posiciones: [PG]\n" + 
			"	Nombre: Karl-Anthony Towns\n" + 
			"	Puntuacion: 965\n" + 
			"	Equipos: [MIN]\n" + 
			"	Posiciones: [C]\n" + 
			"	Nombre: Russell Westbrook\n" + 
			"	Puntuacion: 931\n" + 
			"	Equipos: [OKC]\n" + 
			"	Posiciones: [PG]\n" + 
			"	Nombre: Anthony Davis\n" + 
			"	Puntuacion: 894\n" + 
			"	Equipos: [NOH, NOP]\n" + 
			"	Posiciones: [PF, C]\n" + 
			"	Nombre: Kevin Durant\n" + 
			"	Puntuacion: 864\n" + 
			"	Equipos: [SEA, OKC, GSW]\n" + 
			"	Posiciones: [SG, SF]\n" + 
			"	Nombre: Damian Lillard\n" + 
			"	Puntuacion: 825\n" + 
			"	Equipos: [POR]\n" + 
			"	Posiciones: [PG]\n" + 
			"	Nombre: DeMar DeRozan\n" + 
			"	Puntuacion: 820\n" + 
			"	Equipos: [TOR]\n" + 
			"	Posiciones: [SG]\n" + 
			"	Nombre: Wilt Chamberlain*\n" + 
			"	Puntuacion: 785\n" + 
			"	Equipos: [PHW, SFW, TOT, PHI, LAL]\n" + 
			"	Posiciones: [C]\n\t";

	@Test
	public void cargaDatos() {
		
		DyV dyv = new DyV();
		dyv.load();
		assertTrue(dyv.getDatos().size()==3921);
		
	}
	
	
	@Test
	public void deDiezEnDiez() {

		DyV dyv = new DyV();
		dyv.load();
		PriorityQueue<Jugador> aux = new PriorityQueue<>(Collections.reverseOrder());
		aux.addAll(dyv.deDiezEnDiez());
		
		String comp="";
		
		while(!aux.isEmpty())
			comp+=aux.poll()+"\n\t";
		
		assertEquals(ordenados, comp);
		
		/*assertTrue(aux.size()==10);
		
		for(String nombre: nombres)
			assertTrue(aux.contains(new Jugador(nombre, "", "", 0)));*/
		
		dyv=null;
	}
	
	@Test
	public void Reduce() {

		DyV dyv = new DyV();
		dyv.load();
		//PriorityQueue<Jugador> aux = dyv.reduce();
		
		PriorityQueue<Jugador> aux = new PriorityQueue<>(Collections.reverseOrder());
		aux.addAll(dyv.deDiezEnDiez());
		
		String comp="";
		
		while(!aux.isEmpty())
			comp+=aux.poll()+"\n\t";
		
		assertEquals(ordenados, comp);
		
		/*assertTrue(aux.size()==10);
		
		for(String nombre: nombres)
			assertTrue(aux.contains(new Jugador(nombre, "", "", 0)));*/
		
	}
	
	@Test
	public void MergeSort2() {

		DyV dyv = new DyV();
		dyv.load();
		ArrayList<Jugador> aux = dyv.mergesort2();
		
		String comp="";
		
		for(int i=0; i<10; i++)
			comp+=aux.get(i)+"\n\t";

		assertEquals(ordenados, comp);
		
		/*for(int i = 0; i<10; i++)
			aux.add(dyv.getDatos().get(i));
		
		for(String nombre: nombres)
			assertTrue(aux.contains(new Jugador(nombre, "", "", 0)));*/
		
	}
	
	
	@Test
	public void MergeSort() {

		DyV dyv = new DyV();
		dyv.load();
		dyv.mergesort();
		ArrayList<Jugador> aux = new ArrayList<Jugador>();

		String comp="";
		
		for(int i = 0; i<10; i++)
			aux.add(dyv.getDatos().get(i));
		
		for(int i=0; i<10; i++)
			comp+=aux.get(i)+"\n\t";

		assertEquals(ordenados, comp);
		
		/*for(int i = 0; i<10; i++)
			aux.add(dyv.getDatos().get(i));
		
		for(String nombre: nombres)
			assertTrue(aux.contains(new Jugador(nombre, "", "", 0)));*/
		
	}
}
