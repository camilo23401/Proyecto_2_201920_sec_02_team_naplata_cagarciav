package controller;

import java.util.Scanner;


import model.logic.ProyectoMundo;
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
				proyecto.alimentarArbolPrueba();
				break;

			case 3:
				break;

			case 4:
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


