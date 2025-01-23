package dev.ymkz.demo.core.gateway.datasource;

import dev.ymkz.demo.core.domain.model.Book;
import dev.ymkz.demo.core.domain.model.BookCreateCommand;
import dev.ymkz.demo.core.domain.model.BookSearchQuery;
import dev.ymkz.demo.core.domain.model.BookUpdateCommand;
import dev.ymkz.demo.core.domain.repository.BookRepository;
import dev.ymkz.demo.core.domain.value.Pagination;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookDatasource implements BookRepository {

  private final BookMapper mapper;

  @Override
  public Pagination<Book> findMany(BookSearchQuery query) {
    var total = mapper.count(query);
    var content = mapper.list(query).stream().map(BookEntity::toBook).toList();
    return new Pagination<>(content, total, query.offset(), query.limit());
  }

  @Override
  public Optional<Book> findById(long id) {
    return null;
  }

  @Override
  public void create(BookCreateCommand book) {
    return;
  }

  @Override
  public void update(BookUpdateCommand book) {
    return;
  }

  @Override
  public void delete(long id) {
    return;
  }

}
