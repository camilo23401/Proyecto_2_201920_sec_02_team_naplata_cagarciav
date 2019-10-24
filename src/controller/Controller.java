package controller;

import java.util.Iterator;
import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.data_structures.MaxColaCP;
import model.logic.ProyectoMundo;
import model.logic.ViajeUber;
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
				break;	

			case 6: 

				break;	

			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}
	}
}


