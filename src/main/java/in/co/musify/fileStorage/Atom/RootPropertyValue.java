package in.co.musify.fileStorage.Atom;

import in.co.musify.fileStorage.AtomValidationException;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * Created by aparna.n on 23/01/16.
 */
public class RootPropertyValue implements PropertyValue {

    private static final Charset charset = Charset.forName("UTF-8");
    private final byte[] value;
    private final byte[] qualifier;

    public RootPropertyValue(Object value) {
        this(value, null);
    }

    public RootPropertyValue(Object value, Object qualifier) {
        this.value = toByteArray(value);
        this.qualifier = toByteArray(qualifier);
    }

    @Override
    public String getValueAsString() {
        return getAsString(value);
    }

    @Override
    public Integer getValueAsInt() {
        return getAsInt(value);
    }

    @Override
    public Long getValueAsLong() {
        return getAsLong(value);
    }

    @Override
    public Boolean getValueAsBoolean() {
        return getAsBoolean(value);
    }

    @Override
    public Double getValueAsDouble() {
        return getAsDouble(value);
    }

    @Override
    public Date getValueAsDate() {
        return getAsDate(value);
    }

    @Override
    public Date getQualifierAsDate() {
        return getAsDate(qualifier);
    }

    @Override
    public String getQualifierAsString() {
        return getAsString(qualifier);
    }

    @Override
    public int getQualifierAsInt() {
        return getAsInt(qualifier);
    }

    @Override
    public long getQualifierAsLong() {
        return getAsLong(qualifier);
    }

    @Override
    public boolean getQualifierAsBoolean() {
        return getAsBoolean(qualifier);
    }

    @Override
    public double getQualifierAsDouble() {
        return getAsDouble(qualifier);
    }

    @Override
    public byte[] getValueBytes() {
        return value;
    }

    @Override
    public byte[] getQualifierBytes() {
        return qualifier;
    }

    private static byte[] toByteArray(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof byte[]) {
            return (byte[])value;
        }
        if ((value instanceof String)     ||
                (value instanceof Integer)||
                (value instanceof Long)   ||
                (value instanceof Double) ||
                (value instanceof Boolean)) {
            return String.valueOf(value).getBytes(charset);
        } else if (value instanceof Date) {
            return String.valueOf(((Date)value).getTime()).getBytes(charset);
        } else {
            throw new AtomValidationException("value type not supported : " + value.getClass());
        }
    }


    private String getAsString(byte[] value) {
        if (value == null) return null;
        return new String(value, charset);
    }

    private Integer getAsInt(byte[] value) {
        if (value == null) return null;
        String s = new String(value, charset);
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException e){
            throw new AtomValidationException("Cannot convert to int : " + s, e);
        }
    }

    private Long getAsLong(byte[] value) {
        if (value == null) return null;
        String s = new String(value, charset);
        try {
            return Long.valueOf(s);
        } catch (NumberFormatException e){
            throw new AtomValidationException("Cannot convert to long : " + s, e);
        }
    }

    private Double getAsDouble(byte[] value) {
        if (value == null) return null;
        String s = new String(value, charset);
        try {
            return Double.valueOf(s);
        } catch (NumberFormatException e){
            throw new AtomValidationException("Cannot convert to double : " + s, e);
        }
    }

    private Boolean getAsBoolean(byte[] value) {
        if (value == null) return null;
        String bool = new String(value, charset);
        if ("true".equalsIgnoreCase(bool) || "false".equalsIgnoreCase(bool)) {
            return Boolean.valueOf(bool);
        } else {
            throw new AtomValidationException("Cannot convert to boolean : " + bool);
        }
    }

    private Date getAsDate(byte[] value) {
        if (value == null) return null;
        String s = new String(value, charset);
        try {
            return new Date(Long.valueOf(s));
        } catch (NumberFormatException e){
            throw new AtomValidationException("Cannot convert to date : " + s, e);
        }
    }

    @Override
    public String toString() {
        return "PropertyValue{" +
                "value=" + (value != null ? new String(value, charset) : null) +
                ", qualifier=" + (qualifier != null ? new String(qualifier, charset) : null) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RootPropertyValue value1 = (RootPropertyValue) o;

        if (!Arrays.equals(qualifier, value1.qualifier)) return false;
        if (!Arrays.equals(value, value1.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value != null ? Arrays.hashCode(value) : 0;
        result = 31 * result + (qualifier != null ? Arrays.hashCode(qualifier) : 0);
        return result;
    }

    @Override
    public Map<String, Property> getPropertyValue() {
        throw new UnsupportedOperationException("getPropertyValue Operation is not supported");
    }
}
