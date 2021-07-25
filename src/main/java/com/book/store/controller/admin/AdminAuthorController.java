package com.book.store.controller.admin;

import com.book.store.controller.model.AuthorRequest;
import com.book.store.dto.AuthorDto;
import com.book.store.dto.Converter;
import com.book.store.model.Author;
import com.book.store.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminAuthorController {

    private AuthorService authorService;

    @Autowired
    public AdminAuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/author")
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody @Valid AuthorRequest authorRequest) {
        Author author = this.authorService.saveAuthor(authorRequest);
        return new ResponseEntity<>(Converter.authorToAuthorDto(author), HttpStatus.OK);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        List<Author> authorDtos = this.authorService.getAuthors();
        return new ResponseEntity<>(Converter.authorListToAuthorDtoList(authorDtos), HttpStatus.OK);
    }

    @GetMapping("/author")
    public ResponseEntity<AuthorDto> getAuthor(@RequestParam Long authorId) {
        Author author = this.authorService.getAuthor(authorId);
        return new ResponseEntity<>(Converter.authorToAuthorDto(author), HttpStatus.OK);
    }

    @PutMapping("/author")
    public ResponseEntity<AuthorDto> editAuthor(@RequestBody @Valid AuthorRequest authorRequest) {
        Author author = this.authorService.editAuthor(authorRequest);
        return new ResponseEntity<>(Converter.authorToAuthorDto(author), HttpStatus.OK);
    }

    @DeleteMapping("/author")
    public ResponseEntity<?> removeAuthor(@RequestParam Long authorId) {
        this.authorService.deleteAuthorById(authorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
