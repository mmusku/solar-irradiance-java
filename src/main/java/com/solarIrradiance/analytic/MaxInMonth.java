package com.solarIrradiance.analytic;

import java.util.List;
import java.util.Map;

import com.solarIrradiance.model.Avg;
import com.solarIrradiance.model.AvgFull;

public class MaxInMonth implements IAnalytic {
	private AvgFull _data;
	private Map<String, Object> _params;
	private String _siteName;
	
	public MaxInMonth(AvgFull p_data,  Map<String, Object> p_params, String p_siteName) {
		this._data = p_data;
		this._params  = p_params;
		this._siteName = p_siteName;
		
	}
	
	
	/**
	 * find Max in months
	 */
	@Override
	public void runAction() {
		float max = Float.MIN_VALUE;
		if(validate(this._params)) {
			String metric = (String) this._params.get("Metric");
			Avg avg = AnalitycUtil.sgetMatricByKey(metric, _data);
			List<String> monthes = (List<String>) this._params.get("months");
			StringBuilder sb = new StringBuilder();
			for (String month : monthes) {
				sb.append(avg.getMonthly().get(month));
				sb.append(" ");
				if(max < avg.getMonthly().get(month)) {
					max = avg.getMonthly().get(month);
				}
			}
			
			System.out.println(String.format("%s max of %s  is %s", this._siteName, sb.toString(),  max)  );
		}
		else {
			System.out.println("The params are not valid");
		}
		
	}

	

	/**
	 * validate input params:
	 * Valid input: 
	 * {
	 * 	"Metric": "avg_dni",
	 * 	"months:: ["dec", "oct", "apr", "aug"]  - minimum 1
	 * } 
	 */
	@Override
	public boolean validate(Map<String, Object> p_params) {
		
		if(p_params.containsKey("Metric")) {
			String metric = (String) p_params.get("Metric");
			if(metric != null){// && metric.equals("avg_dni")) {
				if(p_params.containsKey("months")) {
					List<String> months = (List<String>) p_params.get("months");
					if(months.size() > 0 ) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
