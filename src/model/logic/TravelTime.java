package model.logic;

public class TravelTime implements Comparable <TravelTime>
{
	private int trimestre;
	private int sourceid;
	private int distid;
	private int hod;
	private double mean_travel_time;
	private double standard_deviation_travel_time;

	public TravelTime (int pTrimestre, int pSourceid, int pDistid, int pHod, double pMean_time, double pStandard_deviation)
	{
		trimestre = pTrimestre;
		sourceid= pSourceid;
		distid = pDistid;
		hod = pHod;
		mean_travel_time = pMean_time;
		standard_deviation_travel_time = pStandard_deviation;
	}
	public int darTrimestre()
	{
		return trimestre;
	}
	public int darSourceid()
	{
		return sourceid;
	}
	public int darDistid()
	{
		return distid;
	}
	public int darHod()
	{
		return hod;
	}
	public double darMeanTravelTime()
	{
		return mean_travel_time;
	}
	public double darStandardDeviationTime()
	{
		return standard_deviation_travel_time;
	}
	public int compareTo(TravelTime comp) 
	{
		double comparacion = this.darMeanTravelTime()-comp.darMeanTravelTime();
		int compa=0;
		if(comparacion > 0)
		{
			compa=1;
		}
		else if(comparacion < 0)
		{
			compa=-1;
		}
		else{
			double comparacion2=this.darStandardDeviationTime()-comp.darStandardDeviationTime();
			if(comparacion2 > 0){
				compa=1;
			}
			else if(comparacion2 < 0){
				compa=-1;
			}
		}
		return compa;	
	}


}
