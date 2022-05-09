package org.eda2.practica3;

public class Tesoro implements Comparable<Tesoro>{
	
	private String id;
	private int peso;
	private int beneficio;

	public Tesoro(String id, int peso, int beneficio) {
		this.id=id;
		this.beneficio=beneficio;
		this.peso=peso;
		
	}
	
	public boolean equals(Tesoro tesoro) {
		if(this.beneficio==tesoro.beneficio && this.peso==tesoro.peso)
			return true;
		if(this.beneficio==tesoro.beneficio)
			return true;
		if(this.peso==tesoro.peso)
			return true;
		return false;
	}
	
	public boolean equals(Object tesoro) {
		if(this.beneficio==((Tesoro)tesoro).beneficio && this.peso==((Tesoro)tesoro).peso)
			return true;
		if(this.beneficio==((Tesoro)tesoro).beneficio)
			return true;
		if(this.peso==((Tesoro)tesoro).peso)
			return true;
		return false;
	}

	public String toString(){
		return "ID: "+this.id+"\n\tPeso: "+this.peso+"\n\tBeneficio: "+this.beneficio;
	}
	
	public String getId() {
		return id;
	}

	public int getBeneficio() {
		return beneficio;
	}

	public int getPeso() {
		return peso;
	}

	@Override
	public int compareTo(Tesoro o) {
		// TODO Auto-generated method stub
		return this.peso-o.peso;
	}
	
}
