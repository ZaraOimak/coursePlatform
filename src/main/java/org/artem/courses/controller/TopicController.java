package org.artem.courses.controller;

import org.artem.courses.dto.BlockDTO;
import org.artem.courses.dto.ResourceDTO;
import org.artem.courses.dto.TopicDTO;
import org.artem.courses.entity.*;
import org.artem.courses.service.CourseService;
import org.artem.courses.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/courses/{course_id}/topics")
public class TopicController {
    private final TopicService topicService;
    private final CourseService courseService;

    @Autowired
    public TopicController(TopicService topicService, CourseService courseService) {
        this.topicService = topicService;
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<TopicDTO>> getAllTopics(@PathVariable("course_id") int courseId) {
        List<Topic> topics = topicService.getAll(courseId);
        List<TopicDTO> topicDTOS = new ArrayList<>();
        for (Topic topic : topics){
            topicDTOS.add(transform(topic));
        }
        return new ResponseEntity<>(topicDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getTopicById(@PathVariable("id") int id, @PathVariable("course_id") int courseId) {
        Topic topic = topicService.getById(id);
        if (topic != null) {
            return new ResponseEntity<>(transform(topic), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TopicDTO> createTopic(@PathVariable("course_id") int courseId, @RequestBody TopicDTO topicDTO) {

        Topic createdTopic = topicService.create(courseId,transform(topicDTO, courseService.getById(courseId)));
        return new ResponseEntity<>(transform(createdTopic), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable("id") int id, @PathVariable("course_id") int courseId) {
        topicService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private TopicDTO transform(Topic topic) {
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setId(topic.getId());
        topicDTO.setSectionId(topic.getSection().getId());
        topicDTO.setName(topic.getName());
        topicDTO.setDescription(topic.getDescription());
        topicDTO.setOrder(topic.getOrder());

        List<BlockDTO> blocks = new ArrayList<>();
        for (Block block : topic.getBlocks()) {
            blocks.add(transform(block));
        }
        topicDTO.setBlocks(blocks);

        if (topic.getNext() != null) {
            topicDTO.setNextTopicId(topic.getNext().getId());
        }
        if (topic.getPrevious() != null) {
            topicDTO.setPreviousTopicId(topic.getPrevious().getId());
        }
        return topicDTO;
    }

    private BlockDTO transform(Block block) {
        BlockDTO blockDTO = new BlockDTO();
        blockDTO.setId(block.getId());
        blockDTO.setOrder(block.getOrder());
        List<ResourceDTO> resources = new ArrayList<>();
        for (Resource resource : block.getResources()) {
            resources.add(transform(resource));
        }

        blockDTO.setResources(resources);
        return blockDTO;
    }

    private ResourceDTO transform(Resource resource) {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId(resource.getId());
        resourceDTO.setOrder(resource.getOrder());
        resourceDTO.setContent(resource.getContent());
        resourceDTO.setResourceType(resource.getResourceType());
        return resourceDTO;
    }

    private Topic transform(TopicDTO topicDTO, Course course) {
        Topic topic = new Topic();
        topic.setId(topicDTO.getId());

        topic.setSection(findSection(topicDTO.getSectionId(), course));
        topic.setName(topicDTO.getName());
        topic.setDescription(topicDTO.getDescription());
        List<Block> blocks = new ArrayList<>();
        for (BlockDTO blockDTO : topicDTO.getBlocks()) {
            blocks.add(transform(blockDTO, topic));
        }
        topic.setBlocks(blocks);
        topic.setOrder(topicDTO.getOrder());

        if (topicDTO.getNextTopicId() != null) {
            topic.setNext(topicService.getById(topicDTO.getNextTopicId()));
        }
        if (topicDTO.getPreviousTopicId() != null) {
            topic.setPrevious(topicService.getById(topicDTO.getPreviousTopicId()));
        }
        return topic;
    }

    private Block transform(BlockDTO blockDTO, Topic topic) {
        Block block = new Block();
        block.setId(blockDTO.getId());
        block.setTopic(topic);
        block.setOrder(blockDTO.getOrder());
        List<Resource> resources = new ArrayList<>();
        for (ResourceDTO resourceDTO : blockDTO.getResources()) {
            resources.add(transform(resourceDTO, block));
        }
        block.setResources(resources);
        return block;
    }

    private Resource transform(ResourceDTO resourceDTO, Block block) {
        Resource resource = new Resource();
        resource.setId(resourceDTO.getId());
        resource.setOrder(resourceDTO.getOrder());
        resource.setContent(resourceDTO.getContent());
        resource.setResourceType(resourceDTO.getResourceType());
        resource.setParent(block);
        return resource;
    }

    private Section findSection(Integer sectionId, Course course) {
        for (Section section : course.getSections()) {
            if (Objects.equals(section.getId(), sectionId)) {
                return section;
            }
        }
        return null;
    }
}

