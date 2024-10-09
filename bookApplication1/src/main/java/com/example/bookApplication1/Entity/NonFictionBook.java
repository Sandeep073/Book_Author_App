package com.example.bookApplication1.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("NonFiction")
public final class NonFictionBook extends Book {

    @Column(name = "nonfiction_category")
    private String nonFictionCategory;
}
