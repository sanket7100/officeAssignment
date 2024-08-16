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

    @PostMapping("/saveBook")
    public ResponseEntity<String> saveBook(@RequestBody @Valid BookDto book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Validation violation");
        }else bookService.saveBook(book);
        return ResponseEntity.ok("Book saved successfully");
    }

    @GetMapping("/getAllBooks/{pageNo}")
    public ResponseEntity<List<BookDto>> getAllBooks(@PathVariable Integer pageNo){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks(pageNo));
    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.deleteBook(id));
    }

    @GetMapping("/getBookById/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Integer bookId){
        return ResponseEntity.ok().body(bookService.getBookById(bookId));
    }

    @PutMapping("/updateBook")
    public ResponseEntity<String> updateBook(@RequestBody BookDto book){
        if(bookService.updateBook(book)!=null) return ResponseEntity.ok().body("Book Updated Successfully!");

        return ResponseEntity.badRequest().body("Something went wrong........Try again later");
    }

    @GetMapping("/getBookByTitle/{title}")
    public ResponseEntity<List<BookEntity>> getBookByTitle(@PathVariable String title){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookByTitle(title));
    }

    @PostMapping("/saveBooks")
    public ResponseEntity<String> saveBooks(@RequestBody List<BookDto> books){
        return ResponseEntity.ok().body(bookService.saveBooks(books));
    }

}
