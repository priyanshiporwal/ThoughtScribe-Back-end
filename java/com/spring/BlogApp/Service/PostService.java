package com.spring.BlogApp.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.spring.BlogApp.Dto.PostDto;
import com.spring.BlogApp.Payloads.PostPageResponse;

public interface PostService {

	PostPageResponse getAllPosts(int pageNo,int pageSize,String sortDir,String sortBy);
	PostDto getPostById(int id);
	void deletePost(int id);
	PostDto updatePost(PostDto postDto,int id);
	PostDto createPost(PostDto postDto,int userId,int categoryId);
	String uploadImage(MultipartFile multipartFile,String path,int postId) throws IOException;
	InputStream getImage(String path,String imgName) throws FileNotFoundException;
	PostPageResponse getPostByCategory(int pageNo,int pageSize,String sortDir,String sortBy,int categoryId);
	PostPageResponse getPostByUser(int pageNo,int pageSize,String sortDir,String sortBy,int userId);
	PostPageResponse getPostByCategoryAndUser(int pageNo,int pageSize,String sortDir,String sortBy,int categoryId,int userId);
}
