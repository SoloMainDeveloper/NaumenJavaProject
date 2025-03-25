package ru.solomein_michael.NauJava.Repository;

import java.util.Optional;

public interface CrudRepository<T, ID> {
    void create(T entity);
    Optional<T> read(ID id);
    void update(T entity);
    void deleteByGameId(ID id);
}
