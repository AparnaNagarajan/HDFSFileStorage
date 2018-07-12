package in.co.musify.fileStorage.Factory;

import in.co.musify.fileStorage.Dao.AtomDAO;
import in.co.musify.fileStorage.Dao.HdfsDAO;
import in.co.musify.fileStorage.Dao.impl.HdfsDAOImpl;
import in.co.musify.fileStorage.Dao.impl.MasterAtomImpl;
import in.co.musify.fileStorage.DataStore;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import java.util.Map;
import java.util.Properties;

/**
 * Created by aparna.n on 24/01/16.
 */
public class AtomDAOFactory {
    public static AtomDAO newInstance(Properties config, Map<String, DataStore> registry) {
        Configuration hdfsConfiguration = new Configuration();
        hdfsConfiguration.addResource(new Path(config.getProperty("hdfs.core.site")));
        hdfsConfiguration.addResource(new Path(config.getProperty("hdfs.hdfs.site")));
        hdfsConfiguration.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        hdfsConfiguration.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
        HdfsDAO hdfsDAO = new HdfsDAOImpl(hdfsConfiguration);
        return (new MasterAtomImpl(hdfsDAO));
    }
}
