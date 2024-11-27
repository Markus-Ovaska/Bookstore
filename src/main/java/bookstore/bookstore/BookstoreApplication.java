package bookstore.bookstore;

import bookstore.bookstore.domain.Book;
import bookstore.bookstore.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    // CommandLineRunneri
    @Bean
    public CommandLineRunner loadData(BookRepository bookRepository) {
        return (args) -> {
            bookRepository.save(new Book("kirja 1", "Kirjailija 1", 1111, "1111", 11.11));
            bookRepository.save(new Book("kirja 2", "Kirjailija 2", 2222, "2222", 22.22));
            bookRepository.save(new Book("kirja 3", "Kirjailija 3", 3333, "3333", 33.33));
        };
    }
}