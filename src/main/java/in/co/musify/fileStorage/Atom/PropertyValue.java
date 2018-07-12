package in.co.musify.fileStorage.Atom;

import in.co.musify.fileStorage.Atom.Property;

import java.util.Date;
import java.util.Map;

/**
 * Created by aparna.n on 23/01/16.
 */
public interface PropertyValue {

    String getValueAsString();

    String getQualifierAsString();

    byte[] getQualifierBytes();

    byte[] getValueBytes();

    double getQualifierAsDouble();

    boolean getQualifierAsBoolean();

    long getQualifierAsLong();

    int getQualifierAsInt();

    Date getQualifierAsDate();

    Date getValueAsDate();

    Double getValueAsDouble();

    Long getValueAsLong();

    Integer getValueAsInt();

    Map<String, Property> getPropertyValue();

    Boolean getValueAsBoolean();
}
