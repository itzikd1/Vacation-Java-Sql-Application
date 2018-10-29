package Model;

import Model.Excpetions.V4UException;

public interface Table {
    boolean insert(Database db, Object[] data) throws V4UException;

    boolean update(Database db, String id, Object[] data)  throws V4UException;

    boolean delete(Database db, String id);

    Object read(Database db, String id);

    void print(Database db);
}
