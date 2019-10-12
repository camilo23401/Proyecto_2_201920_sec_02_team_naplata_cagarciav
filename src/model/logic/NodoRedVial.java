package model.logic;

public class NodoRedVial {
 private short id;
 private double longitud;
 private double latitud;
 
 
 public NodoRedVial(Short pId,double pLongitud,double pLatitud) {
	 id=pId;
	 longitud=pLongitud;
	 latitud=pLatitud;
 }
public double darLatitud() {
	return latitud;
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
