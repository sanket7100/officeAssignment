package com.demo.repositories;

import com.demo.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Query("select e from BookEntity e where e.bookName LIKE %:title%")
    List<BookEntity> getBookByTitle(@Param("title") String title);
}
