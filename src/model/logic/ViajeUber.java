package model.logic;

public class ViajeUber implements Comparable <ViajeUber>
{
	private int sourceid;
	private int dstid;
	private short mes;
	private short dia;
	private short hora;
	private double tiempoPromedio;
	private double desviacionEstandarTiempo;
	private double tiempoPromedioGeometrico;
	private double desviacionEstandarTiempoGeometrico;
	private short trimestre;


	public ViajeUber(int pSourceid, int pDstid, short pHora, double pTiempoPromedio, short pMes, short pDia, double pDesviacionEstandarTiempo, double pTiempoPromedioGeometrico, double pDesviacionEstandarTiempoGeometrico,short pTrimestre)
	{
		sourceid = pSourceid;
		dstid = pDstid;
		mes = pMes;
		dia = pDia;
		hora = pHora;
		tiempoPromedio = pTiempoPromedio;
		desviacionEstandarTiempo = pDesviacionEstandarTiempo;
		tiempoPromedioGeometrico = pTiempoPromedioGeometrico;
		desviacionEstandarTiempoGeometrico = pDesviacionEstandarTiempoGeometrico;
		trimestre=pTrimestre;
	}

	public int darSourceid()
	{
		return sourceid;
	}
	public int darDstid()
	{
		return dstid;
	}
	public short darMes()
	{
		return mes;	
	}
	public short darDia()
	{
		return dia;
	}
	public short darHora()
	{
		return hora;
	}
	public double darMeanTravelTime()
	{
		return tiempoPromedio;
	}
	public double darDesviacionEstandarTiempo()
	{
		return desviacionEstandarTiempo;
	}
	public double darTiempoPromedioGeometrico()
	{
		return tiempoPromedioGeometrico;
	}
	public double darDesviacionEstandarTiempoGeometrico()
	{
		return desviacionEstandarTiempoGeometrico;
	}
	@Override
	public int compareTo(ViajeUber comp) {
		double comparacion = this.darMeanTravelTime()-comp.darMeanTravelTime();
		int compa=0;
		if(comparacion > 0){
			compa=1;
		}
		else if(comparacion < 0){
			compa=-1;
		}
		else{
			double comparacion2=this.darDesviacionEstandarTiempo()-comp.darDesviacionEstandarTiempo();
			if(comparacion2 > 0){
				compa=1;
			}
			else if(comparacion2 < 0){
				compa=-1;
			}
		}
		return compa;
	}
	public int compareToZonal(ViajeUber comp) {
		double comparacion = this.darSourceid()-comp.darSourceid();
		int compa=0;
		if(comparacion > 0){
			compa=1;
		}
		else if(comparacion < 0){
			compa=-1;
		}

		return compa;
	}
	public short darTrimestre() {
		return trimestre;
	}
}
