package org.artem.courses.controller;

import org.artem.courses.dto.AuthorDTO;
import org.artem.courses.entity.Author;
import org.artem.courses.entity.Course;
import org.artem.courses.service.AuthorService;
import org.artem.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final CourseService courseService;

    @Autowired
    public AuthorController(AuthorService authorService, CourseService courseService) {
        this.authorService = authorService;
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = authorService.getAll();
        List<AuthorDTO> authorsDTO = new ArrayList<>();
        for(Author author : authors){
            authorsDTO.add(transform(author));
        }
        return new ResponseEntity<>(authorsDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable("id") int id) {
        Author author = authorService.getById(id);
        if (author != null) {
            return new ResponseEntity<>(transform(author), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO updatedAuthorDTO) {
        Author resultAuthor = authorService.update(transform(updatedAuthorDTO));
        return new ResponseEntity<>(transform(resultAuthor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") int id) {
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private AuthorDTO transform(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(author.getName());
        authorDTO.setId(author.getId());
        List<Integer> coursesIds = new ArrayList<>();
        for (Course course : author.getCourses()) {
            coursesIds.add(course.getId());
        }
        authorDTO.setCoursesIds(coursesIds);
        return authorDTO;
    }

    private Author transform(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setId(authorDTO.getId());

        List<Course> courses = new ArrayList<>();
        for (Integer courseId : authorDTO.getCoursesIds()) {
            courses.add(courseService.getById(courseId));
        }
        author.setCourses(courses);
        return author;
    }
}
