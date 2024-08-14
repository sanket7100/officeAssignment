package com.demo.services;

import com.demo.DTOs.BookDto;
import com.demo.Exception.BookNotFoundException;
import com.demo.entities.BookEntity;
import com.demo.repositories.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper mapper;

    public BookDto saveBook(BookDto bookDto){
//        BookEntity bookEntity = new BookEntity();
        BookEntity savedBookEntity =  bookRepository.save(mapper.map(bookDto,BookEntity.class));
//        BookDto savedBookDto = new BookDto();
//        BookDto savedBookDto = mapper.map(savedBookEntity,BookDto.class);
        return mapper.map(savedBookEntity, BookDto.class);
    }

    public List<BookDto> getAllBooks(Integer pageno){
        Pageable page = PageRequest.of(pageno, 5);
        List<BookEntity> books = bookRepository.findAll(page).getContent();
        return books.stream().map(book -> mapper.map(book, BookDto.class)).collect(Collectors.toList());
//        return Collections.singletonList(mapper.map(books, BookDto.class));
    }

    public BookDto deleteBook(Integer id){
        BookEntity deletedBook = bookRepository.findById(id).get();
        bookRepository.deleteById(id);
        return mapper.map(deletedBook,BookDto.class);
    }

    public BookDto getBookById(Integer id){
        Optional<BookEntity> book = bookRepository.findById(id) ;

        if(book.isEmpty()){
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        return mapper.map(book, BookDto.class);
    }

    public BookDto updateBook(BookDto book){
        BookEntity bookPresent = null;
        Optional<BookEntity> optional = bookRepository.findById(book.getBookId());
        if(optional.isPresent()) {
            bookPresent = optional.get();
            bookPresent.setBookName(book.getBookName());
            bookPresent.setBookAuther(book.getBookAuther());
            bookPresent.setBookPrize(book.getBookPrize());
        }

        return mapper.map(bookRepository.save(bookPresent),BookDto.class);
    }

    public List<BookEntity> getBookByTitle(String title) {
        return bookRepository.getBookByTitle(title);
    }
}
