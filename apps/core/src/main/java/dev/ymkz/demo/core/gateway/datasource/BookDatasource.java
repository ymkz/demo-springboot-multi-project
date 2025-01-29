package dev.ymkz.demo.core.gateway.datasource;

import dev.ymkz.demo.core.domain.model.Book;
import dev.ymkz.demo.core.domain.model.BookCreateCommand;
import dev.ymkz.demo.core.domain.model.BookSearchQuery;
import dev.ymkz.demo.core.domain.model.BookUpdateCommand;
import dev.ymkz.demo.core.domain.repository.BookRepository;
import dev.ymkz.demo.core.domain.value.Pagination;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookDatasource implements BookRepository {

  private final BookMapper mapper;

  @Override
  public Pagination<Book> search(BookSearchQuery query) {
    var total = mapper.count(query);
    var content = mapper.list(query).stream().map(BookEntity::toBook).toList();
    return new Pagination<>(content, total, query.offset(), query.limit());
  }

  @Override
  public List<Book> download(BookSearchQuery query) {
    var content = mapper.download(query).stream().map(BookEntity::toBook).toList();
    return content;
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
