package org.artem.courses.service.impl.ram;

import org.artem.courses.entity.Topic;
import org.artem.courses.service.TopicService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class RamTopicServiceTest {
    @Test
    void shouldCreateNewTopic() {
        //given
        RamManager ramManager = new RamManager();
        TopicService topicService = new RamTopicService(ramManager);
        Topic topic = new Topic();
        topic.setName("Тест топик");
        assertThat(topic.getId()).isNull();

        //when
        Topic updatedTopic = topicService.update(topic);

        //then
        assertThat(updatedTopic.getName()).isEqualTo(topic.getName());
        assertThat(updatedTopic.getId()).isEqualTo(0);
        assertThat(topic.getId()).isEqualTo(0);
        assertThat(updatedTopic).isEqualTo(topic);
        assertThat(ramManager.getTopics()).hasSize(1).contains(Map.entry(0, topic));
    }

    @Test
    void shouldUpdateTopic() {

        //given
        RamManager ramManager = new RamManager();
        TopicService topicService = new RamTopicService(ramManager);
        Topic old = new Topic();
        old.setName("Тест топик");
        old.setId(5);
        ramManager.getTopics().put(5, old);
        Topic updatedTopic = new Topic();
        updatedTopic.setName("новое имя");
        updatedTopic.setId(5);

        //when
        Topic resultTopic = topicService.update(updatedTopic);

        //then
        assertThat(resultTopic.getName()).isEqualTo(updatedTopic.getName());
        assertThat(resultTopic.getId()).isEqualTo(updatedTopic.getId());
        assertThat(updatedTopic.getId()).isEqualTo(5);
        assertThat(resultTopic).isEqualTo(updatedTopic);
        assertThat(ramManager.getTopics()).hasSize(1).contains(Map.entry(5, updatedTopic));
    }

    @Test
    void shouldDeleteTopicById() {
        //given
        RamManager ramManager = new RamManager();
        TopicService topicService = new RamTopicService(ramManager);
        Topic topic = new Topic();
        topic.setName("Тест топик");
        topicService.update(topic);

        //when
        topicService.delete(topic.getId());

        //then
        assertThat(ramManager.getTopics()).hasSize(0);
    }

    @Test
    void shouldReturnTopicById() {
        RamManager ramManager = new RamManager();
        TopicService topicService = new RamTopicService(ramManager);
        Topic topic1 = new Topic();
        topic1.setName("Тест топик 777");
        Topic UpdateTopic = topicService.update(topic1);

        //when
        Topic getTopic = topicService.getById(UpdateTopic.getId());

        //given
        assertThat(UpdateTopic.getId()).isEqualTo(getTopic.getId());
        assertThat(UpdateTopic).isEqualTo(getTopic);
        assertThat(getTopic).isNotNull();
    }

    @Test
    void shouldReturnNullWhenThereIsNoSuchTopic() {
        // given
        RamManager ramManager = new RamManager();
        TopicService topicService = new RamTopicService(ramManager);

        // when
        Topic topic = topicService.getById(999);

        //then
        assertThat(topic).isNull();
    }

    @Test
    void shouldReturnAllTopics() {
        // given
        RamManager ramManager = new RamManager();
        TopicService topicService = new RamTopicService(ramManager);
        Topic topic1 = new Topic();
        topic1.setName("Тест топик 239");
        Topic topic2 = new Topic();
        topic2.setName("Тест топик 236");
        Topic topic3 = new Topic();
        topic3.setName("Тест топик 23");
        topicService.update(topic1);
        topicService.update(topic2);
        topicService.update(topic3);

        // when
        List<Topic> Topics = topicService.getAll();

        // then
        assertThat(Topics).hasSize(3);
    }

}
