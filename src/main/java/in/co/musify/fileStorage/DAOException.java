package in.co.musify.fileStorage;

/**
 * Created by aparna.n on 23/01/16.
 */
public class DAOException extends RuntimeException {

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public static DAOException wrap(Throwable e) {
        return (e instanceof DAOException) ?(DAOException) e : new DAOException(e);
    }
}
