package practica01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DyV {

	private ArrayList<Jugador> datos;

	public DyV() {

		this.datos = new ArrayList<>();

	}

	public void load() {

		BufferedReader br = null;
		String line;
		String[] items;
		Jugador aux = null;

		try {
			br = new BufferedReader(
					new FileReader("src" + File.separator + "practica01" + File.separator + "NbaStats.csv"));
			while ((line = br.readLine()) != null) {
				if (line.contains("#"))
					continue;
				items = line.split(";");
				aux = new Jugador(items[2], items[6], items[4], Integer.parseInt(items[8]));
				if (!this.datos.contains(aux))
					// System.out.println(this.datos.contains(aux));
					this.datos.add(aux);
				else {

					aux = this.datos.get(this.datos.indexOf(aux));
					aux.setScore((aux.getScore()+Integer.parseInt(items[8]))/2);
					if (!aux.getPositions().contains(items[4]))
						aux.getPositions().add(items[4]);
					if (!aux.getTeams().contains(items[6]))
						aux.getTeams().add(items[6]);
				}
				// for(String word : items) System.out.println(word);

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
	
	public void mergesort() {
		mergesort(0, this.datos.size() - 1);
	}

	public void mergesort(int izq, int der) {
		if (izq < der && (der-izq)>=1) {
			int medio = (izq + der) / 2;
			mergesort(izq, medio);
			mergesort(medio + 1, der);
			merge(izq, medio, der);

		}
	}

	public void merge(int izq, int medio, int der) {
		int i, j, x;
		ArrayList<Jugador>  aux = new ArrayList<Jugador>();

		j = medio + 1;
		x = izq;

		while (izq <= medio && j <= der) {
			if (this.datos.get(izq).getScore() > this.datos.get(j).getScore()) {
				aux.add(this.datos.get(izq));
				izq++;
			} else {
				aux.add(this.datos.get(j));
				j++;
			}
		}

		while (izq <= medio) {
			aux.add(this.datos.get(izq));
			izq++;

		}
		while (j <= der) {
			aux.add(this.datos.get(j));
			j++;
		}
		i = 0;
		while(i < aux.size()){
			this.datos.set(x, aux.get(i++));
            x++;
        }

	}

	public ArrayList<Jugador> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<Jugador> datos) {
		this.datos = datos;
	}

}
