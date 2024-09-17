package repository.interfaces;

import domain.entities.Quote;

import java.util.List;
import java.util.Optional;

public interface QuoteInterface {

    public Optional<Quote> save(Quote quote);

    public Optional<Quote> findById(Quote quote);

    public List<Quote> all();

    public Quote update(Quote quote);

    public boolean delete(Quote quote);
}
