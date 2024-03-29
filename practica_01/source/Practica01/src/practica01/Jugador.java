package practica01;

import java.util.ArrayList;

public class Jugador implements Comparable<Jugador> {

	private String nombre;
	private ArrayList<String> equipos;
	private ArrayList<String> posiciones;
	private int puntuacion;

	public Jugador(String playerName, String team, String position, int score) {
		this.nombre = playerName;
		this.equipos = new ArrayList<String>();
		this.equipos.add(team);
		this.posiciones = new ArrayList<String>();
		this.posiciones.add(position);
		this.puntuacion = score;
	}

	@Override
	public boolean equals(Object o) {
		return this.nombre.equals(((Jugador) o).nombre);
	}

	public String getPlayerName() {
		return this.nombre;
	}

	public void setPlayerName(String playerName) {
		this.nombre = playerName;
	}

	public ArrayList<String> getTeams() {
		return this.equipos;
	}

	public void setTeams(ArrayList<String> teams) {
		this.equipos = teams;
	}

	public ArrayList<String> getPositions() {
		return this.posiciones;
	}

	public void setPositions(ArrayList<String> positions) {
		this.posiciones = positions;
	}

	public int getScore() {
		return this.puntuacion;
	}

	public void setScore(int score) {
		this.puntuacion = score;
	}

	public String toString() {
		return "Nombre: " + this.nombre + "\n\tPuntuacion: " + this.puntuacion + "\n\tEquipos: " + this.equipos
				+ "\n\tPosiciones: " + this.posiciones;
	}

	@Override
	public int compareTo(Jugador o) {
		if (this.puntuacion > o.puntuacion)
			return 1;
		else if (this.puntuacion < o.puntuacion)
			return -1;
		return 0;
	}
}
