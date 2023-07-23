package com.spring.BlogApp.Controller;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.BlogApp.Dto.PostDto;
import com.spring.BlogApp.Payloads.ApiResponse;
import com.spring.BlogApp.Payloads.PostPageResponse;
import com.spring.BlogApp.Service.PostServiceImpl;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

	@Autowired
	private PostServiceImpl postServiceImpl;

	@GetMapping("/all")
	public ResponseEntity<PostPageResponse> getAllPosts(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "asc") String sortDir,
			@RequestParam(defaultValue = "id") String sortBy) {
		PostPageResponse postPageResponse = postServiceImpl.getAllPosts(pageNo, pageSize, sortDir, sortBy);
		return new ResponseEntity<PostPageResponse>(postPageResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int id) {
		PostDto postDto = postServiceImpl.getPostById(id);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int id) {
		postServiceImpl.deletePost(id);
		ApiResponse apiResponse=new ApiResponse("Post Deleted Successfully","200");
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @RequestParam int id) {
		PostDto postDto2 = postServiceImpl.updatePost(postDto, id);
		return new ResponseEntity<PostDto>(postDto2, HttpStatus.OK);
	}

	@PostMapping("/create/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId,
			@PathVariable int categoryId) {
		PostDto postDto2 = postServiceImpl.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(postDto2, HttpStatus.CREATED);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<PostPageResponse> getPostByCategory(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "asc") String sortDir,
			@RequestParam(defaultValue = "id") String sortBy,@PathVariable int categoryId){
		PostPageResponse postPageResponse=postServiceImpl.getPostByCategory(pageNo, pageSize, sortDir, sortBy, categoryId);
		return new ResponseEntity<PostPageResponse>(postPageResponse,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<PostPageResponse> getPostByUser(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "asc") String sortDir,
			@RequestParam(defaultValue = "id") String sortBy,@PathVariable int userId){
		PostPageResponse postPageResponse=postServiceImpl.getPostByUser(pageNo, pageSize, sortDir, sortBy, userId);
		return new ResponseEntity<PostPageResponse>(postPageResponse,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/user/{userId}")
	public ResponseEntity<PostPageResponse> getPostByCategoryAndUser(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "asc") String sortDir,
			@RequestParam(defaultValue = "id") String sortBy,@PathVariable int categoryId,@PathVariable int userId){
		PostPageResponse postPageResponse=postServiceImpl.getPostByCategoryAndUser(pageNo, pageSize, sortDir, sortBy, categoryId,userId);
		return new ResponseEntity<PostPageResponse>(postPageResponse,HttpStatus.OK);
	}
}
