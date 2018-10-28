package Model;

public interface Table {
    boolean insert(Database db, Object[] data);

    boolean update(Database db, String id, Object[] data);

    boolean delete(Database db, String id);

    Object read(Database db, String id);

    void print(Database db);
}
