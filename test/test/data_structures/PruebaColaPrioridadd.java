package test.data_structures;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.data_structures.MaxColaCP;
import model.data_structures.MaxHeapCP;
import model.logic.ViajeUber;

public class PruebaColaPrioridadd {
	private MaxHeapCP<ViajeUber> arregloColaHeap;
	private MaxColaCP<ViajeUber> arreglo;
	
	@Before
	public void setUp1() throws IOException {
		arregloColaHeap=new MaxHeapCP<ViajeUber>(11);
		arreglo=new MaxColaCP<ViajeUber>();
		ViajeUber a=new ViajeUber(0,0,Short.parseShort("0"), 9.0,Short.parseShort("0"), Short.parseShort("0"), 0.0,0.0, 0.0, Short.parseShort("0"));
		ViajeUber b=new ViajeUber(0,0,Short.parseShort("0"), 14.0,Short.parseShort("0"), Short.parseShort("0"), 0.0,0.0, 0.0, Short.parseShort("0"));
		ViajeUber c=new ViajeUber(0,0,Short.parseShort("0"), 5.0,Short.parseShort("0"), Short.parseShort("0"), 0.0,0.0, 0.0, Short.parseShort("0"));
		ViajeUber d=new ViajeUber(0,0,Short.parseShort("0"), 3.0,Short.parseShort("0"), Short.parseShort("0"), 0.0,0.0, 0.0, Short.parseShort("0"));
		ViajeUber e=new ViajeUber(0,0,Short.parseShort("0"), 20.0,Short.parseShort("0"), Short.parseShort("0"), 0.0,0.0, 0.0, Short.parseShort("0"));
		ViajeUber f=new ViajeUber(0,0,Short.parseShort("0"), 1.0,Short.parseShort("0"), Short.parseShort("0"), 0.0,0.0, 0.0, Short.parseShort("0"));
		ViajeUber g=new ViajeUber(0,0,Short.parseShort("0"), 45.0,Short.parseShort("0"), Short.parseShort("0"), 0.0,0.0, 0.0, Short.parseShort("0"));
		ViajeUber h=new ViajeUber(0,0,Short.parseShort("0"), 17.0,Short.parseShort("0"), Short.parseShort("0"), 0.0,0.0, 0.0, Short.parseShort("0"));
		ViajeUber i=new ViajeUber(0,0,Short.parseShort("0"), 7.0,Short.parseShort("0"), Short.parseShort("0"), 0.0,0.0, 0.0, Short.parseShort("0"));
		ViajeUber j=new ViajeUber(0,0,Short.parseShort("0"), 9.0,Short.parseShort("0"), Short.parseShort("0"), 0.0,0.0, 0.0, Short.parseShort("0"));	
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
