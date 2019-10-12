package model.data_structures;

public interface IMaxColaCP<T> {

	public boolean esVacia() ;
	
	public void agregar(T elem) ;
		
	public T sacarMax();
	
	public T darMax() ;
	
	public int darNumElementos();
	
	
	
}
