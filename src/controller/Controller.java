package controller;

import java.util.Iterator;
import java.util.Scanner;

import jdk.nashorn.internal.runtime.ListAdapter;
import model.data_structures.ArregloDinamico;
import model.data_structures.HashSeparateChaining;
import model.data_structures.MaxColaCP;
import model.logic.NodoRedVial;
import model.logic.ProyectoMundo;
import model.logic.ViajeUber;
import model.logic.ZonaUber;
import view.MVCView;

public class Controller {



	/* Instancia de la Vista*/
	private MVCView view;

	private String semestre;

	private ProyectoMundo proyecto;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		proyecto = new ProyectoMundo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				try
				{
					proyecto.agregarDatos("1");
					String[] cargarViajes = proyecto.agregarDatos("2");
					for(int i=0;i<cargarViajes.length;i++)
					{
						System.out.println(cargarViajes[i]);
					}
					proyecto.cargarInfoZonas();
					proyecto.cargarInfoMalla();



				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				break;

			case 2:
				ArregloDinamico<String> retorno=proyecto.darNLetrasMasFrecuentes();
				System.out.println("Ingresar valor N");
				int n  = Integer.parseInt(lector.next());
				for (int i = 0; i <n; i++) {
					System.out.println(retorno.darElementoPos(i));
				}

				break;

			case 3:

				System.out.println("Ingresar longitud");
				String longitud  = lector.next();
				System.out.println("Ingresar latitud");
				String latitud  = lector.next();
				MaxColaCP<String> retorno1=proyecto.buscarNodosDelimitanZona(latitud, longitud);
				System.out.println("El numero de nodos que delimitan la zona son: "+retorno1.darNumElementos());			
				if(retorno1.darNumElementos()>0) {
					for (int i = 0; i <retorno1.darNumElementos(); i++) {
						System.out.println(retorno1.darElemento(i));
					}
				}
				else {
					System.out.println("No se encontraron coordenadas con esas especificaciones");

				}
				break;

			case 4:
				System.out.println("Ingresar limite inferior");
				double limInf  = Double.parseDouble(lector.next());
				System.out.println("Ingresar limite superior");
				double limSup  = Double.parseDouble(lector.next());
				System.out.println("Ingresar N");
				int n1  = Integer.parseInt(lector.next());
				ArregloDinamico<ViajeUber>it1=proyecto.darTiempoPromedioViajesRango(limInf, limSup);
				for (int i = 0; i < n1&&it1.darTamano()>0; i++) {
					ViajeUber act=it1.darElementoPos(i);
					System.out.println("Origen: "+act.darSourceid()+" , Destino:"+act.darDstid()+", Mes:"+act.darMes()+", Tiempo Promedio:"+act.darMeanTravelTime());

				}

				break;

			case 5: 
				System.out.println("Digite el número de zonas más al norte por buscar");
				int numZonas = lector.nextInt();
				ArregloDinamico<ZonaUber> zonas = proyecto.nZonasMasAlNorte(numZonas);
				for(int i=0;i<zonas.darTamano();i++)
				{
					ZonaUber actual = zonas.darElementoPos(i);
					System.out.println("-------------------------");
					System.out.println(actual.scanombre);
					System.out.println("Latitud " + proyecto.mayorLatitudZonal(actual));
					System.out.println("Longitud" + proyecto.mayorLongitudZonalNodo(actual).darLongitud());
					System.out.println("-------------------------");
				}
				break;

			case 6: 
				System.out.println("Ingrese la longitud: ");
				String longitudNodos = lector.next();
				System.out.println("Ingrese la latitud: ");
				String latitudNodos = lector.next();
				MaxColaCP<NodoRedVial> nodos = proyecto.buscarNodosLocalizacion(longitudNodos,latitudNodos);
				for(int i=0;i<nodos.darNumElementos();i++)
				{
					NodoRedVial actual = nodos.darElemento(i);
					System.out.println("----------------------");
					System.out.println("Viaje número " + i);
					System.out.println("Id " + actual.darId());
					System.out.println("Latitud "+actual.darLatitud());
					System.out.println("Longitud "+actual.darLongitud());
					System.out.println("----------------------");
				}
				System.out.println("El número de elementos encontrado fue: " + nodos.darNumElementos());
				break;	
			case 7:
				System.out.println("Ingresar limite inferior");
				double limiteInferior  = Double.parseDouble(lector.next());
				System.out.println("Ingresar limite superior");
				double limiteSuperior  = Double.parseDouble(lector.next());
				System.out.println("Ingresar N");
				int n2  = Integer.parseInt(lector.next());
				ArregloDinamico<ViajeUber>it2=proyecto.darTiempoPromedioViajesRango(limiteInferior, limiteSuperior);
				for (int i = 0; i < n2&&it2.darTamano()>0; i++) 
				{
					ViajeUber act=it2.darElementoPos(i);
					System.out.println("Origen: "+act.darSourceid()+" , Destino:"+act.darDstid()+", Mes:"+act.darMes()+", Tiempo Promedio:"+act.darMeanTravelTime());

				}
				break;
			case 8: 

				System.out.println("Ingresar id de origen");
				int idOrigen  = Integer.parseInt(lector.next());
				System.out.println("Ingresar hora");
				int idHora  =Integer.parseInt(lector.next());
				System.out.println("Ingresar N");
				int n3  =Integer.parseInt(lector.next());
				ArregloDinamico<ViajeUber>origenHora=proyecto.darViajesOrigenHora(idOrigen, idHora);
				String origenzona=proyecto.buscarNombreZona(idOrigen);
				for (int i = 0;origenHora.darTamano()>0&& i < n3; i++) {
					ViajeUber actual=origenHora.darElementoPos(i);
					String destinoZona=proyecto.buscarNombreZona(actual.darDstid());
					System.out.println("Origen: "+origenzona+", Destino: "+destinoZona+", Hora: "+actual.darHora()+", TiempoPromedio: "+actual.darMeanTravelTime());
				}

				break;	
			case 9: 

				System.out.println("Ingresar id de origen");
				int idOrigen1  = Integer.parseInt(lector.next());
				System.out.println("Ingresar hora inferior");
				int horaInf  =Integer.parseInt(lector.next());
				System.out.println("Ingresar hora superior");
				int horaSup  =Integer.parseInt(lector.next());
				System.out.println("Ingresar N");
				int n4  =Integer.parseInt(lector.next());
				ArregloDinamico<ViajeUber>origenHoraRango=proyecto.darViajesOrigenHoraRango(idOrigen1, horaInf, horaSup);
				String origenzona1=proyecto.buscarNombreZona(idOrigen1);
				for (int i = 0;origenHoraRango.darTamano()>0&& i < n4; i++) {
					ViajeUber actual=origenHoraRango.darElementoPos(i);
					String destinoZona=proyecto.buscarNombreZona(actual.darDstid());
					System.out.println("Origen: "+origenzona1+", Destino: "+destinoZona+", Hora: "+actual.darHora()+", TiempoPromedio: "+actual.darMeanTravelTime());
				}

				break;	
			case 10: 


				System.out.println("Ingresar N");
				int n5  =Integer.parseInt(lector.next());
				ArregloDinamico<String>listaZonas=proyecto.darZonaPriorizadas(n5);
				for (int i = 0;listaZonas.darTamano()>0&& i < n5; i++) {
					String[]arregl=listaZonas.darElementoPos(i).split(",");
					System.out.println("Nombre Zona: "+arregl[0]+", Cantidad de nodos frontera: "+arregl[1]);
				}

				break;	
			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}
	}
}


