package in.co.musify.fileStorage.Impl;

import com.google.common.collect.Lists;
import in.co.musify.fileStorage.Atom.Atom;
import in.co.musify.fileStorage.DAOException;
import in.co.musify.fileStorage.Dao.AtomDAO;
import in.co.musify.fileStorage.Enums.PutMode;
import in.co.musify.fileStorage.Initializers.DataStoreInitializer;
import in.co.musify.fileStorage.Resources.ResourceHelpers.UploadRequest;
import in.co.musify.fileStorage.Constants.ConstantValues;
import in.co.musify.fileStorage.Resources.ResourceHelpers.UploadRequestAtomWrapper;
import in.co.musify.fileStorage.Utils.AttachmentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by aparna.n on 20/01/16.
 */
public class FileStorageImpl {

    @Autowired
    UploadRequestAtomWrapper uploadRequestAtomWrapper;

    public static final AtomDAO atomDAO = DataStoreInitializer.getInstance().getDataStore().getAtomDAO();

    public void store(UploadRequest uploadRequest, List<MultipartFile> fileList) throws IOException{
        for (MultipartFile file: fileList) {
            File tmpFile = new File(AttachmentUtils.tempPathGenerator(uploadRequest.getNamespace(), uploadRequest.getId(),
                    file.getOriginalFilename()));
            if (!tmpFile.exists()) {
                tmpFile.mkdirs();
            }
            file.transferTo(tmpFile);
        }
        Atom atom = uploadRequestAtomWrapper.getAtom(uploadRequest, fileList);
        atomDAO.put(atom, PutMode.REPLACE);
    }

    public InputStream download(String namespace, String id) throws DAOException{
        Iterator<InputStream> streamIterator = atomDAO.getInputStreamForAttachments(id, namespace).values().iterator();
        if(streamIterator.hasNext()){
            return streamIterator.next();
        } else
        {
            throw new DAOException("The file that you were looking for is not found in namespace: " + namespace +
            " for id:" + id);
        }
    }
}
