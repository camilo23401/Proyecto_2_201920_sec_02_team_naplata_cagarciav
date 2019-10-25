package model.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

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
import model.data_structures.HashLinearProbing;
import model.data_structures.HashSeparateChaining;
import model.data_structures.MaxColaCP;
import model.data_structures.Stack;
import model.data_structures.TablaHashLineal;

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
		viajesUberMensual = new ArregloDinamico<ViajeUber>(20000000);
		viajesUberSemanal = new ArregloDinamico<ViajeUber>(20000000);
		viajesUberHorario = new ArregloDinamico<ViajeUber>(30000000);
		nodosViales = new ArregloDinamico<NodoRedVial>(20000000);
		zonasUber = new ArregloDinamico<ZonaUber>(20000000);
		prueba = new ArbolRojoNegro<Integer, Integer>();
	}
	public String[] agregarDatos(String pTrimestre) throws IOException

	{

		String [] respuestas = new String[3];
		cargarViajesMensuales(pTrimestre);
		cargarViajesSemanales(pTrimestre);
		cargarViajesHorarios(pTrimestre);
		respuestas [0]= "Número de viajes encontrados para los primeros dos trimestres en el archivo mensual: "+viajesUberMensual.darTamano();
		respuestas [1]= "Número de viajes encontrados para los primeros dos trimestres en el archivo semanal: "+viajesUberSemanal.darTamano();
		respuestas [2]= "Número de viajes encontrados para los primeros dos trimestres en el archivo por horas: "+viajesUberHorario.darTamano() ;

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

			for(JsonElement accesoSegunda : complementoPrimera)
			{
				JsonArray segunda = accesoSegunda.getAsJsonArray();
				double longitud = segunda.get(0).getAsDouble();
				double latitud = segunda.get(1).getAsDouble();
				Coordenadas actual = new Coordenadas(latitud, longitud);
				nueva.meterCoordenadas(actual);
			}
			zonasUber.agregar(nueva);
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


	public ArregloDinamico<String>darNLetrasMasFrecuentes(){
		HashSeparateChaining<String,String> tabla=new HashSeparateChaining<String,String>(2000);
		ArregloDinamico<String>retorno=new ArregloDinamico<String>(2000);
		for (int i = 0; i < zonasUber.darTamano(); i++) {
			ZonaUber actual=zonasUber.darElementoPos(i);
			tabla.putInSet(String.valueOf(actual.scanombre.charAt(0)), actual.scanombre);
		}

		for (int i = 0; i < tabla.darCapacidad(); i++) {
			String llave=tabla.getPosKey(i);
			String parametro="";
			if(llave!=null) {
				int tamanioSet=tabla.getSetSize(llave);
				Iterator<String>it=tabla.getSet(llave);
				while(it.hasNext()) {
					parametro+=","+it.next();
				}

				retorno.agregar(llave+", cantidad:"+tamanioSet+parametro);
			}

		}
		retorno.shellSortString();
		return retorno;
	}

	public MaxColaCP<String>buscarNodosDelimitanZona(String latitud,String longitud){
		MaxColaCP<String>retorno=new MaxColaCP<>();
		for (int i = 0; i < zonasUber.darTamano(); i++) {
			ZonaUber actualZona=zonasUber.darElementoPos(i);
			ArregloDinamico<Coordenadas>cor=actualZona.darCoordendas();
			for (int j = 0; j < cor.darTamano(); j++) {
				Coordenadas actual=cor.darElementoPos(j);
				if(actual!=null&&Double.toString(actual.darLatitud()).length()>=5&&Double.toString(actual.darLongitud()).length()>=7) {
					String lat=Double.toString(actual.darLatitud()).substring(0,5);	
					String lon=Double.toString(actual.darLongitud()).substring(0,7);

					if(lon.equals(longitud.substring(0,7))&&lat.equals(latitud.substring(0,5))) {
						retorno.agregar(lon+","+lat+",  Zona:"+actualZona.scanombre);

					}
				}
			}
		}

		return retorno;
	}

	public ArregloDinamico<ViajeUber>darTiempoPromedioViajesRango(double limiteInferior,double limiteSuperior){
		ArbolRojoNegro<Double,ViajeUber>arbol=new ArbolRojoNegro<Double,ViajeUber>();
		ArregloDinamico<ViajeUber>orden=new ArregloDinamico<ViajeUber>(2000000);

		for (int i = 0; i<viajesUberMensual.darTamano() ; i++) {
			ViajeUber actual=viajesUberMensual.darElementoPos(i);
			if(actual.darTrimestre()==1) {
				arbol.put(actual.darMeanTravelTime(),actual);
			}	
		}
		Iterator<ViajeUber>it= arbol.valuesInRange(limiteInferior, limiteSuperior);
		while(it.hasNext()) {
			ViajeUber act=it.next();
			orden.agregar(act);
		}
		orden.shellSort();
		return orden;
	}

	public ArregloDinamico<ZonaUber> nZonasMasAlNorte(int pN)
	{
		HashLinearProbing<Double, ZonaUber> tabla = new HashLinearProbing<Double, ZonaUber>(20000);
		ArregloDinamico<Double> organizarLlaves= new ArregloDinamico<Double>(20000);
		ArregloDinamico<ZonaUber> rta = new ArregloDinamico<ZonaUber>(200000);
		for(int i= 0;i < zonasUber.darTamano(); i++)
		{
			ZonaUber actual = zonasUber.darElementoPos(i);
			double llaveLatitud = mayorLatitudZonal(actual);
			tabla.put(llaveLatitud, actual);
		}
			Iterator llavesRecuperada = tabla.keys();
			while(llavesRecuperada.hasNext())
			{
				double llave = (double) llavesRecuperada.next();
				organizarLlaves.agregar(llave);
			}
			organizarLlaves.shellSort();
			for(int i = 0; i<organizarLlaves.darTamano()&&i<=pN; i++)
			{
				ZonaUber porAgregar = tabla.get(organizarLlaves.darElementoPos(i));
				rta.agregar(porAgregar);
			}
			return rta;
	}
	public double mayorLatitudZonal(ZonaUber pZona)
	{
		ArregloDinamico<Coordenadas> listaCoordenadas = pZona.darCoordendas();
		double latitudMayor = 0.0;
		for(int i=0;i<listaCoordenadas.darTamano();i++)
		{
			Coordenadas actual = listaCoordenadas.darElementoPos(i);
			if(actual!=null)
			{
				if(actual.darLatitud()>=latitudMayor)
				{
					latitudMayor = actual.darLatitud();
				}	
			}
		}
		return latitudMayor;
	}
	public Coordenadas mayorLongitudZonalNodo(ZonaUber pZona)
	{
		ArregloDinamico<Coordenadas> listaCoordenadas = pZona.darCoordendas();
		double latitudMayor = 0.0;
		Coordenadas mayor = null;
		for(int i=0;i<listaCoordenadas.darTamano();i++)
		{
				Coordenadas actual = listaCoordenadas.darElementoPos(i);
				if(actual!=null)
				{
					if(actual.darLatitud()>=latitudMayor)
					{
						mayor = actual;
					}	
				}
			}
		return mayor;
	}
	
	public MaxColaCP<NodoRedVial> buscarNodosLocalizacion(String pLongitud, String pLatitud)
	{
		MaxColaCP<NodoRedVial> nodos = new MaxColaCP<NodoRedVial>();
		for(int i=0; i<nodosViales.darTamano();i++)
		{
			NodoRedVial actual = nodosViales.darElementoPos(i);
			if(actual!=null&&Double.toString(actual.darLatitud()).length()>=5&&Double.toString(actual.darLongitud()).length()>=7) {
				String lat=Double.toString(actual.darLatitud()).substring(0,4);	
				String lon=Double.toString(actual.darLongitud()).substring(0,6);

				if(lon.equals(pLongitud.substring(0,6))&&lat.equals(pLatitud.substring(0,4))) 
				{
					nodos.agregar(actual);
				}
			}
		}
		return nodos;
	}
	public ArregloDinamico<ViajeUber> tiemposPromedioDesviacionEnRango(double pMin, double pMax)
	{
		ArbolRojoNegro<Double,ViajeUber> rta=new ArbolRojoNegro<Double,ViajeUber>();
		ArregloDinamico<ViajeUber>listaOrdenada=new ArregloDinamico<ViajeUber>(2000000);
		for (int i = 0; i<viajesUberMensual.darTamano() ; i++) 
		{
			ViajeUber actual=viajesUberMensual.darElementoPos(i);
			if(actual.darTrimestre()==1) {
				rta.put(actual.darDesviacionEstandarTiempo(),actual);
			}	
		}
		Iterator<ViajeUber>it= rta.valuesInRange(pMin, pMax);
		while(it.hasNext()) {
			ViajeUber act=it.next();
			listaOrdenada.agregar(act);
		}
		listaOrdenada.shellSort();
		return listaOrdenada;
	}

	public ArregloDinamico<ViajeUber>darViajesOrigenHora(int zona,int hora){
		ArregloDinamico<ViajeUber>retorno=new ArregloDinamico<ViajeUber>(1000000);
		for (int i = 0; i < viajesUberHorario.darTamano(); i++) {
			ViajeUber actual=viajesUberHorario.darElementoPos(i);
			if(actual.darSourceid()==zona&&actual.darHora()==hora) {
				retorno.agregar(actual);
			}
		}
		return retorno;
	}
	public ArregloDinamico<ViajeUber>darViajesOrigenHoraRango(int zona,int horaInf,int horaSup){
		ArregloDinamico<ViajeUber>retorno=new ArregloDinamico<ViajeUber>(1000000);
		for (int i = 0; i < viajesUberHorario.darTamano(); i++) {
			ViajeUber actual=viajesUberHorario.darElementoPos(i);
			if(actual.darSourceid()==zona&&actual.darHora()>=horaInf&&actual.darHora()<=horaSup) {
				retorno.agregar(actual);
			}
		}
		return retorno;
	}

	public String buscarNombreZona(int id) {
		boolean encontrado=false;
		String retorno="";
		for (int i = 0; i < zonasUber.darTamano()&&!encontrado; i++) {
			ZonaUber actual=zonasUber.darElementoPos(i);
			if(actual.MOVEMENT_ID==id) {
				retorno=actual.scanombre;
				encontrado=true;
			}
		}
		return retorno;
	}

	public ArregloDinamico<String>darZonaPriorizadas(int n){
		ArregloDinamico<String>retorno=new ArregloDinamico<String>(100000);
		for (int i = 0; i < zonasUber.darTamano(); i++) {
			ZonaUber actual=zonasUber.darElementoPos(i);
			retorno.agregar(actual.scanombre+","+actual.darCantidadCordenadas());
		}
		retorno.shellSortString();
		return retorno;
	}
}

