package courses.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface EntityDao {

    /**
     * .
     * Method to add object
     */
    <T> void insert(T object);

    /**
     * Method to delete object
     */
    <T> void delete(T object) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException;

    /**
     * Method to delete object by ID
     */
    void deleteById(Integer id) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException;

    /**
     * Method to update information about the object
     */
    <T> void update(T object);

    /**
     * Method to get information about the object.
     */
    List select();

    /**
     * Method to get Entity
     */
    <T> T getEntity(Integer id);
}
