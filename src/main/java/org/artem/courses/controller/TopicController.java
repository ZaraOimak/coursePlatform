package org.artem.courses.controller;

import org.artem.courses.entity.Topic;
import org.artem.courses.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> topics = topicService.getAll();
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(int id) {
        Topic topic = topicService.getById(id);
        if (topic != null) {
            return new ResponseEntity<>(topic, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Topic> createTopic(Topic topic) {
        Topic createdTopic = topicService.update(topic);
        return new ResponseEntity<>(createdTopic, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(int id,Topic updatedTopic) {
        updatedTopic.setId(id);
        Topic resultTopic = topicService.update(updatedTopic);
        return new ResponseEntity<>(resultTopic, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(int id) {
        topicService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

