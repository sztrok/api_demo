package com.example.work_demo.components;

import com.example.work_demo.utils.JSONHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


@Component
public class PostsDataHandler {

	private final String postsUrl;
	private final String dirPath;
	private final JSONParser parser = new JSONParser();

	public PostsDataHandler() {
		this.postsUrl = "https://jsonplaceholder.typicode.com/posts";
		this.dirPath = ".\\Data";
	}

	public PostsDataHandler(String url) {
		this.postsUrl = url;
		this.dirPath = ".\\Data";
	}

	public void savePostsDataFromApi() {
		final RestTemplate restTemplate = new RestTemplate();
		final String posts = restTemplate.getForObject(postsUrl, String.class);
		JSONHandler.saveDataAsJson(dirPath, posts);
	}

	public void saveAndGroupPostsDataFromApi() {
		final RestTemplate restTemplate = new RestTemplate();
		final String posts = restTemplate.getForObject(postsUrl, String.class);
		JSONHandler.test(posts);
	}

	public JSONObject getPostById(String id) {
		return JSONHandler.readDataFromJsonFile(this.dirPath, id);
	}

	public List<JSONObject> getDirectoryContents() {
		ArrayList<Integer> postsIds = new ArrayList<>(Stream.of(Objects.requireNonNull(new File(this.dirPath).listFiles()))
				.filter(file -> !file.isDirectory())
				.map(File::getName)
				.map(name -> name.substring(0, name.length() - 5))
				.map(Integer::parseInt)
				.sorted()
				.toList());
		List<JSONObject> jsonObjects = new ArrayList<>();
 		for (Integer id : postsIds){
			String json = "{\"id\":" + id +", \"fileName\": " + "\"" + id + ".json\"}";
			try{
				jsonObjects.add((JSONObject) parser.parse(json));
			}
			catch (ParseException e){
				e.printStackTrace();
			}
		}
		return jsonObjects;
	}

}
