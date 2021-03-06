INSERT INTO role (user_role) VALUES ('Guest');
INSERT INTO role (user_role) VALUES ('Reader');
INSERT INTO role (user_role) VALUES ('Librarian');
INSERT INTO role (user_role) VALUES ('Admin');

INSERT INTO language (language) VALUES ('Russian');
INSERT INTO language (language) VALUES ('English');
INSERT INTO language (language) VALUES ('Ukrainian');
INSERT INTO language (language) VALUES ('Polish');
INSERT INTO language (language) VALUES ('Italian');

INSERT INTO genre (genre) VALUES ('Detective');
INSERT INTO genre (genre) VALUES ('Novel');
INSERT INTO genre (genre) VALUES ('Science fiction');
INSERT INTO genre (genre) VALUES ('Educational literature');
INSERT INTO genre (genre) VALUES ('Other');

INSERT INTO publishing_house (publishing_house_name) VALUES ('Roman');
INSERT INTO publishing_house (publishing_house_name) VALUES ('Rosmen');
INSERT INTO publishing_house (publishing_house_name) VALUES ('Ranok');

INSERT INTO author (first_name, last_name, patronymic) VALUES ('Taras', 'Shevchenko', 'Grigirievich');
INSERT INTO author (first_name, last_name, patronymic) VALUES ('Alexsandr', 'Pyshkin', 'Sergeevich');
INSERT INTO author (first_name, last_name) VALUES ('Gilbert', 'Shildt');
INSERT INTO author (first_name, last_name) VALUES ('Polka', 'Polska');


INSERT INTO shelf (shelf_number) VALUES (1);
INSERT INTO shelf (shelf_number) VALUES (2);
INSERT INTO shelf (shelf_number) VALUES (3);
INSERT INTO shelf (shelf_number) VALUES (4);
INSERT INTO shelf (shelf_number) VALUES (18);

INSERT INTO order_type (order_type) VALUES ('Given to read');
INSERT INTO order_type (order_type) VALUES ('Send by post');
INSERT INTO order_type (order_type) VALUES ('Given to read in the reading room');

INSERT INTO document_type (document_type) VALUES ('Passport');
INSERT INTO document_type (document_type) VALUES ('Driving licence');

INSERT INTO address (street_name, city_name, house_number, flat_number) VALUES ('23 Augusta', 'Kharkiv', '25B', '34');
INSERT INTO address (street_name, city_name, house_number, flat_number) VALUES ('Nayki', 'Kharkiv', '253', '11');
INSERT INTO address (street_name, city_name, house_number, flat_number) VALUES ('Gagarina', 'Kharkiv', '13', '14');
INSERT INTO address (street_name, city_name, house_number, flat_number) VALUES ('Khrseshatik', 'Kyiv', '1', '1');
INSERT INTO address (street_name, city_name, house_number, flat_number) VALUES ('Konovaltsia', 'Kyiv', '21', '5');
INSERT INTO address (street_name, city_name, house_number, flat_number) VALUES ('Kuibar', 'Kyiv', '33', '12');
INSERT INTO address (street_name, city_name, house_number, flat_number) VALUES ('Shevschenko', 'Kyiv', '22', '22');


INSERT INTO document (document_type_id, series, number, issued_by, date_of_issue) VALUES (1, 'BA', 123456, 'GMVS', '2006-12-30');
INSERT INTO document (document_type_id, series, number, issued_by, date_of_issue) VALUES (2, 'AAA', 123456, 'GAI', '2003-04-12');
INSERT INTO document (document_type_id, series, number, issued_by, date_of_issue) VALUES (1, 'MN', 123233, 'GAI KYIV', '2001-01-23');
INSERT INTO document (document_type_id, series, number, issued_by, date_of_issue) VALUES (2, 'KL', 123833, 'GAI KHARKIV', '1992-03-29');
INSERT INTO document (document_type_id, series, number, issued_by, date_of_issue) VALUES (2, 'BC', 333333, 'GAI KHARKIV', '2009-11-14');
INSERT INTO document (document_type_id, series, number, issued_by, date_of_issue) VALUES (1, 'RR', 984567, 'GUMVS', '1988-07-07');
INSERT INTO document (document_type_id, series, number, issued_by, date_of_issue) VALUES (1, 'RK', 984568, 'GUMVS', '2011-09-19');

