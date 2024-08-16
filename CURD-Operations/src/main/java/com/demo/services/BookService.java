package com.demo.services;

import com.demo.dto.BookDto;
import com.demo.exception.BookAlreadyExistsException;
import com.demo.exception.BookNotFoundException;
import com.demo.entities.BookEntity;
import com.demo.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper mapper;

    public void saveBook(BookDto bookDto){
        if(bookRepository.findById(bookDto.getBookId()).isPresent()) {
            throw new BookAlreadyExistsException("Book is already present with ID : " + bookDto.getBookId());
        }
        mapper.map(bookRepository.save(mapper.map(bookDto,BookEntity.class)), BookDto.class);
    }

    public List<BookDto> getAllBooks(Integer pageNo){
        Pageable page = PageRequest.of(pageNo, 5);
        List<BookEntity> books = bookRepository.findAll(page).getContent();
        return books.stream().map(book -> mapper.map(book, BookDto.class)).toList();
    }

    public String deleteBook(Integer id){
        bookRepository.deleteById(id);
        return "Book deleted successfully...";
    }

    public BookDto getBookById(Integer id){
        Optional<BookEntity> book = bookRepository.findById(id);

        if(book.isEmpty()){
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        return mapper.map(book, BookDto.class);
    }

    public BookDto updateBook(BookDto book){
        BookEntity bookPresent = new BookEntity();
        Optional<BookEntity> optional = bookRepository.findById(book.getBookId());
        if(optional.isPresent()) {
            bookPresent = optional.get();
            bookPresent.setBookName(book.getBookName());
            bookPresent.setBookAuthor(book.getBookAuthor());
            bookPresent.setBookPrize(book.getBookPrize());
        }
        return mapper.map(bookRepository.save(bookPresent),BookDto.class);
    }

    public List<BookEntity> getBookByTitle(String title) {
        return bookRepository.getBookByTitle(title);
    }


    public String saveBooks(List<BookDto> books) {
        if(books.isEmpty()) throw new BookNotFoundException("Book list cannot empty");

        bookRepository.saveAll(books.stream().map(dto->mapper.map(dto,BookEntity.class)).toList());
        return "Books Saved Successfully...";
    }
}
