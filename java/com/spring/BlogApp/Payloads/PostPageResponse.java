package com.spring.BlogApp.Payloads;

import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.BlogApp.Dto.PostDto;

@Component
public class PostPageResponse {
	private List<PostDto>postDtos;
	private boolean last;
	private boolean first;
	private long totalPages;
	private int currentPage;
	public PostPageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostPageResponse(List<PostDto> postDtos, boolean last, boolean first, long totalPages, int currentPage) {
		super();
		this.postDtos = postDtos;
		this.last = last;
		this.first = first;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
	}
	public List<PostDto> getPostDtos() {
		return postDtos;
	}
	public void setPostDtos(List<PostDto> postDtos) {
		this.postDtos = postDtos;
	}
	public boolean isLast() {
		return last;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
	public boolean isFirst() {
		return first;
	}
	public void setFirst(boolean first) {
		this.first = first;
	}
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	

}
