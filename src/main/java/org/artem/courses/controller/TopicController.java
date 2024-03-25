package org.artem.courses.controller;

import org.artem.courses.dto.TopicDTO;
import org.artem.courses.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/courses/{course_uuid}/topics")
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResponseEntity<List<TopicDTO>> getAllTopics(@PathVariable("course_uuid") UUID courseUuid) {
        return new ResponseEntity<>(topicService.getAllDto(courseUuid), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TopicDTO> getTopicById(@PathVariable("uuid") UUID uuid, @PathVariable("course_uuid") UUID courseUuid) {
        TopicDTO topic = topicService.getByUuidDto(uuid);
        if (topic != null) {
            return new ResponseEntity<>(topic, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TopicDTO> updateTopic(@PathVariable("course_uuid") UUID courseUuid, @RequestBody TopicDTO topicDTO) {
        return ResponseEntity.ok(topicService.update(topicDTO));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Void> deleteTopic(@PathVariable("uuid") UUID uuid, @PathVariable("course_uuid") UUID courseUuid) {
        topicService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}

