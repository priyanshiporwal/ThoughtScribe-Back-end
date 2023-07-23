package com.spring.BlogApp.Controller;

import java.util.List;

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

import com.spring.BlogApp.Dto.CommentDto;
import com.spring.BlogApp.Payloads.CommentPageResponse;
import com.spring.BlogApp.Service.CommentServiceImpl;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*")
public class CommentController {

	@Autowired
	private CommentServiceImpl commentServiceImpl;

	@GetMapping("/all")
	public ResponseEntity<CommentPageResponse> getAllComments(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "asc") String sortDir,
			@RequestParam(defaultValue = "id") String sortBy) {
		CommentPageResponse commentPageResponse = commentServiceImpl.getAllComments(pageNo, pageSize, sortDir, sortBy);
		return new ResponseEntity<CommentPageResponse>(commentPageResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable int id) {
		CommentDto commDto = commentServiceImpl.getCommentById(id);
		return new ResponseEntity<CommentDto>(commDto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable int id) {
		commentServiceImpl.deleteComment(id);
		return new ResponseEntity<String>("Comment deleted successfully", HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @RequestParam int id) {
		CommentDto commentDto2 = commentServiceImpl.updateComment(commentDto, id);
		return new ResponseEntity<CommentDto>(commentDto2, HttpStatus.OK);
	}

	@PostMapping("/create/user/{userId}/post/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int userId,@PathVariable int postId) {
		CommentDto commentDto2 = commentServiceImpl.createComment(commentDto, userId,postId);
		return new ResponseEntity<CommentDto>(commentDto2, HttpStatus.CREATED);
	}
	
	@GetMapping("/post/{id}")
	public ResponseEntity<CommentPageResponse> getCommentByPostId(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "asc") String sortDir,
			@RequestParam(defaultValue = "id") String sortBy,@PathVariable int id){
		CommentPageResponse commentPageResponse= commentServiceImpl.getAllCommentsOnPost(pageNo,pageSize,sortDir,sortBy,id);
		return new ResponseEntity<CommentPageResponse>(commentPageResponse,HttpStatus.OK);
	}
}
