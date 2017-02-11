package com.nixsolutions.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookID;
    @NotNull
    @Column(name = "book_name")
    private String bookName;
    @NotNull
    @Column(name = "year_of_issue")
    private Integer yearOfIssue;
    @Column(name = "book_description")
    private String bookDescription;
    @NotNull
    @Column(name = "book_quantity")
    private Integer bookQuantity;
    @NotNull
    @Column(name = "book_in_warehouse")
    private Integer bookInWarehouse;
    @Column(name = "book_rate", columnDefinition = "int default 0")
    private Integer bookRate;
    @Column(name = "number_of_readings", columnDefinition = "int default 0")
    private Integer numberOfReadings;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private Author author;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "publishing_house_id", referencedColumnName = "publishing_house_id")
    private PublishingHouse ph;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", referencedColumnName = "genre_id")
    private Genre genre;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", referencedColumnName = "language_id")
    private Language language;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "shelf_id", referencedColumnName = "shelf_id")
    private Shelf shelf;

    public Book() {
    }

    public Book(String bookName, Integer yearOfIssue, String bookDescription, Integer bookQuantity,
                Integer bookInWarehouse, Author author, PublishingHouse ph,
                Genre genre, Language language, Shelf shelf) {
        this.bookName = bookName;
        this.yearOfIssue = yearOfIssue;
        this.bookDescription = bookDescription;
        this.bookQuantity = bookQuantity;
        this.bookInWarehouse = bookInWarehouse;
        this.author = author;
        this.ph = ph;
        this.genre = genre;
        this.language = language;
        this.shelf = shelf;
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(Integer yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public Integer getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(Integer bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public Integer getBookInWarehouse() {
        return bookInWarehouse;
    }

    public void setBookInWarehouse(Integer bookInWarehouse) {
        this.bookInWarehouse = bookInWarehouse;
    }

    public Integer getBookRate() {
        return bookRate;
    }

    public void setBookRate(Integer bookRate) {
        this.bookRate = bookRate;
    }

    public Integer getNumberOfReadings() {
        return numberOfReadings;
    }

    public void setNumberOfReadings(Integer numberOfReadings) {
        this.numberOfReadings = numberOfReadings;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public PublishingHouse getPh() {
        return ph;
    }

    public void setPh(PublishingHouse ph) {
        this.ph = ph;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookName='" + bookName + '\'' +
                ", yearOfIssue=" + yearOfIssue +
                ", bookDescription='" + bookDescription + '\'' +
                ", bookQuantity=" + bookQuantity +
                ", bookInWarehouse=" + bookInWarehouse +
                ", bookRate=" + bookRate +
                ", numberOfReadings=" + numberOfReadings +
                '}';
    }
}
