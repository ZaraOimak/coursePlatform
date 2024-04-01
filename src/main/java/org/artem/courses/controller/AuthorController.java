package org.artem.courses.controller;

import org.artem.courses.dto.AuthorDTO;
import org.artem.courses.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return new ResponseEntity<>(authorService.getAllDto(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable("uuid") UUID uuid) {
        AuthorDTO author = authorService.getByUuidDto(uuid);
        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO updatedAuthorDTO) {
        return new ResponseEntity<>(authorService.update(updatedAuthorDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("uuid") UUID uuid) {
        authorService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
