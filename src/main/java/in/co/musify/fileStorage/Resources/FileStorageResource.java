package in.co.musify.fileStorage.Resources;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.co.musify.fileStorage.AtomValidationException;
import in.co.musify.fileStorage.DAOException;
import in.co.musify.fileStorage.Resources.ResourceHelpers.UploadRequest;
import in.co.musify.fileStorage.Impl.FileStorageImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by aparna.n on 19/01/16.
 */

@Controller
@RequestMapping("/filestorage")
public class FileStorageResource {
    private static final Logger logger = Logger.getLogger(FileStorageResource.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    FileStorageImpl fileStorage;

    @RequestMapping(value="/storeFile", method = RequestMethod.POST, consumes = {"multipart/form-data"},produces="application/json")
    public @ResponseBody
    String storeFile(@RequestPart("metadata") UploadRequest uploadRequest,@RequestPart("file") List<MultipartFile> file)
            throws IOException, AtomValidationException{
        fileStorage.store(uploadRequest, file);
        return "will sent a link in near future";
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET, produces = "application/octet-stream")
    public @ResponseBody
    ResponseEntity downloadFile(@RequestParam(value = "namespace", required = true) String namespace,
                                                     @RequestParam(value = "id", required = true) String id) throws IOException {

        InputStream inputStream;
        try {
             inputStream = fileStorage.download(namespace, id);
            return ResponseEntity
                    .ok()
                    .header("Cache-Control", "no-cache, no-store, must-revalidate")
                    .header("Pragma", "no-cache")
                    .header("Expires", "0")
                    .contentLength(inputStream.available())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(inputStream));
        } catch (DAOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
