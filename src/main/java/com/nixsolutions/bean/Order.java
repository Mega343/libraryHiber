package com.nixsolutions.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "booking")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderID;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_date")
    private Timestamp orderDate;
    @Column(name = "expected_return_date")
    private Date expectedReturnDate;
    @Column(name = "actual_return_date")
    private Date actualReturnDate;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "librarian_id", referencedColumnName = "user_id")
    private User librarian;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_type_id", referencedColumnName = "order_type_id")
    private OrderType orderType;

    public Order() {
    }

    public Order(Date actualReturnDate, User user, User librarian, Book book, OrderType orderType, Date expectedReturnDate) {
        this.actualReturnDate = actualReturnDate;
        this.user = user;
        this.librarian = librarian;
        this.book = book;
        this.orderType = orderType;
        this.expectedReturnDate = expectedReturnDate;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public Date getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getLibrarian() {
        return librarian;
    }

    public void setLibrarian(User librarian) {
        this.librarian = librarian;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "Order{" +
                ", actualReturnDate=" + actualReturnDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", orderDate=" + orderDate +
                ", orderID=" + orderID +
                '}';
    }
}
