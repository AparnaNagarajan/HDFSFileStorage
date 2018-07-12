package in.co.musify.fileStorage.Dao;

import in.co.musify.fileStorage.Atom.Atom;
import in.co.musify.fileStorage.DAOException;
import in.co.musify.fileStorage.Enums.PutMode;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by aparna.n on 24/01/16.
 */
public interface AtomDAO {
    void put(Atom atom, PutMode mode) throws DAOException;

    Atom get(String id, String namespace) throws DAOException;

    Map<String,InputStream> getInputStreamForAttachments(String entityID, String namespace);
}
