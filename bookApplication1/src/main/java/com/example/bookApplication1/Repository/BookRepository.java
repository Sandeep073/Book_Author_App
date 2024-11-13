package com.example.bookApplication1.Repository;

import com.example.bookApplication1.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Book,Integer> {
    public Book findBookByTitle(String title);
}