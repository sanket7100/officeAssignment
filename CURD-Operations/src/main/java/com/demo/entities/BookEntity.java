package com.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "bookshop")
public class BookEntity {

    @Id
    private Integer bookId;
    private String bookName;
    private String bookAuther;
    private String bookDiscription;
    private Double bookPrize;
}
