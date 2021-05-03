package com.solarIrradiance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.solarIrradiance.analytic.IAnalytic;
import com.solarIrradiance.analytic.MaxInMonth;
import com.solarIrradiance.analytic.MinAnnually;
import com.solarIrradiance.model.AvgFull;
import com.solarIrradiance.model.Site;
import com.solarIrradiance.service.Config;
import com.solarIrradiance.service.FetchDataWithHttpClientService;
import com.solarIrradiance.service.ISolarService;
import com.solarIrradiance.service.SolarService;

public class App {

	public static void main(String[] args) {
		try {
			
			final String baseUrl = "developer.nrel.gov/api/solar/solar_resource/v1.json";
			final String url = "https://developer.nrel.gov/api/solar/solar_resource/v1.json";
			final String METRIC = "Metric";
			final String AVG_DNI = "avg_dni";
			final String AVG_LAT_TILT = "avg_lat_tilt";
			final String MONTHES = "months";

			// read sites data from config and add new sites into file config.json
			Config.load("config.json");
			Config.getInstance().addSites(new Site("CCC", "-102", "40"));

			// create service for fetching data
			ISolarService solarService = new SolarService(new FetchDataWithHttpClientService());
			List<IAnalytic> analytics = new ArrayList<IAnalytic>();
			List<NameValuePair> params = null;
			for (Site s : Config.getInstance().getSites()) {

				System.out.println(s.getSiteName());
				// create parameters for fetching data
				params = new ArrayList<>();
				params.add(new BasicNameValuePair("api_key", "DEMO_KEY"));
				params.add(new BasicNameValuePair("lat", s.getLatitude()));
				params.add(new BasicNameValuePair("lon", s.getLongitude()));

				// fetch data
				String response = solarService.apply(baseUrl, params);
				// parse response data to AvgFull object
				AvgFull avgfull = AvgFull.parseToObject(response);
				System.out.println(avgfull.toString());

				// Max in Month Analytic
				Map<String, Object> arg1 = new HashMap<String, Object>();
				List<String> mnth1 = new ArrayList<>();
				mnth1.add(EnumMonthes.dec.toString());
				mnth1.add(EnumMonthes.mar.toString());
				mnth1.add(EnumMonthes.aug.toString());
				arg1.put(METRIC, AVG_DNI);
				arg1.put(MONTHES, mnth1);
				IAnalytic analytic1 = new MaxInMonth(avgfull, arg1, s.getSiteName());
				analytics.add(analytic1);

				// Min Annually Analytic
				Map<String, Object> arg2 = new HashMap<String, Object>();
				arg2.put(METRIC, AVG_DNI);
				IAnalytic analitics2 = new MinAnnually(avgfull, arg2, s.getSiteName());
				analytics.add(analitics2);

				// Max in Month Analytic
				Map<String, Object> arg3 = new HashMap<String, Object>();
				List<String> mnth3 = new ArrayList<>();
				mnth3.add(EnumMonthes.mar.toString());
				mnth3.add(EnumMonthes.feb.toString());
				mnth3.add(EnumMonthes.jun.toString());
				mnth3.add(EnumMonthes.may.toString());
				mnth3.add(EnumMonthes.nov.toString());
				arg3.put(METRIC, AVG_LAT_TILT);
				arg3.put(MONTHES, mnth3);
				IAnalytic analytic3 = new MaxInMonth(avgfull, arg3, s.getSiteName());
				analytics.add(analytic3);
			}

			// run Analytic
			for (IAnalytic analytic : analytics) {
				analytic.runAction();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
