package com.dev.blog.controllers;

import com.dev.blog.domain.dtos.CreateTagRequest;
import com.dev.blog.domain.dtos.TagDTO;
import com.dev.blog.domain.entities.Tag;
import com.dev.blog.mappers.TagMapper;
import com.dev.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagDTO>> getAll() {
        List<Tag> tags = tagService.getTags();
        List<TagDTO> tagRespons = tags.stream().map(tagMapper::toTagResponse).toList();
        return ResponseEntity.ok(tagRespons);
    }

    @PostMapping
    public ResponseEntity<List<TagDTO>> createTags(@RequestBody CreateTagRequest createTagRequest) {
        List<Tag> savedTags = tagService.createTags(createTagRequest.getNames());
        List<TagDTO> createdTagRespons = savedTags.stream().map(tagMapper::toTagResponse).toList();
        return new ResponseEntity<>(
                createdTagRespons,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id){
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
