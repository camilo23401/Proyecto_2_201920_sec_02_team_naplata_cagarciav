package model.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonStreamParser;
import com.google.gson.stream.JsonReader;
import com.opencsv.CSVReader;
import com.sun.javafx.geom.Area;

import model.data_structures.ArbolRojoNegro;
import model.data_structures.ArregloDinamico;
import model.data_structures.Stack;

public class ProyectoMundo
{

	private CSVReader lector;
	private CSVReader lector2;
	private CSVReader lector3;
	private Gson gson;
	private ArregloDinamico<ViajeUber> viajesUberMensual;
	private ArregloDinamico<ViajeUber> viajesUberSemanal;
	private ArregloDinamico<ViajeUber> viajesUberHorario;
	private ArregloDinamico<NodoRedVial> nodosViales;
	private ArregloDinamico<ZonaUber> zonasUber;
	private ArbolRojoNegro<Integer, Integer> prueba;


	public ProyectoMundo()
	{
		gson = new Gson();
		viajesUberMensual = new ArregloDinamico<ViajeUber>(2000);
		viajesUberSemanal = new ArregloDinamico<ViajeUber>(2000);
		viajesUberHorario = new ArregloDinamico<ViajeUber>(2000);
		nodosViales = new ArregloDinamico<NodoRedVial>(2000);
		zonasUber = new ArregloDinamico<ZonaUber>(2000);
		prueba = new ArbolRojoNegro<Integer, Integer>();
	}
	public String[] agregarDatos(String pTrimestre) throws IOException

	{

		String [] respuestas = new String[3];
		respuestas [0]= "Número de viajes encontrados para el trimestre en el archivo mensual: " + cargarViajesMensuales(pTrimestre);
		respuestas [1]= "Número de viajes encontrados para el trimestre en el archivo semanal: " + cargarViajesSemanales(pTrimestre);
		respuestas [2]= "Número de viajes encontrados para el trimestre en el archivo por horas: " + cargarViajesHorarios(pTrimestre);

		return respuestas;

	}
	public int cargarViajesMensuales(String pTrimestre) throws IOException
	{	
		int contador = 0;
		String rutaMensual = "data/bogota-cadastral-2018-"+pTrimestre+"-All-MonthlyAggregate.csv";
		lector = new CSVReader(new FileReader(rutaMensual));
		String [] siguiente;
		while ((siguiente = lector.readNext()) != null) 
		{
			if(contador!=0)
			{
				ViajeUber viajeNuevo = new ViajeUber(Integer.parseInt(siguiente[0]),Integer.parseInt(siguiente[1]), Short.parseShort("-1"), Double.parseDouble(siguiente[3]), Short.parseShort(siguiente[2]),Short.parseShort("-1"), Double.parseDouble(siguiente[4]), Double.parseDouble(siguiente[5]), Double.parseDouble(siguiente[6]),Short.parseShort(pTrimestre));
				viajesUberMensual.agregar(viajeNuevo);
			}
			contador++;
		}
		lector.close();
		return contador;
	}

	public int cargarViajesSemanales(String pTrimestre) throws IOException
	{
		int contador = 0;
		String rutaSemanal = "data/bogota-cadastral-2018-"+pTrimestre+"-WeeklyAggregate.csv";
		lector2 = new CSVReader(new FileReader(rutaSemanal));
		String [] siguiente;
		while ((siguiente = lector2.readNext()) != null) 
		{
			if(contador!=0)
			{
				ViajeUber viajeNuevo = new ViajeUber(Integer.parseInt(siguiente[0]), Integer.parseInt(siguiente[1]), Short.parseShort("-1"), Double.parseDouble(siguiente[3]), Short.parseShort("-1"), Short.parseShort(siguiente[2]), Double.parseDouble(siguiente[4]), Double.parseDouble(siguiente[5]), Double.parseDouble(siguiente[6]),Short.parseShort(pTrimestre));
				viajesUberSemanal.agregar(viajeNuevo);
			}
			contador++;
		}
		lector2.close();
		return contador;
	}

