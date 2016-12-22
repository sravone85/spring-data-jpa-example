package com.prokarma.com.controller;

import com.prokarma.com.domain.Post;
import com.prokarma.com.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	private PostService postService;
	
	@Autowired
	public PostController(PostService postService){
		this.postService = postService;
	}
	
	@RequestMapping("/")
	public Iterable<Post> list(){
		return postService.list();
	}
	
	@RequestMapping("/byAuthor/{first}")
	public List<Post> byAuthor( @PathVariable(value="first") String first ) {
		return postService.byAuthor(first);
	}
	
	@RequestMapping("/byKeyword/{keyword}")
	public List<Post> byKeyword( @PathVariable(value="keyword") String keyword ) {
		return postService.byKeyword(keyword);
	}
	
	@RequestMapping("/active")
	public List<Post> active() {
		return postService.findActive();
	}
	
	@RequestMapping("/slug/{slug}")
	public Post findPostBySlug(@PathVariable(value="slug") String slug ) {
		return postService.findBySlug(slug);
	}

	@RequestMapping("/futurePosts")
	public List<Post> findFuturePosts(){
		return postService.findByPostedOnFuture();

	}
}
