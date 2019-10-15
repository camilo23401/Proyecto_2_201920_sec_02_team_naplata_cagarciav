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
	}
	public int size()
	{
		return sizeAux(raiz);
	}
	public int sizeAux(NodoArbol<K, V> pNodo)
	{
		if(pNodo==null)
		{
			return 0;
		}
		return pNodo.darTamanio();
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
		else
		{
			return getV(raiz, pLlave);
		}

	}
	public V getV(NodoArbol<K,V> pNodo, K pLlave)
	{
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
				return pNodo.darValor();
			}
		}
		return null;
	}
	public void put (K pLlave, V pValor)
	{
		if(pLlave!=null && pValor!=null)
		{
			raiz = putAux(raiz, pLlave, pValor);
			raiz.cambiarColor(BLACK);
		}
	}
	public NodoArbol<K, V> putAux(NodoArbol<K, V> pNodo, K pLlave, V pValor)
	{
		if(pNodo==null)
		{
			return new NodoArbol<K, V>(pLlave, pValor, RED,(short)1);
		}
		int comparacion = pLlave.compareTo(pNodo.darLlave());
		if(comparacion<0)
		{
			pNodo.cambiarNodoIzquierda(putAux(pNodo.darNodoIzquierda(), pLlave, pValor));
		}
		else if(comparacion>0)
		{
			pNodo.cambiarNodoDerecha(putAux(pNodo.darNodoDerecha(),pLlave, pValor));
		}
		else
		{
			pNodo.ponerValor(pValor);
		}
		
		if(isRed(pNodo.darNodoDerecha()) && !isRed(pNodo.darNodoIzquierda()))
		{
			pNodo = rotarIzquierda(pNodo);
		}
		if(isRed(pNodo.darNodoIzquierda()) && isRed(pNodo.darNodoIzquierda().darNodoIzquierda()))
		{
			pNodo = rotarDerecha(pNodo);
		}
		if(isRed(pNodo.darNodoDerecha()) && isRed(pNodo.darNodoIzquierda()))
		{
			intercambiarColores(pNodo);
		}
		int nuevoTamanio = sizeAux(pNodo.darNodoDerecha()) + sizeAux(pNodo.darNodoIzquierda()) + 1;
		pNodo.cambiarTamaño((short) nuevoTamanio);
		return pNodo;
	}
	public boolean isRed(NodoArbol<K, V> pNodo)
	{
		if(pNodo==null)
		{
			return false;
		}
		return pNodo.darColor() == RED;
	}
	public NodoArbol<K, V> rotarDerecha(NodoArbol<K, V> pNodo)
	{
		NodoArbol<K, V> aux = pNodo.darNodoIzquierda();
		pNodo.cambiarNodoIzquierda(aux.darNodoDerecha());
		aux.cambiarNodoDerecha(pNodo);
		aux.cambiarColor(pNodo.darColor());
		pNodo.cambiarColor(RED);
		aux.cambiarTamaño(pNodo.darTamanio());
		short nuevoTamanio = (short) (sizeAux(pNodo.darNodoIzquierda())+sizeAux(pNodo.darNodoDerecha())+1);
		pNodo.cambiarTamaño(nuevoTamanio);
		return aux;
	}
	public NodoArbol<K, V> rotarIzquierda(NodoArbol<K, V> pNodo)
	{
		NodoArbol<K, V> aux = pNodo.darNodoDerecha();
		pNodo.cambiarNodoDerecha(aux.darNodoIzquierda());
		aux.cambiarNodoIzquierda(pNodo);
		aux.cambiarColor(pNodo.darColor());
		pNodo.cambiarColor(RED);
		aux.cambiarTamaño(pNodo.darTamanio());
		short nuevoTamanio = (short) (sizeAux(pNodo.darNodoIzquierda())+sizeAux(pNodo.darNodoDerecha())+1);
		pNodo.cambiarTamaño(nuevoTamanio);
		return aux;
	}
	public void intercambiarColores(NodoArbol<K, V> pNodo)
	{
		pNodo.cambiarColor(RED);
		pNodo.darNodoIzquierda().cambiarColor(BLACK);
		pNodo.darNodoDerecha().cambiarColor(BLACK);
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
	public int getHeightRaiz()
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
	public Iterator<K> keysInRange(K pLlaveBaja, K pLlaveAlta)
	{
		Stack<K> stack = new Stack<K>();
		if(pLlaveAlta.compareTo(raiz.darLlave())<0)
		{
			boolean encontradoLimite = false;
			Stack<K> llaves = leftKeys(raiz);
			for(int i=0;i<llaves.darTamanio()&&!encontradoLimite;i++)
			{
				K llave = llaves.pop();
				if(llave.compareTo(pLlaveBaja)<0)
				{
					encontradoLimite = true;
				}
				if(llave.compareTo(pLlaveBaja)>=0)
				{
					stack.push(llave);
				}
			}
		}
		else if(pLlaveAlta.compareTo(raiz.darLlave())>0)
		{
			boolean encontradoLimite = false;
			Stack<K> llaves = rightKeys(raiz);
			for(int i=0;i<llaves.darTamanio()&&!encontradoLimite;i++)
			{
				K llave = llaves.pop();
				if(llave.compareTo(pLlaveBaja)<0)
				{
					encontradoLimite = true;
				}
				if(llave.compareTo(pLlaveBaja)>=0)
				{
					stack.push(llave);
				}		
			}
		}
		else if(pLlaveAlta.compareTo(raiz.darLlave())==0)
		{
			boolean encontradoLimite = false;
			Iterator<K> llavess = keys();
			while(llavess.hasNext()&&!encontradoLimite)
			{
				K llaveaux = llavess.next();
				if(llaveaux.compareTo(pLlaveBaja)<0)
				{
					encontradoLimite = true;
				}
				if(llaveaux.compareTo(pLlaveBaja)>=0)
				{
					stack.push(llaveaux);
				}		
			}
		}
		return stack.iterator();
	}
	public Iterator<V> valuesInRange(K pLlaveBaja, K pLlaveAlta)
	{
		Iterator<K> llaves = keysInRange(pLlaveBaja, pLlaveAlta);
		Stack<V> valoresEnRango = new Stack<V>();
		while(llaves.hasNext())
		{
			K llave = llaves.next();
			valoresEnRango.push(get(llave));
		}
		return valoresEnRango.iterator();
	}
	public boolean check()
	{
		return esSimetrico() && esConsistente() && esBalanceado() && es23();
	}
	public boolean esSimetrico()
	{
		return esSimetricoAux(raiz, null, null);
	}
	public boolean esSimetricoAux(NodoArbol<K, V> pNodo, K pMinimo, K pMaximo)
	{
		if(pNodo==null)
		{
			return true;
		}
		if(pMinimo != null && pNodo.darLlave().compareTo(pMinimo) <= 0)
		{
			return false;
		}
		if(pMaximo !=null && pNodo.darLlave().compareTo(pMaximo) >= 0)
		{
			return false;
		}
		return esSimetricoAux(pNodo.darNodoIzquierda(), pMinimo, pNodo.darLlave()) && esSimetricoAux(pNodo.darNodoDerecha(), pNodo.darLlave(), pMaximo);
	}
	public boolean esConsistente()
	{
		return esConsistenteAux(raiz);
	}
	public boolean esConsistenteAux(NodoArbol<K, V> pNodo)
	{
		if(pNodo==null)
		{
			return true;
		}
		if(pNodo.darTamanio() != sizeAux(pNodo.darNodoIzquierda())+ sizeAux(pNodo.darNodoDerecha())+1)
		{
			return false;
		}
		return esConsistenteAux(pNodo.darNodoIzquierda()) && esConsistenteAux(pNodo.darNodoDerecha());
	}
	public boolean esBalanceado()
	{
		int negro = 0;
		NodoArbol<K, V> aux = raiz;
		while(aux!=null)
		{
			if(aux.darColor()!=RED)
			{
				negro++;
				aux = aux.darNodoIzquierda();
			}
		}
		return esBalanceadoAux(raiz, negro);
	}
	public boolean esBalanceadoAux(NodoArbol<K, V> pNodo, int pNegro)
	{
		if(pNodo==null)
		{
			return pNegro ==0;
		}
		if(pNodo.darColor()!=RED)
		{
			pNegro--;
		}
		return esBalanceadoAux(pNodo.darNodoIzquierda(), pNegro) && esBalanceadoAux(pNodo.darNodoDerecha(), pNegro);
	}
	public boolean es23()
	{
		return es23Aux(raiz);
	}
	public boolean es23Aux(NodoArbol<K, V> pNodo)
	{
		if(pNodo==null)
		{
			return true;
		}
		if(isRed(pNodo.darNodoDerecha()))
		{
			return false;
		}
		if(pNodo != raiz && isRed(pNodo) && isRed(pNodo.darNodoIzquierda()))
		{
			return false;
		}
		return es23Aux(pNodo.darNodoIzquierda()) && es23Aux(pNodo.darNodoDerecha());
	}
}
