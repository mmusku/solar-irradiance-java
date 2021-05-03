package com.solarIrradiance.analytic;

import com.solarIrradiance.model.Avg;
import com.solarIrradiance.model.AvgFull;

public class AnalitycUtil {

	static Avg sgetMatricByKey(String p_metric, AvgFull p_data) {
		switch(p_metric) {
			case "avg_dni":
				return p_data.getAvg_dni();
			case "avg_lat_tilt":
				return  p_data.getAvg_lat_tilt();
			case "avg_ghi":{
				return p_data.getAvg_ghi();
			}
		}
		return null;
	}
}
