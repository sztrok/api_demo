package com.example.work_demo.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public final class JSONHandler {
	private static final JSONParser parser = new JSONParser();

	private JSONHandler() {
	}

	public static void saveDataAsJson(final String dirPath,final String data){
		final ArrayList<JSONObject> jsonObjects = getJsonObjectsFromString(data);
		saveJsonObjectsToFiles(dirPath, jsonObjects);
	}

	private static ArrayList<JSONObject> getJsonObjectsFromString(final String data){
		final JSONArray jsonArray = parseString(data);
		return getJsonObjectsFromJsonArray(jsonArray);
	}

	private static JSONArray parseString(final String data){
		try{
			return (JSONArray) parser.parse(data);
		}
		catch (final ParseException e){
			e.printStackTrace();
			return new JSONArray();
		}
	}

	private static ArrayList<JSONObject> getJsonObjectsFromJsonArray(final JSONArray jsonArray){
		final ArrayList<JSONObject> arrayList = new ArrayList<>();
		for(final Object array : jsonArray){
			arrayList.add((JSONObject) array);
		}
		return arrayList;
	}

	private static void saveJsonObjectsToFiles(final String dirPath, final ArrayList<JSONObject> jsonObjects){
		for(final JSONObject jsonObject : jsonObjects){
			final String filename = jsonObject.get("id").toString() + ".json";
			try(final FileWriter fileWriter = new FileWriter(new File(dirPath,filename))){
				fileWriter.write(jsonObject.toString());
			}
			catch (final IOException exception){
				System.out.println("IOException in DataHandler");
				exception.printStackTrace();
			}
		}
	}
}
