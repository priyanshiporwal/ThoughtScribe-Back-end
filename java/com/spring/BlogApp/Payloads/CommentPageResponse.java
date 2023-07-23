package com.spring.BlogApp.Payloads;

import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.BlogApp.Dto.CommentDto;

@Component
public class CommentPageResponse {

	private List<CommentDto>commentDtos;
	private boolean last;
	private boolean first;
	private long totalPages;
	private int currentPage;
	public List<CommentDto> getCommentDtos() {
		return commentDtos;
	}
	public void setCommentDtos(List<CommentDto> commentDtos) {
		this.commentDtos = commentDtos;
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
	public CommentPageResponse(List<CommentDto> commentDtos, boolean last, boolean first, long totalPages,
			int currentPage) {
		super();
		this.commentDtos = commentDtos;
		this.last = last;
		this.first = first;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
	}
	public CommentPageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
}
