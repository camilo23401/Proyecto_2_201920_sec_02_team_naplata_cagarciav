package controller;

import java.util.Scanner;

import model.logic.MVCModelo;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;
	
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
		modelo = new MVCModelo();
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
					System.out.println("Digite el número del trimestre a consultar: ");
					String semestrePorRevisar = "";
					semestrePorRevisar = lector.next();
					if(Integer.parseInt(semestrePorRevisar)<0 || Integer.parseInt(semestrePorRevisar)>4)
					{
						System.out.println("Digite un dato válido. Un año solo tiene 4 trimestres.");	
					}
					else
					{
						semestre=semestrePorRevisar;
						try
						{
							String[] rta = proyecto.agregarDatos(semestrePorRevisar);
							for(int i=0;i<rta.length;i++)
							{
								System.out.println(rta[i]);
							}
						}
						catch (Exception e)
						{
							System.out.println("Error fatal. No se pudo leer el archivo deseado");
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
