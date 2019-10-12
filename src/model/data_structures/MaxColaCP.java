package model.data_structures;

public class MaxColaCP<T extends Comparable<T>> implements IMaxColaCP<T>{

	private int tamanio;

	private NodoColaPrioritaria<T>cabeza;
	private  NodoColaPrioritaria<T> ultimoAgregado;
	public MaxColaCP() {
		cabeza=null;
		tamanio=0;
	}
	public boolean esVacia() {
		return cabeza==null;
	}

	public int darNumElementos() {
		return tamanio;
	}

	public void agregar(T elem) {
		if(cabeza==null){
			cabeza=new NodoColaPrioritaria<T>(elem);
			ultimoAgregado = cabeza;
			tamanio++;
		}
		else{
			if(ultimoAgregado.darSiguiente()==null)
			{
				NodoColaPrioritaria<T> nuevo = new NodoColaPrioritaria<T>(elem);
				ultimoAgregado.cambiarSiguiente(nuevo);
				ultimoAgregado = nuevo;
				tamanio++;	
			}
		}
	}
	public T sacarMax() {
		ArregloDinamico<T> ordenado=this.pasarAArregloDinamico(cabeza);
		ordenado.shellSort();
		T eliminado=ordenado.darElementoPos(0);
		this.eliminar(eliminado);
		return eliminado;

	}

	public T darMax() {
		ArregloDinamico<T> ordenado=this.pasarAArregloDinamico(cabeza);
		ordenado.shellSort();
		T eliminado=ordenado.darElementoPos(0);
		return eliminado;

	}
	public ArregloDinamico<T> pasarAArregloDinamico(NodoColaPrioritaria<T>actual) {
		ArregloDinamico<T> nuevo=new ArregloDinamico<T>(25000000);
		while(actual!=null)
		{
			T viajeActual = actual.getLlave();

			nuevo.agregar(viajeActual);

			actual = actual.darSiguiente();
		}
		return nuevo;
	}
	public T eliminar(T dato) {
		NodoColaPrioritaria<T>actual=cabeza;
		NodoColaPrioritaria<T>buscado=null;
		T eliminado=null;

		if(cabeza.getLlave().equals(dato)){
			cabeza=cabeza.darSiguiente();
			tamanio--;
		}
		else{
			while(actual!=null&&!actual.getLlave().equals(dato)){
				buscado=actual;
				actual=actual.darSiguiente();

			}
			eliminado=actual.getLlave();
			buscado.cambiarSiguiente(actual.darSiguiente());
			tamanio--;

		}
		return eliminado;
	}

	public T darElemento(int i) {
		T buscado=null;
		if(i==0){
			buscado=cabeza.getLlave();
		}
		else{
			NodoColaPrioritaria<T>actual=cabeza;
			int pos=0;
			boolean encontrado=false;
			while(actual.darSiguiente()!=null&&!encontrado){
				if(pos+1==i){
					buscado=actual.darSiguiente().getLlave();
					encontrado=true;
				}
				else{
					actual=actual.darSiguiente();
					pos++;
				}
			}
		}
		return buscado;

		
	}
	
	public NodoColaPrioritaria<T>darCabeza(){
		return cabeza;
	}
}
