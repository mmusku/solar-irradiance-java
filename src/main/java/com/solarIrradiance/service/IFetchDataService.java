package com.solarIrradiance.service;

import java.util.List;

import org.apache.http.NameValuePair;

public interface IFetchDataService {
	String fetchData(String p_host, List<NameValuePair> p_params);
}