INSERT INTO user (first_name, last_name, email, password, phone_number, karma, document_id, role_id, address_id) VALUES ('Ivan', 'Ivanov', 'ivanov@mail.ru', 'qwerty', '+380501234567', 20, 1, 4, 1);
INSERT INTO user (first_name, last_name, email, password, phone_number, karma, document_id, role_id, address_id) VALUES ('Tamara', 'Ivanova', 'ivanova@mail.ru', 'qwerty123', '+380501234568', 15, 2, 3, 2);
INSERT INTO user (first_name, last_name, email, password, phone_number, karma, document_id, role_id, address_id) VALUES ('Petr', 'Petrov', 'petrov@mail.ru', 'qwerty1234', '+380501234578', 15, 3, 3, 3);
INSERT INTO user (first_name, last_name, email, password, phone_number, karma, document_id, role_id, address_id) VALUES ('Sidor', 'Sidorov', 'sidorov@mail.ru', 'qwerty12345', '+380541234578', 10, 4, 2, 4);
INSERT INTO user (first_name, last_name, email, password, phone_number, karma, document_id, role_id, address_id) VALUES ('Kirill', 'Kutuzov', 'kutuzov@gmail.com', '12344566', '+380641234578', 10, 5, 2, 5);
INSERT INTO user (first_name, last_name, email, password, phone_number, karma, document_id, role_id, address_id) VALUES ('Olga', 'Orlova', 'orlova@gmail.com', '1234456633', '+380931234578', 10, 6, 2, 6);
INSERT INTO user (first_name, last_name, email, password, phone_number, karma, document_id, role_id, address_id) VALUES ('Oleg', 'Krutoj', 'krutoj@yahoo.com', '123444633', '+380931234568', 0, 7, 1, 7);

INSERT INTO book (book_name, author_id, publishing_house_id, genre_id, year_of_issue, language_id, book_description, book_quantity, book_in_warehouse, shelf_id) VALUES ('Java 8. Full manual.', 3, 1, 4, 2012, 2, 'Some desrpt', 25, 25, 1);
INSERT INTO book (book_name, author_id, publishing_house_id, genre_id, year_of_issue, language_id, book_description, book_quantity, book_in_warehouse, shelf_id) VALUES ('Java 8.', 3, 2, 4, 2013, 1, 'Some desrpt', 50, 50, 3);
INSERT INTO book (book_name, author_id, publishing_house_id, genre_id, year_of_issue, language_id, book_description, book_quantity, book_in_warehouse, shelf_id) VALUES ('Kobzar', 1, 3, 1, 2000, 3, 'Some desrpt', 4, 4, 2);
INSERT INTO book (book_name, author_id, publishing_house_id, genre_id, year_of_issue, language_id, book_description, book_quantity, book_in_warehouse, shelf_id) VALUES ('Polska mova', 3, 3, 3, 2000, 4, 'Some desrpt', 1, 1, 3);
INSERT INTO book (book_name, author_id, publishing_house_id, genre_id, year_of_issue, language_id, book_description, book_quantity, book_in_warehouse, shelf_id) VALUES ('Skazki', 2, 2, 2, 2000, 1, 'Some desrpt', 4, 4, 5);



INSERT INTO booking (user_id, librarian_id, book_id,  expected_return_date,  order_type_id) VALUES (4, 2, 2,  '2016-12-31',  1);
INSERT INTO booking (user_id, librarian_id, book_id,  expected_return_date,  order_type_id) VALUES (5, 3, 1,  '2017-01-31',  3);
INSERT INTO booking (user_id, librarian_id, book_id,  expected_return_date,  order_type_id) VALUES (6, 1, 3,  '2017-02-11',  2);
INSERT INTO booking (user_id, librarian_id, book_id,  expected_return_date,  order_type_id) VALUES (7, 5, 3,  '2016-02-11',  3);