	public int cargarViajesHorarios(String pTrimestre) throws IOException
	{
		int contador = 0;
		String rutaHoraria = "data/bogota-cadastral-2018-"+pTrimestre+"-All-HourlyAggregate.csv";
		lector3 = new CSVReader(new FileReader(rutaHoraria));
		String [] siguiente;
		while ((siguiente = lector3.readNext()) != null) 
		{
			if(contador!=0)
			{
				ViajeUber viajeNuevo = new ViajeUber(Integer.parseInt(siguiente[0]), Integer.parseInt(siguiente[1]), Short.parseShort(siguiente[2]), Double.parseDouble(siguiente[3]), Short.parseShort("-1"), Short.parseShort("-1"), Double.parseDouble(siguiente[4]), Double.parseDouble(siguiente[5]), Double.parseDouble(siguiente[6]),Short.parseShort(pTrimestre));
				viajesUberHorario.agregar(viajeNuevo);
			}
			contador++;
		}
		lector3.close();
		return contador;
	}
	public void cargarInfoZonas() throws IOException
	{
		FileReader lector = new FileReader("data/bogota_cadastral.json");
		Object aux = JsonParser.parseReader(lector);
		JsonObject json = (JsonObject) aux;
		JsonElement elementoJson = json.get("features");
		JsonArray zonas = elementoJson.getAsJsonArray();
		for(int i=0; i<zonas.size();i++)
		{
			JsonElement elementoActual = zonas.get(i);
			JsonObject objeto = elementoActual.getAsJsonObject();
			JsonElement geometry = objeto.get("geometry");
			JsonObject x = geometry.getAsJsonObject();
			JsonElement posicion = x.get("coordinates");
			JsonArray arregloCoordenadas = posicion.getAsJsonArray();
			JsonArray primera = arregloCoordenadas.get(0).getAsJsonArray();
			JsonArray complementoPrimera = primera.get(0).getAsJsonArray();
			JsonElement lectorProperties = objeto.get("properties");
			JsonObject properties = lectorProperties.getAsJsonObject();
			JsonElement id = properties.get("MOVEMENT_ID");
			int idNum = id.getAsInt();
			JsonElement nombreZonal = properties.get("scanombre");
			String nombre = nombreZonal.getAsString();
			JsonElement lengArchivo = properties.get("shape_leng");
			double leng = lengArchivo.getAsDouble();
			JsonElement areaArchivo = properties.get("shape_area");
			double area = areaArchivo.getAsDouble();
			
			ZonaUber nueva = new ZonaUber(idNum, nombre, leng, area);
			zonasUber.agregar(nueva);
			for(JsonElement accesoSegunda : complementoPrimera)
			{
				JsonArray segunda = accesoSegunda.getAsJsonArray();
				double longitud = segunda.get(0).getAsDouble();
				double latitud = segunda.get(1).getAsDouble();
				Coordenadas actual = new Coordenadas(latitud, longitud);
				nueva.meterCoordenadas(actual);
			}
			//System.out.println(nueva.coordenadas.darElementoPos(0).darLongitud() + "  " + nueva.coordenadas.darElementoPos(0).darLatitud());
		
		}
		System.out.println("El número de zonas encontradas al cargar el archivo identificador de estas fue: "+zonasUber.darTamano());
	}
	public void cargarInfoMalla() throws IOException
	{
		BufferedReader lector = new BufferedReader(new FileReader("data/Nodes_of_red_vial-wgs84_shp.txt"));
		StringBuilder sb = new StringBuilder();
		String linea = lector.readLine();
		while(linea!=null)
		{
			String partes[] = linea.split(",");
			String id = partes[0];
			String longitud = partes[1];
			String latitud = partes[2];
			NodoRedVial nuevo = new NodoRedVial(Integer.parseInt(id),Double.parseDouble(longitud),Double.parseDouble(latitud));
			nodosViales.agregar(nuevo);
			linea = lector.readLine();
		}
		System.out.println("El número de nodos de la malla vial es: " + nodosViales.darTamano());
	}
	public void alimentarArbolPrueba()
	{
		prueba.put(3, 4);
		prueba.put(4, 6);
		prueba.put(2, 5);
		prueba.put(1, 7);
		System.out.println(prueba.size());
	}
}
