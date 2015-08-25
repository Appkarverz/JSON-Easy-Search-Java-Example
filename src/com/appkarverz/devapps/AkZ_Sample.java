package com.appkarverz.devapps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import appkarverz.devapps.jsonsearch.AkZ_JsonSearcher;

public class AkZ_Sample {

	private static final String strKey = "dt";
	private static final String strJSONURL = "http://api.openweathermap.org/data/2.5/weather?q=india";

	private static JSONObject jsonObj;
	private static ArrayList<Object> arraylist_ReturnValues;

	public static void main(String[] args) {

		intialise();

		try {
			// loading JSON from URL(for test purpose)
			jsonObj = readJsonFromUrl(strJSONURL);
			arraylist_ReturnValues = AkZ_JsonSearcher.getAllKeyValues(jsonObj, strKey);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}

		print();

	}

	private static void intialise() {

		jsonObj = new JSONObject();
		arraylist_ReturnValues = new ArrayList<>();

	}

	private static void print() {

		Iterator<Object> iterator = arraylist_ReturnValues.iterator();

		System.out.println("JSON URL:" + strJSONURL);
		System.out.println("\n");
		System.out.println("Complete JSON found at given URL");
		System.out.println(jsonObj);
		System.out.println("\n\n");
		System.out.println("Values found in given JSON URL\n");
		while (iterator.hasNext()) {
			Object object = iterator.next();
			System.out.println(object.toString());

		}

	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
}
