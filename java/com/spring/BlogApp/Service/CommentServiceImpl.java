package com.spring.BlogApp.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.spring.BlogApp.Dto.CommentDto;
import com.spring.BlogApp.Exception.ResourceNotFoundException;
import com.spring.BlogApp.Models.Comment;
import com.spring.BlogApp.Models.Post;
import com.spring.BlogApp.Models.User;
import com.spring.BlogApp.Payloads.CommentPageResponse;
import com.spring.BlogApp.Respository.CommentRepo;
import com.spring.BlogApp.Respository.PostRepo;
import com.spring.BlogApp.Respository.UserRepo;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private CommentPageResponse commentPageResponse;
	
	@Autowired
	private PostRepo postRepo;

	@Override
	public CommentPageResponse getAllComments(int pageNo, int pageSize, String sortDir, String sortBy) {
		Pageable pageable = sortDir.equalsIgnoreCase("asc") ? PageRequest.of(pageNo, pageSize, Sort.by(sortBy))
				: PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
		Page<Comment> pageComments = commentRepo.findAll(pageable);
		List<Comment> comments = pageComments.getContent();
		long totalPages = pageComments.getTotalPages();
		int currentPage = pageComments.getNumber();
		boolean last = pageComments.isLast();
		boolean first = pageComments.isFirst();
		List<CommentDto> commentDtos = comments.stream().map((comment) -> modelMapper.map(comment, CommentDto.class))
				.collect(Collectors.toList());
		commentPageResponse.setCommentDtos(commentDtos);
		commentPageResponse.setCurrentPage(currentPage);
		commentPageResponse.setFirst(first);
		commentPageResponse.setLast(last);
		commentPageResponse.setTotalPages(totalPages);
		return commentPageResponse;
	}

	@Override
	public CommentDto getCommentById(int id) {
		Comment comment = commentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("comment not found for commentId " + id));
		return modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public void deleteComment(int id) {
		Comment comment = commentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("comment not found for commentId " + id));
		commentRepo.delete(comment);

	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, int id) {
		Comment comment = commentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("comment not found for commentId " + id));
		comment.setContent(commentDto.getContent());
		commentRepo.save(comment);
		return modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public CommentDto createComment(CommentDto commentDto, int userId,int postId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for userId " + userId));
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not found for postId "+postId));
		Comment comment = modelMapper.map(commentDto, Comment.class);
		comment.setDate(new Date());
		comment.setUser(user);
		comment.setPost(post);
		commentRepo.save(comment);
		CommentDto commentDto2=modelMapper.map(comment,CommentDto.class);
		//commentDto2.setPostDto(modelMapper.map(post, PostDto.class));
		return commentDto2;

	}

	@Override
	public CommentPageResponse getAllCommentsOnPost(int pageNo,int pageSize,String sortDir,String sortBy, int postId) {
		// TODO Auto-generated method stub
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not found for postId "+postId));
		Pageable pageable = sortDir.equalsIgnoreCase("asc") ? PageRequest.of(pageNo, pageSize, Sort.by(sortBy))
				: PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
		Page<Comment>page=commentRepo.findByPost(pageable,post);
		List<Comment>comments=page.getContent();
		long totalPages = page.getTotalPages();
		int currentPage = page.getNumber();
		boolean last = page.isLast();
		boolean first = page.isFirst();
		List<CommentDto>commentDtos=comments.stream().map((comment)->modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
		commentPageResponse.setCurrentPage(currentPage);
		commentPageResponse.setFirst(first);
		commentPageResponse.setLast(last);
		commentPageResponse.setTotalPages(totalPages);
		commentPageResponse.setCommentDtos(commentDtos);
		return commentPageResponse;
	}
	

}
