package in.co.musify.fileStorage;

/**
 * Created by aparna.n on 23/01/16.
 */
public class AtomValidationException extends DAOException {
        public AtomValidationException(String message) {
            super(message);
        }

        public AtomValidationException(String message, Throwable cause) {
            super(message, cause);
        }

        public AtomValidationException(Throwable cause) {
            super(cause);
        }
    }