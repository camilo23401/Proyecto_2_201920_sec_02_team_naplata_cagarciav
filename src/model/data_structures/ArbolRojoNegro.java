package model.data_structures;

import java.util.Iterator;

public class ArbolRojoNegro <K extends Comparable<K>, V> 
{
	private final static boolean RED = true;
	private final static boolean BLACK = false;
	private NodoArbol<K, V> raiz;

	public ArbolRojoNegro()
	{
		raiz = null;
		System.out.println(this.size());
		System.out.println(this.getHeight());  //SI ESTOS FUNCIONAS PORQUE PUTAS A PUT NI LO LLAMAN
	}
	public int size()
	{
		int tamanio = 0;
		if(raiz!=null)
		{
			tamanio = (int)raiz.darTamanio();		
		}
		return tamanio;
	}
	public boolean isEmpty()
	{
		boolean rta = false;
		if(raiz==null)
		{
			rta = true;
		}
		return rta;
	}
	public V get (K pLlave)
	{
		if(pLlave==null)
		{
			return null;
		}
		return getV(raiz, pLlave);
	}
	public V getV(NodoArbol<K,V> pNodo, K pLlave)
	{
		V rta = null;
		while(pNodo!=null)
		{
			int comparacion = pLlave.compareTo(pNodo.darLlave());
			if(comparacion<0)
			{
				pNodo = pNodo.darNodoIzquierda();
			}
			else if(comparacion>0)
			{
				pNodo = pNodo.darNodoDerecha();
			}
			else
			{
				rta = pNodo.darValor();
			}
		}
		return rta;
	}
	public void put (K pLlave, V pValor)
	{
		System.out.println(pLlave + "" + "" + pValor);
		if(pLlave!=null && pValor!=null)
		{
			raiz = putAux(raiz, pLlave, pValor);
			System.out.println(raiz);
			raiz.cambiarColor(BLACK);
		}
	}
	public NodoArbol<K, V> putAux(NodoArbol<K, V> pNodo, K pLlave, V pValor)
	{
		NodoArbol<K, V> nuevo = null;
		if(pNodo==null)
		{
			nuevo = new NodoArbol<K, V>(pLlave, pValor, RED,(short)1);
		}
		int comparacion = pLlave.compareTo(pNodo.darLlave());
		if(comparacion<0)
		{
			pNodo.cambiarNodoIzquierda(putAux(pNodo, pLlave, pValor));
		}
		else if(comparacion>0)
		{
			pNodo.cambiarNodoDerecha(putAux(pNodo,pLlave, pValor));
		}
		else
		{
			pNodo.ponerValor(pValor);
		}
		return nuevo;
	}
	public boolean contains(K pLlave)
	{
		if(get(pLlave)==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public K min()
	{
		if(isEmpty())
		{
			return null;
		}
		else
		{
			return minAux(raiz);
		}
	}
	public K minAux(NodoArbol<K, V> pNodo)
	{
		if(pNodo.darNodoIzquierda()==null)
		{
			return pNodo.darLlave();
		}
		else
		{
			return(minAux(pNodo.darNodoIzquierda()));
		}
	}
	public K max()
	{
		if(isEmpty())
		{
			return null;
		}
		else
		{
			return maxAux(raiz);
		}
	}
	public K maxAux(NodoArbol<K, V> pNodo)
	{
		if(pNodo.darNodoDerecha()==null)
		{
			return pNodo.darLlave();
		}
		else
		{
			return maxAux(pNodo.darNodoDerecha());
		}
	}
	public int getHeight()
	{
		return getHeightAux(raiz);
	}
	public int getHeightAux (NodoArbol<K, V> pNodo)
	{
		if(pNodo==null)
		{
			return -1;
		}
		else
		{
			return 1 + Math.max(getHeightAux(pNodo.darNodoIzquierda()),getHeightAux(pNodo.darNodoDerecha()));
		}
	}
	public Iterator<K> keys()
	{
		Stack<K> llavesIzquierda = leftKeys(raiz);
		Stack<K> llavesDerecha = rightKeys(raiz);
		Stack<K> todas = new Stack<K>();
		while(!llavesIzquierda.isEmpty())
		{
			todas.push(llavesIzquierda.pop());
		}
		while(!llavesDerecha.isEmpty())
		{
			todas.push(llavesDerecha.pop());
		}
		return todas.iterator();
	}
	public Stack<K> leftKeys(NodoArbol<K, V> pNodo)
	{
		Stack<K> stack = new Stack<K>();
		if(pNodo.darNodoIzquierda()==null)
		{
			stack.push(pNodo.darLlave());
			return stack;
		}
		else
		{
			stack.push(pNodo.darLlave());
			return leftKeys(pNodo.darNodoIzquierda());
		}
	}
	public Stack<K> rightKeys(NodoArbol<K, V> pNodo)
	{
		Stack<K> stack = new Stack<K>();
		if(pNodo.darNodoDerecha()==null)
		{
			stack.push(pNodo.darLlave());
			return stack;
		}
		else
		{
			stack.push(pNodo.darLlave());
			return leftKeys(pNodo.darNodoDerecha());
		}
	}
	public Iterator<K> keysInRange(NodoArbol<K, V> pNodo)
	{
		return null;
	}
	public Iterator<K> valuesInRange(NodoArbol<K, V> pNodo)
	{
		return null;
	}
}
