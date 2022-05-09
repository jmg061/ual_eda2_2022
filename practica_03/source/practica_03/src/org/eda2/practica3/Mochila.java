package org.eda2.practica3;

import java.util.ArrayList;

public class Mochila {
	
	private final int maxCapacidad;
	private ArrayList<Tesoro> tesoros;
	private int pesoActual;
	private int beneficioTotal;
	
	public Mochila(int maxCapacidad) {
		this.maxCapacidad = maxCapacidad;
		tesoros = new ArrayList<>();
		this.pesoActual = 0;
		this.beneficioTotal = 0;
	}
	
	public boolean add(Tesoro tesoro){
		if((pesoActual+tesoro.getPeso())>maxCapacidad)
			return false;
		this.tesoros.add(tesoro);
		this.pesoActual+=tesoro.getPeso();
		this.beneficioTotal+=tesoro.getBeneficio();
		return true;
	}
	
	public boolean add(ArrayList<Tesoro> tesoros){
		ArrayList<Tesoro> aux = new ArrayList<>();
		int sumPeso=0;
		int sumBeneficio=0;
		aux.addAll(tesoros);
		for(Tesoro tesoro : tesoros) {
			sumPeso+=tesoro.getPeso();
			sumBeneficio+=tesoro.getBeneficio();
		}
		if(sumPeso>maxCapacidad)
			return false;
		this.tesoros.addAll(aux);
		this.pesoActual=sumPeso;
		this.beneficioTotal=sumBeneficio;
		return true;
	}
	
	public String toString() {
		return String.valueOf(this.beneficioTotal);
	}

	public ArrayList<Tesoro> getTesoros() {
		return tesoros;
	}

	public void setTesoros(ArrayList<Tesoro> tesoros) {
		this.tesoros = tesoros;
	}

	public int getMaxCapacidad() {
		return maxCapacidad;
	}

	public int getPesoActual() {
		return pesoActual;
	}

	public int getBeneficioTotal() {
		return beneficioTotal;
	}
	
}
