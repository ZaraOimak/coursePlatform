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
import java.util.UUID;


@RestController
@RequestMapping("/courses/{course_uuid}/topics")
public class TopicController {
    private final TopicService topicService;
    private final CourseService courseService;

    @Autowired
    public TopicController(TopicService topicService, CourseService courseService) {
        this.topicService = topicService;
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<TopicDTO>> getAllTopics(@PathVariable("course_uuid") UUID courseUuid) {
        List<Topic> topics = topicService.getAll(courseUuid);
        List<TopicDTO> topicDTOS = new ArrayList<>();
        for (Topic topic : topics){
            topicDTOS.add(transform(topic));
        }
        return new ResponseEntity<>(topicDTOS, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TopicDTO> getTopicById(@PathVariable("uuid") UUID uuid, @PathVariable("course_uuid") UUID courseUuid) {
        Topic topic = topicService.getByUuid(uuid);
        if (topic != null) {
            return new ResponseEntity<>(transform(topic), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TopicDTO> updateTopic(@PathVariable("course_uuid") UUID courseUuid, @RequestBody TopicDTO topicDTO) {

        Topic createdTopic = topicService.update(transform(topicDTO, courseService.getByUuid(courseUuid)));
        return new ResponseEntity<>(transform(createdTopic), HttpStatus.CREATED);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Void> deleteTopic(@PathVariable("uuid") UUID uuid, @PathVariable("course_uuid") UUID courseUuid) {
        topicService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private TopicDTO transform(Topic topic) {
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setUuid(topic.getUuid());
        topicDTO.setSectionUuid(topic.getSection().getUuid());
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
        blockDTO.setUuid(block.getUuid());
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
        resourceDTO.setUuid(resource.getUuid());
        resourceDTO.setOrder(resource.getOrder());
        resourceDTO.setContent(resource.getContent());
        resourceDTO.setResourceType(resource.getResourceType());
        return resourceDTO;
    }

    private Topic transform(TopicDTO topicDTO, Course course) {
        Topic topic = new Topic();
        topic.setUuid(topicDTO.getUuid());

        topic.setSection(findSection(topicDTO.getSectionUuid(), course));
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
        block.setUuid(blockDTO.getUuid());
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
        resource.setUuid(resourceDTO.getUuid());
        resource.setOrder(resourceDTO.getOrder());
        resource.setContent(resourceDTO.getContent());
        resource.setResourceType(resourceDTO.getResourceType());
        resource.setParent(block);
        return resource;
    }

    private Section findSection(UUID sectionUuid, Course course) {
        for (Section section : course.getSections()) {
            if (Objects.equals(section.getUuid(), sectionUuid)) {
                return section;
            }
        }
        return null;
    }
}

