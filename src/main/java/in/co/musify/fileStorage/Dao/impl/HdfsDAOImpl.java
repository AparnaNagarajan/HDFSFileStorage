package in.co.musify.fileStorage.Dao.impl;

import in.co.musify.fileStorage.Atom.Atom;
import in.co.musify.fileStorage.Atom.Attachment;
import in.co.musify.fileStorage.Utils.AttachmentUtils;
import in.co.musify.fileStorage.DAOException;
import in.co.musify.fileStorage.Dao.HdfsDAO;
import in.co.musify.fileStorage.Enums.PutMode;
import in.co.musify.fileStorage.Constants.ConstantValues;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aparna.n on 23/01/16.
 */
public class HdfsDAOImpl implements HdfsDAO {
    private static final Logger logger = LoggerFactory.getLogger((HdfsDAOImpl.class));
    private FileSystem fileSystem;

    public HdfsDAOImpl(){}

    public HdfsDAOImpl(Configuration configuration) {
        try {
            logger.info("Getting configuration for Hdfs " + configuration);
            fileSystem = FileSystem.get(configuration);
        } catch (IOException e) {
            logger.error("Exception in getting configuration in hdfs");
            throw DAOException.wrap(e);
        }
    }
    public boolean deleteDirectory(String hdfsDirectoryPath) throws Exception {
        return true;
    }

    public boolean exists(String hdfsPath) throws Exception {
        return true;
    }

    public void deleteAttachments(Collection<String> HdfsFilePaths) {

    }

    public void put(Atom atom, PutMode mode) {
        if (mode == PutMode.APPEND) {
            _put(atom);
        } else if (mode == PutMode.REPLACE) {
//            String destPath = ConstantValues.HDFS_BASE_FILE_PATH +
//                    Path.SEPARATOR + atom.getNamespace() +
//                    Path.SEPARATOR + atom.getId();
            //deleteFiles(destPath, true);
            _put(atom);
        }
    }

    public void fetch(String sourcePath,String destPath){}

    public Map<String,InputStream> getStreams(Collection<String> hdfsPaths) throws IOException{
        Map<String, InputStream> attachmentStreamMap = new HashMap();
        for (String hdfsPath: hdfsPaths) {
            attachmentStreamMap.put(hdfsPath, fileSystem.open(new Path(hdfsPath)));
        }
        return attachmentStreamMap;
    }

    private void _put(Atom atom) {
        String destPath;
        if (atom.hasAttachments()) {
            Collection<Attachment> attachments = atom.getAttachments();
            for (Attachment attachment : attachments) {
                copyFileToHDFS(attachment.getFilePath(), attachment.getHfdsPath(), true);
            }
        }
    }

    private void copyFileToHDFS(String sourcePath, String destinationPath, boolean deleteSrc) {
        try {
            fileSystem.copyFromLocalFile(deleteSrc, true, new Path(sourcePath), new Path(destinationPath));
            System.out.println("destination hdfs path :: " + new Path(destinationPath));
        } catch (IOException e) {
            logger.error("Exception in coping files to hdfs for sourcepath: " + sourcePath + " hdfspath: " + destinationPath);
            throw DAOException.wrap(e);
        }
    }
}
