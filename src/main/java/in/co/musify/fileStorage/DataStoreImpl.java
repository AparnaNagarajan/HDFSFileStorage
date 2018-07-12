package in.co.musify.fileStorage;

import in.co.musify.fileStorage.Dao.AtomDAO;
import in.co.musify.fileStorage.Factory.AtomDAOFactory;

import java.util.Map;
import java.util.Properties;

/**
 * Created by aparna.n on 24/01/16.
 */
public class DataStoreImpl implements DataStore{
    private final Properties dataStoreConfig;
    private final AtomDAO atomDAO;

    public DataStoreImpl(Properties dataStoreConfig, Map<String, DataStore> registry) {
        this.dataStoreConfig = dataStoreConfig;
        atomDAO = AtomDAOFactory.newInstance(dataStoreConfig, registry);
    }

    @Override
    public AtomDAO getAtomDAO(){
        return atomDAO;
    }
}
