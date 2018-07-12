package in.co.musify.fileStorage.Dao;

import in.co.musify.fileStorage.Atom.Atom;
import in.co.musify.fileStorage.Enums.PutMode;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

/**
 * Created by aparna.n on 23/01/16.
 * HdfsDAO provides Api's to use Hdfs system
 * HDFS implementation . Entities of same namespace are stored
 * in a same directory named after their namespace and attachments of the entity are stored in the entityId directory created
 * in the namespace directory name. This implementation internally uses a {@link org.apache.hadoop.fs.FileSystem}.
 */
public interface HdfsDAO {

    /**
     * Api to delete an entire directory in Hdfs
     * @param hdfsDirectoryPath the directory Path to delete
     * @return true if successful else false
     * @throws Exception
     */
    public boolean deleteDirectory(String hdfsDirectoryPath) throws Exception;

    /**
     *
     * @param hdfsPath the path in Hdfs
     * @return true if the hdfsPath exists in hdfs else false
     * @throws Exception
     */
    public boolean exists(String hdfsPath) throws Exception;

    /**
     * deletes a list of files provided by hdfsFilePaths
     * @param HdfsFilePaths lists of path in Hdfs
     */
    public void deleteAttachments(Collection<String> HdfsFilePaths);

    /**
     * puts the attachment present in the atom to hdfs
     * @param atom Atom to put
     * @param mode The mode to put the atom
     */
    public void put(Atom atom, PutMode mode);

    /**
     * Copies the file present in hdfs to the local file system given the sourcepath.
     * @param sourcePath path in hdfs fs
     * @param destPath path in local fs
     */
    public void fetch(String sourcePath,String destPath);

    /**
     * @param hdfsPaths the path from where file is being fetched
     * @return collection of inputStreams
     */
    public Map<String,InputStream> getStreams(Collection<String> hdfsPaths) throws IOException;

}

