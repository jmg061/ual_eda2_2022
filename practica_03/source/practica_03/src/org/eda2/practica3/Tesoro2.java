package org.eda2.practica3;

public class Tesoro2 implements Comparable<Tesoro2>{
	
	private String id;
	private double peso;
	private int beneficio;

	public Tesoro2(String id, double peso, int beneficio) {
		this.id=id;
		this.beneficio=beneficio;
		this.peso=peso;
		
	}
	
	public boolean equals(Tesoro2 tesoro) {
		if(this.beneficio==tesoro.beneficio && this.peso==tesoro.peso)
			return true;
		if(this.beneficio==tesoro.beneficio)
			return true;
		if(this.peso==tesoro.peso)
			return true;
		return false;
	}
	
	public boolean equals(Object tesoro) {
		if(this.beneficio==((Tesoro2)tesoro).beneficio && this.peso==((Tesoro2)tesoro).peso)
			return true;
		if(this.beneficio==((Tesoro2)tesoro).beneficio)
			return true;
		if(this.peso==((Tesoro2)tesoro).peso)
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

	public double getPeso() {
		return peso;
	}

	@Override
	public int compareTo(Tesoro2 o) {
		// TODO Auto-generated method stub
		double result=this.peso-o.peso;
		if(result<0)
			return -1;
		if(result>0)
			return 1;
		return 0;
	}
	
}
