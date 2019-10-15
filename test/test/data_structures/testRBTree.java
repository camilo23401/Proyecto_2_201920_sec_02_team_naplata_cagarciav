package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import model.data_structures.ArbolRojoNegro;

public class testRBTree 
{
	private ArbolRojoNegro<Integer, Integer> arbol;
	
	public void setUp()
	{
		arbol = new ArbolRojoNegro<Integer, Integer>();
	}
	public void setUp2()
	{
		arbol = new ArbolRojoNegro<Integer, Integer>();
		arbol.put(2, 3);
	}
	@Test
	public void testArbol()
	{
		setUp();
		assertTrue(arbol!=null);
	}
	@Test
	public void testPut()
	{
		setUp();
		arbol.put(2, 5);
		arbol.put(3, 6);
		arbol.put(1, 7);
		assertEquals(1,arbol.getHeightRaiz());
	}
	@Test
	public void testSize()
	{
		setUp();
		arbol.put(3, 4);
		arbol.put(4, 6);
		arbol.put(2, 5);
		arbol.put(1, 7);
		arbol.put(5, 2);   
		assertEquals(5, arbol.size(), 0);
	}
	@Test
	public void testGet()
	{
		setUp();
		arbol.put(5, 6);
		assertEquals(6, arbol.get(5), 0);
		arbol.put(4, 3);
		assertEquals(3, arbol.get(4), 0);
		assertTrue(arbol.get(2)==null);
	}
	@Test
	public void testHeight()
	{
		setUp();
		arbol.put(1, 5);
		arbol.put(2, 2);
		arbol.put(3, 7);
		assertEquals(1, arbol.getHeightRaiz(), 0);
	}
	@Test
	public void testMin()
	{
		setUp();
		arbol.put(1, 5);
		arbol.put(2, 2);
		arbol.put(3, 7);
		assertEquals(1, arbol.min(), 0);
	}
	@Test
	public void testMax()
	{
		setUp();
		arbol.put(1, 5);
		arbol.put(2, 2);
		arbol.put(3, 7);
		assertEquals(3, arbol.max(), 0);
	}
	@Test
	public void testKeys()
	{
		setUp();
		arbol.put(1, 5);
		arbol.put(2, 2);
		arbol.put(3, 7);
		
		Iterator<Integer> rta = arbol.keys();
		Integer valor = rta.next();
		assertEquals(3, valor,0);
		valor = rta.next();
		assertEquals(1, valor,0);	
	}
	@Test
	public void testIsEmpty()
	{
		setUp();
		assertTrue(arbol.isEmpty());
		setUp2();
		assertFalse(arbol.isEmpty());
	}
	@Test
	public void testCheck()
	{
		setUp();
		arbol.put(1, 5);
		arbol.put(2, 2);
		arbol.put(3, 7);
		arbol.put(4, 3);
		assertTrue(arbol.check());
	}
}
