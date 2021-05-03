package com.solarIrradiance.model;

import java.util.HashMap;
import java.util.Map;

public class Avg {
	private float annual;
	public float getAnnual() {
		return annual;
	}

	public void setAnnual(float annual) {
		this.annual = annual;
	}

	public Map<String, Float> getMonthly() {
		return monthly;
	}

	public void setMonthly(Map<String, Float> monthly) {
		this.monthly = monthly;
	}

	private Map<String, Float> monthly ;
	Avg(){
		this.monthly = new HashMap<> ();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append("annual: ");
		sb.append(annual);
		for(String key : monthly.keySet()) {
		    Float value = monthly.get(key);
		    sb.append(key);
		    sb.append(value);
		}
		
		
		return sb.toString();
	}
}


