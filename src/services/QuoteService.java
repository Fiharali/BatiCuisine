package services;

import domain.entities.Quote;
import repository.QuoteRepository;
import services.interfaces.QuoteServiceInterface;

import java.util.Optional;

public class QuoteService implements QuoteServiceInterface {

    private QuoteRepository quoteRepository;

    public QuoteService() {
        this.quoteRepository = new QuoteRepository();
    }


    @Override
    public Optional<Quote> addQuote(Quote quote) {
        return quoteRepository.save(quote);

    }
}
