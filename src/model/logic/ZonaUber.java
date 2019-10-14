package model.logic;

import com.google.gson.annotations.SerializedName;

import model.data_structures.ArregloDinamico;
import model.data_structures.Stack;

public class ZonaUber implements Comparable<ZonaUber>
{
public int MOVEMENT_ID;
public String scanombre;
public double shape_leng;
public double shape_area;
public ArregloDinamico<Coordenadas> coordenadas;

	public ZonaUber(int pId, String pScanombre, double pShape_leng, double pShape_area) 
	{
		MOVEMENT_ID = pId;
		scanombre = pScanombre;
		shape_leng = pShape_leng;
		shape_area = pShape_area;
		coordenadas = new ArregloDinamico<Coordenadas>(4);
	}


	@Override
	public int compareTo(ZonaUber o) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void meterCoordenadas(Coordenadas pCoordenada)
	{
		coordenadas.agregarPos(pCoordenada, 0);
	}
	
	
	

}
