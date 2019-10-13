package model.logic;

import com.google.gson.annotations.SerializedName;

import model.data_structures.Stack;

public class ZonaUber {
	@SerializedName("properties")
	private String[] properties;
	@SerializedName("geometry")
	private String[] geometry;

	public ZonaUber(short pId,String pNombre,double pPerimetro,double pArea, Stack<Cordenadas>pStack) {
		properties = new String[8];
		geometry = new String[2];
	}
	public String[] getProperties()
	{
		return properties;
	}
	public String[] getGeometry()
	{
		return geometry;
	}



}
