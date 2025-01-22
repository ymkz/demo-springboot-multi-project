package dev.ymkz.demo.core.domain.repository;

import dev.ymkz.demo.core.domain.model.Book;
import dev.ymkz.demo.core.domain.model.BookCreateCommand;
import dev.ymkz.demo.core.domain.model.BookSearchQuery;
import dev.ymkz.demo.core.domain.model.BookUpdateCommand;
import dev.ymkz.demo.core.domain.value.Isbn;
import dev.ymkz.demo.core.domain.value.Pagination;
import java.util.Optional;

public interface BookRepository {

  Pagination<Book> findMany(BookSearchQuery query);

  Optional<Book> findByIsbn(Isbn isbn);

  void create(BookCreateCommand book);

  void update(BookUpdateCommand book);

  void delete(long id);

  void deleteByIsbn(Isbn isbn);

}
