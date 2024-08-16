package com.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDto {

    @Valid

    @NotNull(message = "Book Id should not be blank")
    private Integer bookId;

    @NotBlank(message = "Book Name should not be blank")
    private String bookName;

    @NotBlank(message = "Author Name should not be blank")
    private String bookAuthor;

    @Size(max = 50, message = "Max limit for description is 50")
    private String bookDescription;

    @Min(value = 1, message = "Price should not be zero or less")
    private Double bookPrize;
}
