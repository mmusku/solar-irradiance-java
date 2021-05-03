package com.solarIrradiance.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

public class AvgFull {
	private Avg avg_dni = null;
	private Avg avg_ghi = null;
	private Avg avg_lat_tilt = null;
	public Avg getAvg_dni() {
		return avg_dni;
	}
	public void setAvg_dni(Avg avg_dni) {
		this.avg_dni = avg_dni;
	}
	public Avg getAvg_ghi() {
		return avg_ghi;
	}
	public void setAvg_ghi(Avg avg_ghi) {
		this.avg_ghi = avg_ghi;
	}
	public Avg getAvg_lat_tilt() {
		return avg_lat_tilt;
	}
	public void setAvg_lat_tilt(Avg avg_lat_tilt) {
		this.avg_lat_tilt = avg_lat_tilt;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("avg_dni: ");
		if(this.avg_dni != null) {
			sb.append(this.avg_dni.toString());	
		}
		
		sb.append("avg_ghi: ");
		sb.append(this.avg_ghi.toString());
		if(this.avg_ghi != null) {
			sb.append(this.avg_ghi.toString());	
		}
		sb.append("avg_lat_tilt: ");
		if(this.avg_lat_tilt != null) {
			sb.append(this.avg_lat_tilt.toString());	
		}
		
		return sb.toString();
	}
	
	
	public static AvgFull parseToObject(String p_data) throws Exception {
		final String OUTPUTS = "outputs";
		final String NO_DATA = "no data";
		
//		avg_dni": "no data",
		AvgFull avgfull = null;		
		JSONParser parser = new JSONParser(); 
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(p_data);
			Gson gson = new Gson();	
			if(json.containsKey(OUTPUTS)) {
				avgfull = new AvgFull();
				JSONObject output = (JSONObject)json.get(OUTPUTS); 
				if(output.get("avg_dni").equals(NO_DATA) ){
					System.out.println("Have no data");
				}
				else {
					avgfull = gson.fromJson(output.toString(), AvgFull.class);	
				}
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return avgfull;
	}
}
