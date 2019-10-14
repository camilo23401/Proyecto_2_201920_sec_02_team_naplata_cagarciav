package model.logic;

import com.google.gson.annotations.SerializedName;

public class NodoRedVial implements Comparable<NodoRedVial> 
{
 private int id;
 private double longitud;
 private double latitud;
 
 
 public NodoRedVial(int pId,double pLongitud,double pLatitud) {
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
@Override
public int compareTo(NodoRedVial o) {
	// TODO Auto-generated method stub
	return 0;
}
}
