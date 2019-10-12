package test.data_structures;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.data_structures.MaxColaCP;
import model.data_structures.MaxHeapCP;
import model.logic.TravelTime;

public class PruebaColaPrioridadd {
	private MaxHeapCP<TravelTime> arregloColaHeap;
	private MaxColaCP<TravelTime> arreglo;
	
	@Before
	public void setUp1() throws IOException {
		arregloColaHeap=new MaxHeapCP<TravelTime>(11);
		arreglo=new MaxColaCP<TravelTime>();
		TravelTime a=new TravelTime(0, 0, 0, 0, 9, 0);
		TravelTime b=new TravelTime(0, 0, 0, 0, 14, 0);
		TravelTime c=new TravelTime(0, 0, 0, 0, 5, 0);
		TravelTime d=new TravelTime(0, 0, 0, 0, 3, 0);
		TravelTime e=new TravelTime(0, 0, 0, 0, 20, 0);
		TravelTime f=new TravelTime(0, 0, 0, 0, 1, 0);
		TravelTime g=new TravelTime(0, 0, 0, 0, 45, 0);
		TravelTime h=new TravelTime(0, 0, 0, 0, 17, 0);
		TravelTime i=new TravelTime(0, 0, 0, 0, 7, 0);
		TravelTime j=new TravelTime(0, 0, 0, 0, 9, 0);	
		arregloColaHeap.agregar(a);
		arreglo.agregar(a);
		arregloColaHeap.agregar(b);
		arreglo.agregar(b);
		arregloColaHeap.agregar(c);
		arreglo.agregar(c);
		arregloColaHeap.agregar(d);
		arreglo.agregar(d);
		arregloColaHeap.agregar(e);
		arreglo.agregar(e);
		arregloColaHeap.agregar(f);
		arreglo.agregar(f);
		arregloColaHeap.agregar(g);
		arreglo.agregar(g);
		arregloColaHeap.agregar(h);
		arreglo.agregar(h);
		arregloColaHeap.agregar(i);
		arreglo.agregar(i);
		arregloColaHeap.agregar(j);
		arreglo.agregar(j);

	}

	@Test
	//Se prueban los metodos sacar max y darmax de MaxColaCP
	public void testMaxCola() {
		
		Assert.assertTrue("Not equals", 45-arreglo.darMax().darMeanTravelTime() == 0);
		arreglo.sacarMax();
		Assert.assertTrue("Not equals", 20-arreglo.darMax().darMeanTravelTime() == 0);
		arreglo.sacarMax();
		Assert.assertTrue("Not equals", 17-arreglo.darMax().darMeanTravelTime() == 0);
		arreglo.sacarMax();
		Assert.assertTrue("Not equals", 14-arreglo.darMax().darMeanTravelTime() == 0);
		arreglo.sacarMax();
		Assert.assertTrue("Not equals", 9-arreglo.darMax().darMeanTravelTime() == 0);
		arreglo.sacarMax();
		
	}
	@Test
	//Se prueban los metodos sacar max y darmax de MaxHeapCP
	public void testMaxHeap() {
		
		Assert.assertTrue("Not equals", 45-arregloColaHeap.darMax().darMeanTravelTime() == 0);
		arregloColaHeap.sacarMax();
		Assert.assertTrue("Not equals", 20-arregloColaHeap.darMax().darMeanTravelTime() == 0);
		arregloColaHeap.sacarMax();
		Assert.assertTrue("Not equals", 17-arregloColaHeap.darMax().darMeanTravelTime() == 0);
		arregloColaHeap.sacarMax();
		Assert.assertTrue("Not equals", 14-arregloColaHeap.darMax().darMeanTravelTime() == 0);
		arregloColaHeap.sacarMax();
		Assert.assertTrue("Not equals", 9-arregloColaHeap.darMax().darMeanTravelTime() == 0);
		arregloColaHeap.sacarMax();
		
	}
}
