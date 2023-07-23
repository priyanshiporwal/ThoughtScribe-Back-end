package com.spring.BlogApp.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.BlogApp.Dto.PostDto;
import com.spring.BlogApp.Service.PostServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*")
public class ImageController {

	@Value("${image.path}")
	private String path;

	@Autowired
	private PostServiceImpl postServiceImpl;

	@PostMapping("/image/{postId}")
	public String postImage(@RequestParam MultipartFile multipartFile, @PathVariable int postId) throws IOException {
		String imgName = postServiceImpl.uploadImage(multipartFile, path, postId);
		PostDto postDto = postServiceImpl.getPostById(postId);
		postDto.setImage(imgName);
		postServiceImpl.updatePost(postDto, postId);
		return imgName;

	}

	@GetMapping(value = "/public/get/image")
	public String getImage(@RequestParam String imgName, HttpServletResponse httpServletResponse) {
		InputStream inputStream = null;
		try {
			inputStream = postServiceImpl.getImage(path, imgName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
			e.printStackTrace();
		}
		String fullPath = path + File.separator + imgName;
		httpServletResponse.setContentType(MediaType.IMAGE_PNG_VALUE);
		try {
			StreamUtils.copy(inputStream, httpServletResponse.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fullPath;

	}

}
