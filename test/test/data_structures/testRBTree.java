package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.data_structures.ArbolRojoNegro;

public class testRBTree 
{
	private ArbolRojoNegro<Integer, Integer> arbol;
	
	public void setUp()
	{
		arbol = new ArbolRojoNegro<Integer, Integer>();
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
		assertEquals(1,arbol.getHeight());
	}
	@Test
	public void testSize()
	{
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
	public void testGetHeight()
	{
		setUp();
		arbol.put(1, 5);
		arbol.put(2, 2);
		arbol.put(3, 7);
		assertEquals(2, arbol.getHeight(), 0);
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
}
