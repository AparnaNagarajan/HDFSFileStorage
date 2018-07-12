package in.co.musify.fileStorage.Atom;

import com.google.common.collect.Sets;
import in.co.musify.fileStorage.Constants.DataStoreConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by aparna.n on 23/01/16.
 */
public class Atom implements NamespaceView {
    private final String id;
    private final Map<String, Property> properties;
    private final String namespace;
    private Collection<Attachment> attachments;

    private Atom(String id, Map<String, Property> properties, String namespace, Collection<Attachment> attachments) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("id is blank");
        }
        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("namespace is blank");
        }
        this.id = id;
        this.properties = Collections.unmodifiableMap(properties);
        this.namespace = namespace;
        this.attachments = Collections.unmodifiableCollection(attachments);
    }

    public static class Builder {
        private String id;
        private Map<String, Property> properties = new HashMap<String, Property>();
        private String namespace;
        Collection<Attachment> attachments = Sets.newHashSet();
        private long expiresAt = -1L;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withProperties(Map<String, Property> properties) {
            this.properties.putAll(properties);
            return this;
        }

        public Builder withProperty(Property property) {
            this.properties.put(property.getName(), property);
            return this;
        }

        public Builder withNamespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        public Builder withExpiresAt(long expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public Builder withAttachments(Collection<Attachment> attachments) {
            this.attachments.addAll(attachments);
            return this;
        }

        public Atom createAtom() {
            validateExpiresAt();
            if (isExpirySpecified()) {
                properties.put(DataStoreConstants.TTL_PROPERTY_NAME, getTTLProperty(expiresAt));
            }
            return new Atom(id, properties, namespace, attachments);
        }

        private void validateExpiresAt() {
            if (isExpirySpecified() && expiresAt <= System.currentTimeMillis()) {
                throw new IllegalArgumentException("expiresAt is in the past, set expiresAt: " + expiresAt);
            }
        }

        private boolean isExpirySpecified() {
            return expiresAt != -1L;
        }

        private Property getTTLProperty(long expiresAt) {
            return new Property.Builder().withName(DataStoreConstants.TTL_PROPERTY_NAME).addPropertyValue(expiresAt).build();
        }
    }

    public String getId() {
        return id;
    }

    public Collection<Attachment> getAttachments() {
        return attachments;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public Set<String> getPropertyNames() {
        return properties.keySet();
    }

    public boolean hasProperty(String propertyName) {
        return properties.containsKey(propertyName);
    }

    public boolean hasAttachments() {
        return CollectionUtils.isNotEmpty(attachments);
    }

    public Map<String, Property> getProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Atom atom = (Atom) o;

        if (!id.equals(atom.id)) return false;
        if (!namespace.equals(atom.namespace)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + namespace.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Atom{" +
                "id='" + id + '\'' +
                ", properties=" + properties +
                ", namespace='" + namespace + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}

