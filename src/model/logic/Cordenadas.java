package model.logic;

public class Cordenadas 
{
	private double latitud;
	private double longitud;
	public double darLatitud() {
		return latitud;
	}

	public Cordenadas(double pLatitud,double pLongitud) {
		latitud=pLatitud;
		longitud=pLongitud;
	}
	public void cambiarLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double darLongitud() {
		return longitud;
	}
	public void cambiarLongitud(double longitud) {
		this.longitud = longitud;
	}







}
