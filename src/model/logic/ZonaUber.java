package model.logic;

import model.data_structures.Stack;

public class ZonaUber {
	private short id;

	private String nombre;

	private double perimetro;

	private double area;

	private Stack<Cordenadas>cordenadas;

	public ZonaUber(short pId,String pNombre,double pPerimetro,double pArea, Stack<Cordenadas>pStack) {
		cambiarId(pId);
		cambiarNombre(pNombre);
		cambiarPerimetro(pPerimetro);
		cambiarArea(pArea);
		cordenadas=pStack;
	}

	public short darId() {
		return id;
	}

	public void cambiarId(short id) {
		this.id = id;
	}

	public String darNombre() {
		return nombre;
	}

	public void cambiarNombre(String nombre) {
		this.nombre = nombre;
	}

	public double darPerimetro() {
		return perimetro;
	}

	public void cambiarPerimetro(double perimetro) {
		this.perimetro = perimetro;
	}

	public double darArea() {
		return area;
	}

	public void cambiarArea(double area) {
		this.area = area;
	}

	public Stack<Cordenadas> darCordenadas() {
		return cordenadas;
	}



}
