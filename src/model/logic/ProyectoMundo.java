package model.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import com.google.gson.stream.JsonReader;
import com.opencsv.CSVReader;

import model.data_structures.ArregloDinamico;
import model.data_structures.Stack;

public class ProyectoMundo
{
	
	private Stack<ViajeUber> viajesMensuales; 
	private Stack<ViajeUber> viajesSemanales; 
	private Stack<ViajeUber> viajesHorarios; 
	private CSVReader lector;
	private CSVReader lector2;
	private CSVReader lector3;
	private Gson gson;



	public ProyectoMundo()
	{
		viajesMensuales = new Stack<ViajeUber>();
		viajesSemanales = new Stack<ViajeUber>();
		viajesHorarios = new Stack<ViajeUber>();
		gson = new Gson();
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
				viajesMensuales.push(viajeNuevo);
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
				viajesSemanales.push(viajeNuevo);
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
				viajesHorarios.push(viajeNuevo);
			}
			contador++;
		}
		lector3.close();
		return contador;
	}
	public void cargarInfoZonas() throws IOException
	{
		Gson gson = new GsonBuilder().create();
		int contador = 0;
		BufferedReader lectorJson = new BufferedReader(new FileReader("data/bogota_cadastral.json"));
	    JsonStreamParser p = new JsonStreamParser(lectorJson);

        while(p.hasNext()){
            JsonElement e = p.next();
            if(e.isJsonObject()){
            	ZonaUber zona = gson.fromJson(lectorJson, ZonaUber.class);
            	System.out.println(zona);
                System.out.println(zona.getProperties());
            }
        }
    }
	public void cargarInfoMalla() throws IOException
	{
		int contador = 0;
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
			contador++;
			linea = lector.readLine();
		}
		System.out.println("El número de nodos de la malla vial es: " + contador);
	}
}
