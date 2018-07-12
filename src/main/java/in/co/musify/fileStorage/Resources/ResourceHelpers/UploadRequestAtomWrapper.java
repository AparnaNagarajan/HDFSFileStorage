package in.co.musify.fileStorage.Resources.ResourceHelpers;

import in.co.musify.fileStorage.Atom.Atom;
import in.co.musify.fileStorage.Atom.Attachment;
import in.co.musify.fileStorage.Atom.Property;
import in.co.musify.fileStorage.AtomValidationException;
import in.co.musify.fileStorage.Constants.ConstantValues;
import in.co.musify.fileStorage.Utils.AttachmentUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.Path;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aparna.n on 24/01/16.
 */
public class UploadRequestAtomWrapper {

    public Atom getAtom(UploadRequest uploadRequest,List<MultipartFile> filesToAttach) throws AtomValidationException{
        try {
            Atom atom = new Atom.Builder()
                    .withId(uploadRequest.getId())
                    .withNamespace(uploadRequest.getNamespace())
                    .withProperties(getProperties(uploadRequest.getProperties()))
                    .withAttachments(getAttachment(uploadRequest.getId(), uploadRequest.getNamespace(),filesToAttach))
                    .createAtom();
            return atom;
        } catch (AtomValidationException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new AtomValidationException(e);
        }
    }

    public Map<String,Property> getProperties(Map<String,List<String>> rawProperties) {
        Map<String,Property> propertyMap = new HashMap<String, Property>();
        if(MapUtils.isNotEmpty(rawProperties)) {
            for (String propertyName : rawProperties.keySet()) {
                List<String> values = rawProperties.get(propertyName);
                if (values != null && !values.isEmpty()) {
                    Property.Builder rootPropertyBuilder = new Property.Builder().withName(propertyName);
                    for (String property : values) {
                        rootPropertyBuilder.addPropertyValue(property);
                    }
                    propertyMap.put(propertyName, rootPropertyBuilder.build());
                }
            }
        }
        return propertyMap;
    }

    public List<Attachment> getAttachment(String id, String namespace,
                                          List<MultipartFile> filesToAttach) throws AtomValidationException{
        List<Attachment> attachmentList = new ArrayList<Attachment>();
        if(StringUtils.isEmpty(id) || StringUtils.isEmpty(namespace)) {
            throw new AtomValidationException("One of Id: " + id + " Namespace: " + namespace + "is null please enter correct values");
        } else {
            for(MultipartFile file: filesToAttach) {
                String filePath = AttachmentUtils.tempPathGenerator(namespace, id, file.getOriginalFilename());
                Attachment attachment = new Attachment.AttachmentBuilder().withFilePath(filePath)
                        .withHdfsPath(AttachmentUtils.destPathGenerator(namespace,id,file.getName()))
                        .createAttachment();
                attachmentList.add(attachment);
            }
        }
        return attachmentList;
    }
}
