package com.example.work_demo.components;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;


class PostsDataHandlerTest {

	@Test
	void checkIfFileIsCreated() {
		String url = "https://jsonplaceholder.typicode.com/posts/1";
		PostsDataHandler dataHandler = new PostsDataHandler(url);
		dataHandler.savePostsDataFromApi();
		File f = new File(".\\Data","1.json");
		Assertions.assertTrue(f.exists());
		f.delete();
	}

	@Test
	void checkIfAllFilesAreCreated() {
		boolean allFilesExist = true;
		PostsDataHandler dataHandler = new PostsDataHandler();
		dataHandler.savePostsDataFromApi();
		for(int i=1; i<=100; i++){
			String fileName = i+".json";
			File f = new File(".\\Data",fileName);
			if(!f.exists()){
				allFilesExist = false;
			}
			f.delete();
		}
		Assertions.assertTrue(allFilesExist);
	}
}