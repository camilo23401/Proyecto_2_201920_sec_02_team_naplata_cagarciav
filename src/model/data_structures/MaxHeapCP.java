package model.data_structures;

public class MaxHeapCP<T extends Comparable<T>> {

	private T[] heap; 
	private int capacidad;
	private int tamanio;

	@SuppressWarnings("unchecked")
	public MaxHeapCP(int capa) {
		capacidad=capa;
		tamanio=0;
		heap=(T[]) new Comparable[capa];


	}
	public boolean esVacia()
	{
		return tamanio == 0;
	}
	public int darNumElementos()
	{
		return tamanio;
	}
	private int indicePadre(int pos) 
	{ 
		return pos / 2; 
	} 

	private int hijoIzquierdo(int pos) 
	{ 
		return (2 * pos); 
	} 
	private int hijoDerecho(int pos) 
	{ 
		return (2 * pos) + 1; 
	} 

	private boolean tieneHijoIzquierdo(int i)
	{
		return hijoIzquierdo(i) <= tamanio;
	}

	private boolean tieneHijoDerecho(int i)
	{
		return hijoDerecho(i) <= tamanio;
	}

	public T sacarMax()
	{
		if (esVacia()) return null;

		T result = darMax();
		swap(1, tamanio);
		heap[tamanio] = null;
		tamanio--;
		sink();

		return result;
	}
	public void agregar(T value)
	{
		tamanio++;
		heap[tamanio] = value;
		swim();
	}
	private void swim()
	{
		int index = tamanio;

		while (tienePadre(index) && (darPadre(index).compareTo(heap[index]) < 0))
		{
			swap(index, indicePadre(index));
			index = indicePadre(index);
		}	

	}
	private void sink() {
		int index=1;
		while (tieneHijoIzquierdo(index))
		{
			int mayor = hijoIzquierdo(index);
			if (tieneHijoDerecho(index) && heap[hijoIzquierdo(index)].compareTo(heap[hijoDerecho(index)]) < 0)
			{
				mayor = hijoDerecho(index);
			}
			if (heap[index].compareTo(heap[mayor]) < 0)
			{
				swap(index, mayor);
			}
			else break;

			index = mayor;
		}
	}
	public T darMax()
	{
		if (tamanio>0);
		return heap[1];
	}


	private boolean tienePadre(int i)
	{
		return i > 1;
	}
	private T darPadre(int i)
	{
		return heap[indicePadre(i)];
	}
	private void swap(int index1, int index2)
	{
		T temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
	}
	public T darElementoPos(int i){
		return heap[i];
	}
	public ArregloDinamico<T> pasarAArregloDinamico() {
		ArregloDinamico<T> nuevo=new ArregloDinamico<T>(10000000);
		for (int i = 0; i < heap.length; i++) {
			nuevo.agregar(heap[i]);
		}
		return nuevo;
	}


}
