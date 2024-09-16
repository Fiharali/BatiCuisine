package services;

import domain.entities.Quote;
import repository.QuoteRepository;

import java.util.Optional;

public class DevisService {

    private QuoteRepository quoteRepository;

    public DevisService() {
        this.quoteRepository = new QuoteRepository();
    }


    public Optional<Quote> addDevis (Quote quote) {
        return quoteRepository.save(quote);

    }
}
