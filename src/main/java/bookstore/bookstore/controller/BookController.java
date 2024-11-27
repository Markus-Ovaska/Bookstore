package bookstore.bookstore.controller;

import bookstore.bookstore.domain.Book;
import bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import bookstore.bookstore.domain.Category;
import bookstore.bookstore.repository.CategoryRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository CategoryRepository;

    @GetMapping("/booklist")

    // kirjojen haku
    public String getAllBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        System.out.println("Books: " + books);
        model.addAttribute("books", books);
        return "booklist";
    }

    @GetMapping("/addbook")
    public String showAddBookForm(Model model) {
        model.addAttribute("categories", CategoryRepository.findAll());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBook(@RequestParam String title,
            @RequestParam String author,
            @RequestParam int publicationYear,
            @RequestParam String isbn,
            @RequestParam double price,
            @RequestParam Long categoryId) {

                Category category = CategoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + categoryId));
    
        // Create and save the book with the selected category
        Book book = new Book(title, author, publicationYear, isbn, price, category);
        book.setCategory(category);
        bookRepository.save(book);
        return "redirect:/booklist"; // ohjaa takaisin booklist sivulle
    }

    @GetMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable Long id) {

        {
            bookRepository.deleteById(id);
        }
        return "redirect:/booklist";
    }

    @GetMapping("/editbook/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "editbook";
    }

    @PostMapping("/editbook/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookRepository.save(book); // tallenna editointi
        return "redirect:/booklist";
    }
}
