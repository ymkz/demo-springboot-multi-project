package dev.ymkz.demo.api.usecase;

import dev.ymkz.demo.core.domain.model.Book;
import dev.ymkz.demo.core.domain.model.BookSearchQuery;
import dev.ymkz.demo.core.domain.repository.BookRepository;
import dev.ymkz.demo.core.domain.value.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookSearchUsecase {

  private final BookRepository repository;

  public Pagination<Book> execute(BookSearchQuery query) {
    return repository.findMany(query);
  }

}
