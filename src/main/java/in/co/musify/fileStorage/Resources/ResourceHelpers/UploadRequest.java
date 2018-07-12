package in.co.musify.fileStorage.Resources.ResourceHelpers;

import com.fasterxml.jackson.annotation.JsonProperty;
import in.co.musify.fileStorage.Constants.ConstantValues;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

/**
 * Created by aparna.n on 19/01/16.
 */
public class UploadRequest {
    private String namespace;
    private String storeMode;
    private String storePath;
    private String id;
    private Map<String, List<String>> properties;

    public UploadRequest(){
    }

    @NotNull
    @NotEmpty
    public String getNamespace(){
        return namespace;
    }

    public void setNamespace(String namespace){
        this.namespace = namespace;
    }

    @NotNull
    @NotEmpty
    @Pattern(regexp = "(A|R)")
    public String getStoreMode(){
        return storeMode;
    }

    public void setStoreMode(String storeMode){
        this.storeMode = storeMode;
    }

    public String getStorePath(){
        return storePath;
    }

    public void setStorePath(String storePath){
        this.storePath = storePath;
    }

    @NotNull
    @NotEmpty
    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    @NotNull
    @NotEmpty
    public Map<String, List<String>> getProperties(){
        return properties;
    }

    public void setProperties(Map<String, List<String>> properties){
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "UploadRequest{" +
                ", namespace='" + namespace + '\'' +
                ", storeMode='" + storeMode + '\'' +
                ", storePath='" + storePath + '\'' +
                ", id='" + id + '\'' +
                ", properties=" + properties +
                '}';
    }
}
