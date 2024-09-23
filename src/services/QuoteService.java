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

    public Optional<Quote> findById(int id) {
        Quote quote = new Quote();
        quote.setId(id);
        return quoteRepository.findById(quote);
    }


    public boolean deleteQuote(Quote quote) {
        return quoteRepository.delete(quote);
    }
}
