package in.co.musify.fileStorage.Factory;

import in.co.musify.fileStorage.DataStore;
import in.co.musify.fileStorage.DataStoreImpl;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by aparna.n on 24/01/16.
 */
public class DataStoreFactory {
    private static final Logger logger = Logger.getLogger(DataStoreFactory.class);
    private static final ConcurrentMap<String, DataStore> registry = new ConcurrentHashMap<String, DataStore>();

    private DataStoreFactory(){}

    public static DataStore create(Properties dataStoreProperties) {
        logger.info("Creating datastore instance");
        if (MapUtils.isEmpty(registry)) {
            DataStore dataStore = new DataStoreImpl(dataStoreProperties,registry);
            registry.put("default",dataStore);
        } else {
            throw new IllegalStateException("cluster already created");
        }
        return registry.get("default");
    }
}
