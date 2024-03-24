package com.example.work_demo.controllers;

import com.example.work_demo.components.PostsDataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

}
