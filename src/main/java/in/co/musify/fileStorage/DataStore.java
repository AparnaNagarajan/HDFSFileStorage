package in.co.musify.fileStorage;

import in.co.musify.fileStorage.Dao.AtomDAO;

/**
 * Created by aparna.n on 24/01/16.
 */
public interface DataStore {
    /**
     * @return The {@link AtomDAO} for the dataStore
     */
    AtomDAO getAtomDAO();
}
