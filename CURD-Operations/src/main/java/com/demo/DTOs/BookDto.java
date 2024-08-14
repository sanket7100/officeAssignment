package com.demo.DTOs;

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
    @NotNull
    private String bookName;

    @NotBlank(message = "Auther Name should not be blank")
    @NotNull
    private String bookAuther;

    @Size(min = 0, max = 50, message = "Max limit for discription is 50")
    private String bookDiscription;

    @Min(value = 1, message = "Price should not be zero or less")
    private Double bookPrize;
}
