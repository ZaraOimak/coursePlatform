package org.artem.courses.service.impl.ram;

import org.artem.courses.entity.Topic;
import org.artem.courses.service.TopicService;
import org.junit.jupiter.api.Test;

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
}
