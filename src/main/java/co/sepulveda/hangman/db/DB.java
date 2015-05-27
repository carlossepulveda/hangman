package co.sepulveda.hangman.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Carlos Sepulveda
 */
public class DB {

    Map<Class, Map<String, Object>> storage = new ConcurrentHashMap();

    public synchronized void save(Class c, String id, Object object) {
        Map<String, Object> objects = storage.get(c);
        if (objects == null) {
            objects = new ConcurrentHashMap<>();
        }

        objects.put(id, object);
        storage.put(c, objects);
    }

    public synchronized void delete(Class c, String id) {
        Map<String, Object> objects = storage.get(c);
        if (objects == null) {
            return;
        }

        objects.remove(id);
        storage.put(c, objects);
    }

    public Object get(Class c, String id) {
        Map<String, Object> objects = storage.get(c);
        if (objects == null) {
            return null;
        }

        return objects.get(id);
    }

    public List<?> list(Class c) {
        Map<String, Object> objects = storage.get(c);
        if (objects == null) {
            return Collections.emptyList();
        }

        return new ArrayList<>(objects.values());
    }
}