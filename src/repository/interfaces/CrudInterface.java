package repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface CrudInterface<T> {
    List<T> all();

    T save(T entity);

    Optional<T> findById(T t);


    T update(T entity);

    boolean delete(T entity);
}
