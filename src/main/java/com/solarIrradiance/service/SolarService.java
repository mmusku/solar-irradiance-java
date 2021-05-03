package com.solarIrradiance.service;

import java.util.List;

import org.apache.http.NameValuePair;

public class SolarService implements ISolarService {
	
//	private static final Logger logger = LogManager.getLogger(SolarService.class);
	private IFetchDataService _fetchSrv = null;
	
	
	
	public IFetchDataService getFetchSrv() {
		return _fetchSrv;
	}

	public void setFetchSrv(IFetchDataService _fetchSrv) {
		this._fetchSrv = _fetchSrv;
	}

	public SolarService(IFetchDataService p_fetchSrv) {
		this._fetchSrv = p_fetchSrv; 		
	}
	
	@Override
	public String apply(String p_host,  List<NameValuePair> p_params) {
		String result = this.getFetchSrv().fetchData(p_host, p_params);
		return result;
	}

	

	


}
