package com.demo.controller;

import com.demo.dto.BookDto;
import com.demo.entities.BookEntity;
import com.demo.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class Controller {

    private final BookService bookService;

    @PostMapping("/savebook")
    public ResponseEntity<String> saveBook(@RequestBody @Valid BookDto book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Validation violation");
        }else bookService.saveBook(book);
        return ResponseEntity.ok("Book saved successfully");
    }

    @GetMapping("/getallbooks/{pageno}")
    public ResponseEntity<List<BookDto>> getAllBooks(@PathVariable Integer pageno){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks(pageno));
    }

    @DeleteMapping("/deletebookbyid/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.deleteBook(id));
    }

    @GetMapping("/getbookbyid/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Integer bookId){
        return ResponseEntity.ok().body(bookService.getBookById(bookId));
    }

    @PutMapping("/updatebook")
    public ResponseEntity<String> updateBook(@RequestBody BookDto book){
        bookService.updateBook(book);
        return ResponseEntity.ok().body("Book Updated Succefully!");
    }

    @GetMapping("/getbookbytitle/{title}")
    public ResponseEntity<List<BookEntity>> getBookByTitle(@PathVariable String title){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookByTitle(title));
    }

    @PostMapping("/savebooks")
    public ResponseEntity<String> saveBooks(@RequestBody List<BookDto> books){
        return ResponseEntity.ok().body(bookService.saveBooks(books));
    }

}
