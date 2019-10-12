package model.data_structures;

public interface INodoColaPrioritaria<T> {



	public T getLlave();

	public void setLlave(T llave) ;

	public NodoColaPrioritaria<T>darSiguiente();

	public void cambiarSiguiente(NodoColaPrioritaria<T> nuevo);

}
