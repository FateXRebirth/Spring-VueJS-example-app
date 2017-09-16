package com.adeveloperdiary.controller;

import com.adeveloperdiary.entities.Post;
import com.adeveloperdiary.repositories.PostRepository;
import com.adeveloperdiary.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by FateXRebirth on 14/09/2017.
 */
@RestController
public class Controller {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/")
    public String index() {
        return "Hello World";
    }

    @GetMapping(value = "/posts")
    public List<Post> posts() {
        return postService.getAllPosts();
    }

    @PostMapping(value = "/post")
    public void publishPost(@RequestBody Post post) {
        if(post.getDate() == null) {
            post.setDate(new Date());
        }
        postService.insert(post);
    }

}
