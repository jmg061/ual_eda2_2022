package practica01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DyV {
	
	public void load() {
		
		BufferedReader br;
		
		try {
			br=new BufferedReader(new FileReader("src"+File.separator+"practica01"+File.separator+"NbaStats.csv"));
		} catch (FileNotFoundException e) {
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

}
