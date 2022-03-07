package practica01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DyV {
	
	ArrayList<Jugador> datos;
	
	public DyV() {
		
		this.datos=new ArrayList<>();
		
	}
	
	public void load() {
		
		BufferedReader br=null;
		String line;
		String[] items;
		Jugador aux=null;
		
		try {
			br=new BufferedReader(new FileReader("src"+File.separator+"practica01"+File.separator+"NbaStats.csv"));
			while ((line = br.readLine()) != null) {
				if(line.contains("#")) continue;
				items=line.split(";");
				aux=new Jugador(items[2],items[6],items[4], Integer.parseInt(items[8]));
				if(!this.datos.contains(aux))
				//System.out.println(this.datos.contains(aux));
					this.datos.add(aux);
				else {
					
					int pos=this.datos.indexOf(aux);
					int score=(this.datos.get(pos).getScore()+Integer.parseInt(items[8]))/2;
					this.datos.get(pos).setScore(score);
					if(!this.datos.get(pos).getPositions().contains(items[4]))
					this.datos.get(pos).getPositions().add(items[4]);
					if(!this.datos.get(pos).getTeams().contains(items[6]))
					this.datos.get(pos).getTeams().add(items[6]);
				}
				//for(String word : items) System.out.println(word);
				
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void mergesort(Jugador A[], int izq, int der) {
		if (izq > der) {
			int medio = (int) Math.ceil((izq + der)/2);
			mergesort(A, izq, medio);
			mergesort(A, medio + 1, der);
			merge(A, izq, medio, der);
			
		}
	}
	
	public static void merge(Jugador A[] , int izq, int medio, int der) {
		int i, j;
		Jugador[] aux = new Jugador[A.length];
		for (int l = 0; l < aux.length; l++) {
			aux[l] = A[l];
		}
		
		i = izq;
		j = medio + 1;
		
		while (izq <= medio && j <= der) {
			if (A[izq].getScore() > A[j].getScore()) {
				aux[i++] = A[izq++];
			}else {
				aux[i++] = A[j++];
			}
		}
		
		while (izq < medio) {
			aux[i++] = A[izq ++];
			
		}
		while (j < der) {
			aux[i++] = A[j++];
		}
		
	}

	public ArrayList<Jugador> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<Jugador> datos) {
		this.datos = datos;
	}

}
