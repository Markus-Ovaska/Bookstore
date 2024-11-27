package bookstore.bookstore.repository;

import bookstore.bookstore.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
