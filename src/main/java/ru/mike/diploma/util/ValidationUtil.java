package ru.mike.diploma.util;



import ru.mike.diploma.model.AbstractBaseEntity;
import ru.mike.diploma.util.exception.IllegalRequestException;
import ru.mike.diploma.util.exception.NotFoundException;


public class ValidationUtil {

    public static void checkNotFound(boolean found, String msg)  {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);

    }

    public static <T> T checkNotFound(T obj, String msg)  {
        checkNotFound(obj != null, msg);
        return obj;
    }

    public static <T> T checkNotFoundWithId(T obj, int id)  {
        checkNotFound(obj != null, "id=" + id);
        return obj;
    }
    public static void checkNew(AbstractBaseEntity entity){
        if(entity.getId()!=null){
            throw new IllegalRequestException(entity + " must be new (id=null)");
        }

    }
    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }
}
