package com.spring.BlogApp.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.swing.event.TableColumnModelListener;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.BlogApp.Dto.CommentDto;
import com.spring.BlogApp.Dto.PostDto;
import com.spring.BlogApp.Exception.ResourceNotFoundException;
import com.spring.BlogApp.Models.Category;
import com.spring.BlogApp.Models.Comment;
import com.spring.BlogApp.Models.Post;
import com.spring.BlogApp.Models.User;
import com.spring.BlogApp.Payloads.PostPageResponse;
import com.spring.BlogApp.Respository.CategoryRepo;
import com.spring.BlogApp.Respository.CommentRepo;
import com.spring.BlogApp.Respository.PostRepo;
import com.spring.BlogApp.Respository.UserRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostPageResponse postPageResponse;
	
	@Autowired
	private CommentRepo commentRepo;

	@Override
	public PostPageResponse getAllPosts(int pageNo, int pageSize,String sortDir,String sortBy) {
		Pageable pageable= (sortDir.equalsIgnoreCase("asc") ? PageRequest.of(pageNo, pageSize, Sort.by(sortBy)) : PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending()));
		Page<Post> posts = postRepo.findAll(pageable);
		List<Post> postList = posts.getContent();
		List<PostDto> postDtos = postList.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		long totalPages = posts.getTotalPages();
		int currentPage = posts.getNumber();
		boolean last = posts.isLast();
		boolean first = posts.isFirst();
		postPageResponse.setCurrentPage(currentPage);
		postPageResponse.setFirst(first);
		postPageResponse.setLast(last);
		postPageResponse.setTotalPages(totalPages);
		postPageResponse.setPostDtos(postDtos);
		return postPageResponse;
	}

	@Override
	public PostDto getPostById(int id) {
		Post post = postRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("post not found for postId " + id));
//		List<Comment>comments=commentRepo.findByPost(post);
//		List<CommentDto>commentDtos=comments.stream().map((comment)->modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
		PostDto postDto=modelMapper.map(post, PostDto.class);
		//postDto.setCommentDtos(commentDtos);
		return postDto;
	}

	@Override
	public void deletePost(int id) {
		Post post = postRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("post not found for postId " + id));
		postRepo.delete(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int id) {
		Post post = postRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("post not found for postId " + id));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		if(postDto.getImage()!=null) {
			post.setImage(postDto.getImage());
		}
		postRepo.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		Post post = modelMapper.map(postDto, Post.class);
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for postId " + userId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category not found for categoryId " + categoryId));
		post.setAddedDate(new Date());
		post.setImage("defaul.png");
		post.setUser(user);
		post.setCategory(category);
		postRepo.save(post);
		PostDto postDto2 = modelMapper.map(post, PostDto.class);
		return postDto2;
	}

	@Override
	public String uploadImage(MultipartFile multipartFile, String path, int postId) throws IOException {
		String imgName=multipartFile.getOriginalFilename();
		String tempId=UUID.randomUUID().toString();
		String fullPath=path+File.separator+imgName+tempId;
		File file=new File(path);
		if(!file.exists()) {
			file.mkdir();
		}
		Files.copy(multipartFile.getInputStream(), Paths.get(fullPath));
		return imgName+tempId;
	}

	@Override
	public InputStream getImage(String path, String imgName) throws FileNotFoundException {
		String fullPath=path+File.separator+imgName;
		InputStream inputStream=new FileInputStream(fullPath);
		return inputStream;
	}

	@Override
	public PostPageResponse getPostByCategory(int pageNo, int pageSize, String sortDir, String sortBy, int categoryId) {
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category Not found for categoryId "+categoryId));
		Pageable pageable=sortDir.equalsIgnoreCase("asc") ? PageRequest.of(pageNo, pageSize,Sort.by(sortBy)) : PageRequest.of(pageNo, pageSize,Sort.by(sortBy).descending());
		Page<Post>page=postRepo.findByCategory(pageable, category);
		List<Post>posts=page.getContent();
		List<PostDto>postDtos=posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		long totalPages = page.getTotalPages();
		int currentPage = page.getNumber();
		boolean last = page.isLast();
		boolean first = page.isFirst();
		postPageResponse.setPostDtos(postDtos);
		postPageResponse.setCurrentPage(currentPage);
		postPageResponse.setFirst(first);
		postPageResponse.setLast(last);
		postPageResponse.setTotalPages(totalPages);
		return postPageResponse;
	}

	@Override
	public PostPageResponse getPostByUser(int pageNo, int pageSize, String sortDir, String sortBy, int userId) {
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not found for userId "+userId));
		Pageable pageable=sortDir.equalsIgnoreCase("asc") ? PageRequest.of(pageNo, pageSize,Sort.by(sortBy)) : PageRequest.of(pageNo, pageSize,Sort.by(sortBy).descending());
		Page<Post>page=postRepo.findByUser(pageable, user);
		List<Post>posts=page.getContent();
		List<PostDto>postDtos=posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		long totalPages = page.getTotalPages();
		int currentPage = page.getNumber();
		boolean last = page.isLast();
		boolean first = page.isFirst();
		postPageResponse.setPostDtos(postDtos);
		postPageResponse.setCurrentPage(currentPage);
		postPageResponse.setFirst(first);
		postPageResponse.setLast(last);
		postPageResponse.setTotalPages(totalPages);
		return postPageResponse;
	}

	@Override
	public PostPageResponse getPostByCategoryAndUser(int pageNo, int pageSize, String sortDir, String sortBy,
			int categoryId, int userId) {
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not found for userId "+userId));
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category Not found for categoryId "+categoryId));
		Pageable pageable=sortDir.equalsIgnoreCase("asc") ? PageRequest.of(pageNo, pageSize,Sort.by(sortBy)) : PageRequest.of(pageNo, pageSize,Sort.by(sortBy).descending());
		Page<Post>page=postRepo.findByCategoryAndUser(pageable, category,user);
		List<Post>posts=page.getContent();
		List<PostDto>postDtos=posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		long totalPages = page.getTotalPages();
		int currentPage = page.getNumber();
		boolean last = page.isLast();
		boolean first = page.isFirst();
		postPageResponse.setPostDtos(postDtos);
		postPageResponse.setCurrentPage(currentPage);
		postPageResponse.setFirst(first);
		postPageResponse.setLast(last);
		postPageResponse.setTotalPages(totalPages);
		return postPageResponse;
	}

}
