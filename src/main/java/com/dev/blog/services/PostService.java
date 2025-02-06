package com.dev.blog.services;

import com.dev.blog.domain.CreatePostRequest;
import com.dev.blog.domain.UpdatePostRequest;
import com.dev.blog.domain.entities.Post;
import com.dev.blog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {

    Post getPost(UUID id);
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDrafts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
}
