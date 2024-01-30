package org.artem.courses.service.impl.ram;
import org.artem.courses.entity.Course;
import org.artem.courses.entity.Section;
import org.artem.courses.entity.Topic;
import org.artem.courses.service.CourseService;
import org.artem.courses.service.TopicService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class RamCourseServiceTest {
    @Test
    void shouldCreateNewCourse() {
        // given
        RamManager ramManager = new RamManager();
        TopicService topicService = new RamTopicService(ramManager);
        CourseService courseService = new RamCourseService(ramManager, topicService);
        Course course = new Course();
        course.setName("Тест курс");
        assertThat(course.getId()).isNull();

        // when
        Course updatedCourse = courseService.update(course);

        // then
        assertThat(updatedCourse.getName()).isEqualTo(course.getName());
        assertThat(updatedCourse.getId()).isEqualTo(0);
        assertThat(course.getId()).isEqualTo(0);
        assertThat(updatedCourse).isEqualTo(course);
        assertThat(ramManager.getCourses()).hasSize(1).containsValue(updatedCourse);
    }

    @Test
    void shouldUpdateCourse() {
        // given
        RamManager ramManager = new RamManager();
        TopicService topicService = new RamTopicService(ramManager);
        CourseService courseService = new RamCourseService(ramManager, topicService);
        Course old = new Course();
        old.setName("Тест курс");
        old.setId(5);
        ramManager.getCourses().put(5, old);
        Course updatedCourse = new Course();
        updatedCourse.setName("Новое имя");
        updatedCourse.setId(5);

        // when
        Course resultCourse = courseService.update(updatedCourse);

        // then
        assertThat(resultCourse.getName()).isEqualTo(updatedCourse.getName());
        assertThat(resultCourse.getId()).isEqualTo(updatedCourse.getId());
        assertThat(updatedCourse.getId()).isEqualTo(5);
        assertThat(resultCourse).isEqualTo(updatedCourse);
        assertThat(ramManager.getCourses()).hasSize(1).containsValue(updatedCourse);
    }

    @Test
    void shouldNotDeleteCourseWithoutId() {
        // given
        RamManager ramManager = new RamManager();
        TopicService topicService = new RamTopicService(ramManager);
        CourseService courseService = new RamCourseService(ramManager, topicService);
        Course course = new Course();
        course.setName("Тест курс");

        // when
        courseService.delete(null);

        // then
        assertThat(ramManager.getCourses()).isEmpty();
        assertThat(ramManager.getTopics()).isEmpty();
    }

    @Test
    void shouldReturnNullWhenThereIsNoSuchCourse() {
        // given
        RamManager ramManager = new RamManager();
        TopicService topicService = new RamTopicService(ramManager);
        CourseService courseService = new RamCourseService(ramManager, topicService);

        // when
        Course course = courseService.getById(9889);

        // then
        assertThat(course).isNull();
    }

    @Test
    void shouldReturnAllCourses() {
        // given
        RamManager ramManager = new RamManager();
        TopicService topicService = new RamTopicService(ramManager);
        CourseService courseService = new RamCourseService(ramManager, topicService);

        Course course1 = new Course();
        course1.setName("Тест курс 1");
        Course course2 = new Course();
        course2.setName("Тест курс 2");

        courseService.update(course1);
        courseService.update(course2);

        // when
        List<Course> allCourses = courseService.getAll();

        // then
        assertThat(allCourses).hasSize(2);
    }
}
