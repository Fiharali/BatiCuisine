package services;

import domain.entities.Quote;
import repository.QuoteRepository;

import java.util.Optional;

public class QuoteService {

    private QuoteRepository quoteRepository;

    public QuoteService() {
        this.quoteRepository = new QuoteRepository();
    }


    public Optional<Quote> addQuote (Quote quote) {
        return quoteRepository.save(quote);

    }
}
