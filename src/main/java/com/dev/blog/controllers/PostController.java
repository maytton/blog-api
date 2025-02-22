package com.dev.blog.controllers;

import com.dev.blog.domain.CreatePostRequest;
import com.dev.blog.domain.UpdatePostRequest;
import com.dev.blog.domain.dtos.CreatePostRequestDTO;
import com.dev.blog.domain.dtos.PostDTO;
import com.dev.blog.domain.dtos.UpdatePostRequestDTO;
import com.dev.blog.domain.entities.Post;
import com.dev.blog.domain.entities.User;
import com.dev.blog.mappers.PostMapper;
import com.dev.blog.services.PostService;
import com.dev.blog.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID tagId) {
        List<Post> posts = postService.getAllPosts(categoryId, tagId);
        List<PostDTO> postDTOs = posts.stream().map(postMapper::toDTO).toList();
        return ResponseEntity.ok(postDTOs);
    }

    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDTO>> getDrafts(@RequestAttribute UUID userId) {
        User loggedUser = userService.getUserById(userId);
        List<Post> draftPosts = postService.getDrafts(loggedUser);
        List<PostDTO> postDTOs = draftPosts.stream().map(postMapper::toDTO).toList();
        return ResponseEntity.ok(postDTOs);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(
            @RequestBody CreatePostRequestDTO createPostRequestDTO,
            @RequestAttribute UUID userId
    ) {

        User loggedUser = userService.getUserById(userId);
        CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(createPostRequestDTO);
        Post createdPost = postService.createPost(loggedUser, createPostRequest);
        PostDTO createdPostDTO = postMapper.toDTO(createdPost);
        return new ResponseEntity<>(createdPostDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDTO> updatePost(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePostRequestDTO updatePostRequestDTO
    ) {
        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(updatePostRequestDTO);
        Post updatePost = postService.updatePost(id, updatePostRequest);
        PostDTO updatePostDTO = postMapper.toDTO(updatePost);
        return ResponseEntity.ok(updatePostDTO);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostDTO> getPost(
            @PathVariable UUID id
    ) {
        Post post = postService.getPost(id);
        PostDTO postDTO = postMapper.toDTO(post);
        return ResponseEntity.ok(postDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }


    public ResponseEntity<PostDTO> publishPost(@PathVariable UUID id) {
        Post publishedPost = postService.publishPost(id);
        PostDTO publishedPostDTO = postMapper.toDTO(publishedPost);
        return ResponseEntity.ok(publishedPostDTO);
    }
}
