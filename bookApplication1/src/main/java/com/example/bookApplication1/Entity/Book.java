package com.example.bookApplication1.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "book_type", discriminatorType = DiscriminatorType.STRING)
@Table(name="books")
public sealed class Book permits FictionBook, NonFictionBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="book_name")
    private String title;

    @Column(name="book_author")
    private String author;

    @Column(name="book_genre")
    private String genre;
}
