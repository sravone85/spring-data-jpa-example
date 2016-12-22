package com.prokarma.com.service;

import com.prokarma.com.domain.Post;
import com.prokarma.com.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class PostService {

	private PostRepository postRepository;


	
	@Autowired
	public PostService(PostRepository postRepository){
		this.postRepository = postRepository;
	}




	public Iterable<Post> list(){
		Page<Post> posts = postRepository.findAll(new PageRequest(1,2));
		return posts.getContent();
		//return postRepository.findAll();
	}
	
	// CUSTOM QUERIES

	public List<Post> byAuthor(String first){
		return postRepository.findAllByAuthorFirstNameIgnoreCaseOrderByPostedOnDesc(first);
	}

	public List<Post> byKeyword(String keyword) {
		return postRepository.findAllByKeywordsLikeIgnoreCase('%' + keyword + '%');
	}

	public List<Post> findActive() {
		return postRepository.findAllByActiveTrue();
	}

	public Post findBySlug(String slug) {
		return postRepository.findPostBySlugNative(slug);
	}

	public List<Post> findByPostedOnFuture(){
		Date now = new Date();
		Calendar calendar = new GregorianCalendar(/* remember about timezone! */);
		calendar.setTime(now);
		calendar.add(Calendar.DATE, 30);
		now = calendar.getTime();
		System.out.println("Start date: "+new Date());
		System.out.println("End date: "+now);
		return postRepository.findByPostedOnBetween(new Date(), now);
	}

	
}
