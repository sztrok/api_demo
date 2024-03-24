package com.example.work_demo.components;

import com.example.work_demo.utils.JSONHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class PostsDataHandler {

	private final String postsUrl;
	private final String dirPath;

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

}
