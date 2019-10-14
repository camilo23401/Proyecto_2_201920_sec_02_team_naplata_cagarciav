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
					String trimestre = "";
					System.out.println("Digite el número del semestre a consultar");
					trimestre = lector.next();
					if(Short.parseShort(trimestre)<1||Short.parseShort(trimestre)>4)
					{
						System.out.println("Valor de trimestre no válido");
						break;
					}
					else
					{
						proyecto.cargarInfoZonas();
					}
					
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				break;

			case 2:
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


