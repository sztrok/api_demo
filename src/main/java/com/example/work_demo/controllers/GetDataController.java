package com.example.work_demo.controllers;

import com.example.work_demo.components.PostsDataHandler;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class GetDataController {

	private final PostsDataHandler postsDataHandler;

	@Autowired
	public GetDataController(final PostsDataHandler postsDataHandler){
		this.postsDataHandler = postsDataHandler;
	}

	@GetMapping(value = "/get_posts")
	public String getPostsFromApi() {
		postsDataHandler.savePostsDataFromApi();
		return "Data has been successfully saved";
	}

	@GetMapping(value = "/get_grouped_posts")
	public String getGroupedPostsFromApi() {
		postsDataHandler.saveAndGroupPostsDataFromApi();
		return "Data has been successfully saved and grouped";
	}

	@GetMapping(value = "/show_post/{id}")
	public JSONObject showPost(@PathVariable String id) {
		return postsDataHandler.getPostById(id);
	}

	@GetMapping(value = "/list_downloaded_files")
	public List<JSONObject> getListOfDownloadedFiles() {
		return postsDataHandler.getDirectoryContents();
	}

}
