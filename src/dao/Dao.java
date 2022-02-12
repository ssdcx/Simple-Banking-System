package dao;

public interface Dao<T> {

    T get(String param);

    void save(T t);

    void update(T t, String param, int value);

    void delete(T t);
}
