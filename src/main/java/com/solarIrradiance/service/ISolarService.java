package com.solarIrradiance.service;

import java.util.List;


import org.apache.http.NameValuePair;

public interface ISolarService {
	String apply(String p_url, List<NameValuePair> params);
}
