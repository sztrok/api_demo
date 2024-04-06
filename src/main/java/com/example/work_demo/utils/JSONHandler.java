package com.example.work_demo.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;


public final class JSONHandler {
	private static final JSONParser parser = new JSONParser();

	private JSONHandler() {
	}

	public static void saveDataAsJson(final String dirPath,final String data){
		final ArrayList<JSONObject> jsonObjects = getJsonObjectsFromString(data);
		saveJsonObjectsToFiles(dirPath, jsonObjects);
	}

	public static void test(final String data){
		final ArrayList<JSONObject> jsonObjects = getJsonObjectsFromString(data);
		method(jsonObjects);
	}


	public static JSONObject readDataFromJsonFile(final String dirPath, final String id) {
		String filePath = dirPath + "\\"+ id + ".json";
		String fileContent;
		try{
			fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
			return (JSONObject) parser.parse(fileContent);
		}
		catch (Exception e){
			System.out.println("Error reading file: " + filePath);
			return new JSONObject();
		}
	}


	private static ArrayList<JSONObject> getJsonObjectsFromString(final String data){
		return parseString(data);
	}

	private static ArrayList<JSONObject> parseString(final String data){
		ArrayList<JSONObject> jsonObjects = new ArrayList<>();
		try{
			if(parser.parse(data) instanceof JSONObject jsonObject){
                jsonObjects.add(jsonObject);
			}
			else{
				jsonObjects.addAll(getJsonObjectsFromJsonArray((JSONArray) parser.parse(data)));
			}
		}
		catch (final ParseException e){
			e.printStackTrace();
		}
		return jsonObjects;
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

	public static void method(final ArrayList<JSONObject> jsonObjects){
		HashSet<String> uniqueUserIds = new HashSet<>();
		for(final JSONObject jsonObject : jsonObjects){
			uniqueUserIds.add(jsonObject.get("userId").toString());
		}
		System.out.println(uniqueUserIds);
		for(String id : uniqueUserIds){
			String pathName = ".\\Data\\"+id;
			new File(pathName).mkdirs();
		}
		for(final JSONObject jsonObject : jsonObjects){
			final String postId = jsonObject.get("id").toString();
			final String userId = jsonObject.get("userId").toString();
			final String dirName = ".\\Data\\"+userId;
			final String fileName = postId + ".json";
			try(final FileWriter fileWriter = new FileWriter(new File(dirName, fileName))){
				fileWriter.write(jsonObject.toString());
			}
			catch (IOException exception){
				exception.printStackTrace();
			}
		}
	}

}
