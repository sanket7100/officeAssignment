package com.demo.controller;

import com.demo.DTOs.BookDto;
import com.demo.entities.BookEntity;
import com.demo.services.BookService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/library")
public class Controller {
    @Autowired
    private BookService bookService;

    @PostMapping("/savebook")
    public ResponseEntity<String> saveBook(@RequestBody @Valid BookDto book, BindingResult bindingResult){
        BookDto bookDto = new BookDto();

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Validation violation");
        }else bookDto =  bookService.saveBook(book);
//        bookDto =  bookService.saveBook(book);
        return ResponseEntity.ok("Book saved successfully");
    }

    @GetMapping("/getallbooks/{pageno}")
    public ResponseEntity<List<BookDto>> getAllBooks(@PathVariable Integer pageno){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks(pageno));
    }

    @DeleteMapping("/deletebookbyid/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.deleteBook(id));
    }

    @GetMapping("/getbookbyid/{bookId}")
    public BookDto getBookById(@PathVariable Integer bookId){
        return bookService.getBookById(bookId);
    }

    @PutMapping("/updatebook")
    public void updateBook(@RequestBody BookDto book){

        bookService.updateBook(book);
    }

    @GetMapping("/getbookbytitle/{title}")
    public List<BookEntity> getBookByTitle(@PathVariable String title){
        return bookService.getBookByTitle(title);
    }

    @PostMapping("/savebooks")
    public ResponseEntity<String> saveBooks(@RequestBody List<BookDto> books){
        if(books != null){
            books.forEach(book -> {
                bookService.saveBook(book);
            });
            return ResponseEntity.ok("Books Saved Successfully!!!!");
        }
        return ResponseEntity.badRequest().body("Something went wrong");
    }

//    method for handling validation exceptions

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public Map<String, String> validationExceptionHandler(MethodArgumentNotValidException e){
//        Map<String, String> errors = new HashMap<>();
//
//        e.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMsg = error.getDefaultMessage();
//            errors.put(fieldName, errorMsg);
//        });
//        return errors;
//    }
}
