package view;



public class MVCView 
{
	    /**
	     * Metodo constructor
	     */
	    public MVCView()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar Archivos");
			System.out.println("2.Obtener las N letras m�s frecuentes por las que comienza el nombre de una zona");
			System.out.println("3.Buscar los nodos que delimitan las zonas por Localizaci�n Geogr�fica (latitud,longitud)"); 
			System.out.println("4.Buscar los tiempos promedio de viaje que est�n en un rango y que son del primer trimestre del 2018.");
			System.out.println("6. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
	
}
