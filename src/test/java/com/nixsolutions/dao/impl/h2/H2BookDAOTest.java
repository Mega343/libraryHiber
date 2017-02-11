package com.nixsolutions.dao.impl.h2;

import com.nixsolutions.DBUnitConfig;
import com.nixsolutions.bean.*;
import com.nixsolutions.dao.BookDAO;
import com.nixsolutions.util.HibernateUtil;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.FileInputStream;

import static org.dbunit.Assertion.assertEqualsIgnoreCols;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HibernateUtil.class)
public class H2BookDAOTest extends DBUnitConfig {

    private Book book;
    private BookDAO bookDAO;

    public H2BookDAOTest(String name) throws Exception {
        super(name);
    }



    protected SessionFactory mockSetUp() throws ClassNotFoundException{

        if (sessionFactory == null) {

            Configuration configuration = new Configuration().configure("test_hibernate.cfg.xml");
            configuration.configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    @Test
    public void testShouldAddBook() throws Exception {

        //given
        PowerMockito.mockStatic(HibernateUtil.class);
        Mockito.when(HibernateUtil.getSessionFactory()).thenReturn(mockSetUp());
        bookDAO = factory.getBookDAO();
        Author author = new Author(3, "Gilbert", "Shildt", null);
        PublishingHouse ph = new PublishingHouse(2, "Rosmen");
        Genre genre = new Genre(3, "Science fiction");
        Language language = new Language(4, "Polish");
        Shelf shelf = new Shelf(2, 2);
        book = new Book("Polska istoria", 2001, "Some desrpt", 12, 12, author, ph, genre,language, shelf);
        IDataSet expectedSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/book/book-add.xml"));
        ITable expectedTable = expectedSet.getTable("book");

        //when
        bookDAO.add(book);
        IDatabaseConnection connection = getConnection();
        IDataSet actualSet = connection.createDataSet();
        ITable actualTable = actualSet.getTable("book");

        // then
        String[] cols = {"book_id", "book_rate", "number_of_readings"};
        assertEqualsIgnoreCols(expectedTable, actualTable, cols);
    }

//    @Test
//    public void testShouldUpdateBook() throws Exception {
//        //given
//        book = new Book("Polska istoria", 3, 2, 3, 2001, 4, "Some desrpt", 12, 12, 2);
//        book.setBookID(4L);
//        IDataSet expectedSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/book/book-update.xml"));
//        ITable expectedTable = expectedSet.getTable("book");
//
//        //when
//        bookDAO.edit(book);
//        IDataSet actualSet = connection.createDataSet();
//        ITable actualTable = actualSet.getTable("book");
//
//        // then
//        String[] cols = {"book_id", "book_rate", "number_of_readings"};
//        assertEqualsIgnoreCols(expectedTable, actualTable, cols);
//    }
//
//    @Test
//    public void testShouldDeleteBook() throws Exception {
//        //given
//        Long bookID = 5L;
//        IDataSet expectedSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/book/book-delete.xml"));
//        ITable expectedTable = expectedSet.getTable("book");
//
//        //when
//        bookDAO.delete(bookID);
//        IDataSet actualSet = connection.createDataSet();
//        ITable actualTable = actualSet.getTable("book");
//
//        // then
//        String[] cols = {"book_id", "book_rate", "number_of_readings"};
//        assertEqualsIgnoreCols(expectedTable, actualTable, cols);
//    }
//
//    @Test(expected = SQLException.class)
//    public void testShouldThrowExceptionIfTryDeleteBookWhichUsedInOrder() throws FileNotFoundException, DataSetException {
//        //given
//        Long bookID = 2L;
//
//        //when
//        bookDAO.delete(bookID);
//    }
//
//    @Test
//    public void testShouldReturnBook() throws Exception {
//        //given
//        Long bookID = 3L;
//        Book expectedBook = new Book("Кобзарь.", 1, 1, 1, 2000, 1, "Some desrpt", 4, 4, 2);
//        expectedBook.setBookRate(0);
//        expectedBook.setNumberOfReadings(0);
//        expectedBook.setBookID(bookID);
//
//        //when
//        Book actualBook = bookDAO.getBook(bookID);
//
//        //then
//        assertEquals(expectedBook.toString(), actualBook.toString());
//    }
//
//    @Test
//    public void testShouldReturnBooksByAuthor() throws Exception {
//        //given
//        Integer authorID = 1;
//        List<Book> expectedBooksList = new ArrayList<>();
//        Book expectedBook = new Book("Кобзарь.", 1, 1, 1, 2000, 1, "Some desrpt", 4, 4, 2);
//        expectedBooksList.add(expectedBook);
//        expectedBook.setBookRate(0);
//        expectedBook.setNumberOfReadings(0);
//        expectedBook.setBookID(3L);
//
//        //when
//        List<Book> actualBooksList = bookDAO.searchByAuthor(authorID);
//
//        //then
//        assertEquals(expectedBooksList.toString(), actualBooksList.toString());
//    }
//
//    @Test
//    public void testSearchByBookName() throws Exception {
//        //given
//        String bookName = "Polska mova.";
//        List<Book> expectedBooksList = new ArrayList<>();
//        Book expectedBook = new Book("Polska mova.", 3, 2, 3, 2000, 4, "Some desrpt", 1, 1, 3);
//        expectedBooksList.add(expectedBook);
//        expectedBook.setBookRate(0);
//        expectedBook.setNumberOfReadings(0);
//        expectedBook.setBookID(4L);
//
//        //when
//        List<Book> actualBooksList = bookDAO.searchByBookName(bookName);
//
//        //then
//        assertEquals(expectedBooksList.toString(), actualBooksList.toString());
//    }
//
//
//    @Test
//    public void testShouldReturnBooksByGenre() throws Exception {
//        //given
//        Integer genreID = 1;
//        List<Book> expectedBooksList = new ArrayList<>();
//        Book expectedBook = new Book("Кобзарь.", 1, 1, 1, 2000, 1, "Some desrpt", 4, 4, 2);
//        expectedBooksList.add(expectedBook);
//        expectedBook.setBookRate(0);
//        expectedBook.setNumberOfReadings(0);
//        expectedBook.setBookID(3L);
//
//        //when
//        List<Book> actualBooksList = bookDAO.searchByGenre(genreID);
//
//        //then
//        assertEquals(expectedBooksList.toString(), actualBooksList.toString());
//    }
//
//
//    @Test
//    public void testGetAllBooks() throws Exception {
//        //given
//        List<Book> expectedBooksList = prepareBook();
//
//        //when
//        List<Book> actualBooksList = bookDAO.getAllBooks();
//
//        //then
//        assertEquals(expectedBooksList.toString(), actualBooksList.toString());
//    }
//
//    private List<Book> prepareBook() {
//        List<Book> expectedBooksList = new ArrayList<>();
//        Book book1 = new Book("Java 8. Full manual.", 3, 1, 4, 2012, 2, "Some desrpt", 25, 25, 1);
//        book1.setNumberOfReadings(0);
//        book1.setBookRate(0);
//        Book book2 = new Book("Java 8. Полное руководство.", 3, 2, 4, 2013, 1, "Some desrpt", 50, 50, 3);
//        book2.setNumberOfReadings(0);
//        book2.setBookRate(0);
//        Book book3 = new Book("Кобзарь.", 1, 1, 1, 2000, 1, "Some desrpt", 4, 4, 2);
//        book3.setNumberOfReadings(0);
//        book3.setBookRate(0);
//        Book book4 = new Book("Polska mova.", 3, 2, 3, 2000, 4, "Some desrpt", 1, 1, 3);
//        book4.setNumberOfReadings(0);
//        book4.setBookRate(0);
//        Book book5 = new Book("Сказки.", 2, 2, 2, 2000, 1, "Some desrpt", 4, 4, 1);
//        book5.setBookRate(0);
//        book5.setNumberOfReadings(0);
//        book1.setBookID(1L);
//        book2.setBookID(2L);
//        book3.setBookID(3L);
//        book4.setBookID(4L);
//        book5.setBookID(5L);
//        expectedBooksList.add(book1);
//        expectedBooksList.add(book2);
//        expectedBooksList.add(book3);
//        expectedBooksList.add(book4);
//        expectedBooksList.add(book5);
//        return expectedBooksList;
//    }
}