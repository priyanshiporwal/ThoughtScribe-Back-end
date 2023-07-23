package com.spring.BlogApp.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.BlogApp.Dto.CommentDto;
import com.spring.BlogApp.Dto.PostDto;
import com.spring.BlogApp.Payloads.CommentPageResponse;

public interface CommentService {

	CommentPageResponse getAllComments(int pageNo,int pageSize,String sortDir,String sortBy);
	CommentDto getCommentById(int id);
	void deleteComment(int id);
	CommentDto updateComment(CommentDto commentDto,int id);
	CommentDto createComment(CommentDto commentDto,int userId,int postId);
//	List<CommentDto> getAllCommentsOnPost(int postId);
	CommentPageResponse getAllCommentsOnPost(int pageNo,int pageSize,String sortDir,String sortBy,int postId);
}
