package com.solarIrradiance.analytic;

import java.util.Map;

public interface IAnalytic {
	void runAction();
	boolean validate(Map<String, Object> p_params);
}
