package in.co.musify.fileStorage.Initializers;

import in.co.musify.fileStorage.DAOException;
import in.co.musify.fileStorage.DataStore;
import in.co.musify.fileStorage.Factory.DataStoreFactory;
import in.co.musify.fileStorage.Utils.JSONHelpers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by aparna.n on 27/01/16.
 */
public class DataStoreInitializer {
    private static DataStoreInitializer INSTANCE;
    private DataStore dataStore;

    private DataStoreInitializer() {
        Properties dataStoreProperties = getDataStoreProperties();
        dataStore = DataStoreFactory.create(dataStoreProperties);
    }

    public static DataStoreInitializer getInstance() {
        if(INSTANCE == null) {
            synchronized(DataStoreInitializer.class) {
                if(INSTANCE == null) {
                    INSTANCE = new DataStoreInitializer();
                }
            }
        }
        return INSTANCE;
    }

    public DataStore getDataStore() {
        return dataStore;
    }

    private Properties getDataStoreProperties(){
        //read the appropriate json file for config
        Properties properties = new Properties();
        Map<String, String> jsonConfig = new HashMap<String, String>();
        try {
            jsonConfig = (Map<String, String>) JSONHelpers.readJson("hdfsConf.json", "java.util.HashMap");
            for(String key: jsonConfig.keySet()) {
                properties.setProperty(key, jsonConfig.get(key));
            }
        } catch (ClassNotFoundException e) {
            throw new DAOException(e);
        } catch (IOException e) {
            throw new DAOException(e);
        }
        return properties;
    }
}
