package model.data_structures;

public class NodoColaPrioritaria <T> implements INodoColaPrioritaria<T>{

	private T llave;

	private NodoColaPrioritaria<T>siguiente;


	public NodoColaPrioritaria(T elem){
		llave=elem;
	}	


	public T getLlave() {
		return llave;
	}

	public void setLlave(T llave) {
		this.llave = llave;
	}

	public NodoColaPrioritaria<T>darSiguiente(){
		return this.siguiente;
	}

	public void cambiarSiguiente(NodoColaPrioritaria<T> nuevo){

		this.siguiente=nuevo;
		
	}



}
