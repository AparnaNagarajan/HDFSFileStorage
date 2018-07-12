package in.co.musify.fileStorage.Utils;

import in.co.musify.fileStorage.Constants.ConstantValues;
import org.apache.hadoop.fs.Path;

import java.io.File;

/**
 * Created by aparna.n on 24/01/16.
 */
public class AttachmentUtils {
    private AttachmentUtils(){}

    public static String destPathGenerator(String namespace,String atomID,String fileName){
        return ConstantValues.HDFS_BASE_FILE_PATH + Path.SEPARATOR + namespace + Path.SEPARATOR + atomID +
                Path.SEPARATOR + fileName;
            //TODO:: Include pathToAppend while downloading fetch the hdfsPath and pass it to pathToAppend
    }

    public static String tempPathGenerator(String namespace, String id, String fileName){
        return ConstantValues.LOCAL_TEMP_DIR + File.separator + namespace
                + File.separator + id + File.separator + fileName;
    }
}
