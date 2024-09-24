package services.interfaces;

import domain.entities.Quote;

import java.util.Optional;

public interface QuoteServiceInterface {
    Optional<Quote> addQuote(Quote quote);

    Optional<Quote> findById(int id);

    boolean deleteQuote(Quote quote);
}
