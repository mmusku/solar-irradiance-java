package com.solarIrradiance.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Site implements ISite {
	private String sitename;
	private String longitude;
	private String latitude;
	
	public String getSiteName() {
		return sitename;
	}
	public void setSiteName(String p_sitename) {
		this.sitename = p_sitename;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String p_longitude) {
		this.longitude = p_longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String p_latitude) {
		this.latitude = p_latitude;
	}
	
	public Site(@JsonProperty("siteName") String p_sitename,
            	@JsonProperty("longitude") String p_longitude,
            	@JsonProperty("latitude") String p_latitude){

		this.sitename = p_sitename;
		this.longitude = p_longitude;
		this.latitude = p_latitude;
	}
}
