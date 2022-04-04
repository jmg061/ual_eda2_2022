package practica02;

public class Pavimento implements Comparable<Pavimento>{

	private String inicio;
	private String fin;
	private double coste;
	private int caminos;
	
	public Pavimento(String inicio, String fin, double coste, int caminos) {
		
		this.inicio=inicio;
		this.fin=fin;
		this.coste=coste;
		this.caminos=caminos;
		
	}
	
	public Pavimento() {
		this.inicio="Vacio";
		this.fin="Vacio";
		this.coste=Double.MAX_VALUE;
		this.caminos=-1;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this.inicio.equals(((Pavimento)o).inicio) && this.fin.equals(((Pavimento)o).fin))
			return true;
		if(this.fin.equals(((Pavimento)o).inicio) && this.inicio.equals(((Pavimento)o).fin))
			return true;
		return false;
	}
	
	@Override
	public int compareTo(Pavimento o) {
		// TODO Auto-generated method stub
		return (int) (this.coste - o.coste);
	}
	
	@Override
	public String toString() {
		return "Inicio: "+this.inicio + " Fin: "+this.fin+" Coste: "+this.coste+" Caminos: "+this.caminos;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public double getCoste() {
		return this.coste;
	}

	public void setCoste(double coste) {
		this.coste = coste;
	}
	
	public double getCaminos() {
		return this.caminos;
	}

	public void setCaminos(int caminos) {
		this.caminos = caminos;
	}
	
}
