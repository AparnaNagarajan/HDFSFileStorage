package in.co.musify.fileStorage.Dao.impl;

import com.google.common.collect.Lists;
import in.co.musify.fileStorage.Atom.Atom;
import in.co.musify.fileStorage.DAOException;
import in.co.musify.fileStorage.Dao.AtomDAO;
import in.co.musify.fileStorage.Dao.HdfsDAO;
import in.co.musify.fileStorage.Factory.DataStoreFactory;
import in.co.musify.fileStorage.Enums.PutMode;
import in.co.musify.fileStorage.Utils.AttachmentUtils;
import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by aparna.n on 24/01/16.
 */
public class MasterAtomImpl implements AtomDAO {
    private static final Logger logger = Logger.getLogger(DataStoreFactory.class);
    private final HdfsDAO hdfsDAO;

    public MasterAtomImpl(HdfsDAO hdfsDAO) {
        this.hdfsDAO = hdfsDAO;
    }

    @Override
    public void put(Atom e, PutMode mode) {
        try {
            hdfsDAO.put(e,mode);

        } catch (Exception ex) {
            logger.error("Exception in put, for entityId: " + e.getId() + " namespace: "
            + e.getNamespace() + " mode: " + mode, ex);
            throw DAOException.wrap(ex);
        }
    }

    @Override
    public Atom get(String id, String namespace) {
        return new Atom.Builder().createAtom();
    }

    @Override
    public Map<String, InputStream> getInputStreamForAttachments(String id, String namespace) {
        List<String> hdfsPaths = Lists.newArrayList();
        //TODO:: Extract the path from the stored entity in Hbase remove the below line
        hdfsPaths.add(AttachmentUtils.destPathGenerator(namespace,id,""));
        try {
            return hdfsDAO.getStreams(hdfsPaths);
        } catch(IOException e) {
            throw new DAOException(e);
        }
    }
}
