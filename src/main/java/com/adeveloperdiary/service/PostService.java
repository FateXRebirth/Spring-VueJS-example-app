package com.adeveloperdiary.service;

/**
 * Created by FateXRebirth on 16/09/2017.
 */

import com.adeveloperdiary.entities.Post;
import com.adeveloperdiary.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void insert(Post post) {
        postRepository.save(post);
    }
}
