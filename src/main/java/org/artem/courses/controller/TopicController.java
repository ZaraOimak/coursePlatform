package org.artem.courses.controller;

import org.artem.courses.entity.Topic;
import org.artem.courses.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/courses/{course_id}")
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> getAllTopics(@PathVariable("course_id") int courseId) {
        List<Topic> topics = topicService.getAll(courseId);
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable("id") int id, @PathVariable String course_id) {
        Topic topic = topicService.getById(id);
        if (topic != null) {
            return new ResponseEntity<>(topic, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{section_order}/topics")
    public ResponseEntity<Topic> createTopic(@PathVariable("course_id") int courseId,@PathVariable("section_order") int sectionOrder, @RequestBody Topic topic) {

        Topic createdTopic = topicService.create(courseId,sectionOrder, topic);
        return new ResponseEntity<>(createdTopic, HttpStatus.CREATED);
    }

    @PutMapping("topics/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable("id") int id, @RequestBody Topic updatedTopic, @PathVariable String course_id) {
        updatedTopic.setId(id);
        Topic resultTopic = topicService.update(updatedTopic);
        return new ResponseEntity<>(resultTopic, HttpStatus.OK);
    }

    @DeleteMapping("topics/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable("id") int id, @PathVariable String course_id) {
        topicService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

