package com.solarIrradiance.analytic;

import java.util.Map;

import com.solarIrradiance.model.Avg;
import com.solarIrradiance.model.AvgFull;

public class MinAnnually implements IAnalytic {

	private AvgFull _data;
	private Map<String, Object> _params;
	private String _siteName;
	
	public MinAnnually(AvgFull p_data,  Map<String, Object> p_params, String p_siteName){
		this._data = p_data;
		this._params = p_params;
		this._siteName = p_siteName;
	}
	/**
	 * find Min annually (avg_ghi, None)
	 */
	@Override
	public void runAction() {
		float min = Float.MAX_VALUE;
		if(validate(this._params)) {
			String metric = (String) this._params.get("Metric");
			Avg avg = AnalitycUtil.sgetMatricByKey(metric, this._data);
			System.out.println(String.format("%s annual = %f", this._siteName, avg.getAnnual()) );
		
		}
		else {
			System.out.println("The params are not valid");
		}
		
	}

	/**
	 * validate input params:
	 * Valid input: 
	 * {
	 * 	"Metric": "avg_ghi"
	 * } 
	 */
	@Override
	public boolean validate(Map<String, Object> p_params) {
		
		if(p_params.containsKey("Metric")) {
			String metric = (String) p_params.get("Metric");
			if(metric != null) {// && metric.equals("avg_ghi")) {
				return true;
			}
		}
		return false;
	}

}
