package in.co.musify.fileStorage.Atom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by aparna.n on 23/01/16.
 */
public class Property {
    private final String name;
    private final List<PropertyValue> values;

    private Property(String name, List<PropertyValue> values){
        this.name = name;
        this.values = Collections.unmodifiableList(values);
    }

    public String getName() {
        return name;
    }

    public List<PropertyValue> getValues() {
        return values;
    }

    public static class Builder {
        private String name;
        private List<PropertyValue> values = new ArrayList<PropertyValue>();

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder addPropertyValue(Object value) {
            this.values.add(new RootPropertyValue(value, null));
            return this;
        }

        public Builder addPropertyValue(Object value, Object qualifier) {
            this.values.add(new RootPropertyValue(value, qualifier));
            return this;
        }

        public Builder addPropertyValue(byte[] value, byte[] qualifier) {
            this.values.add(new RootPropertyValue(value, qualifier));
            return this;
        }

        public Builder withPropertyValue(PropertyValue propertyValue) {
            this.values.add(propertyValue);
            return this;
        }

        public Builder addPropertyValues(Collection<? extends PropertyValue> propertyValue) {
            this.values.addAll(propertyValue);
            return this;
        }

        public Property build() {
            return new Property(name, values);
        }
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", values=" + values +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property property = (Property) o;

        if (name != null ? !name.equals(property.name) : property.name != null) return false;
        if (values != null ? !values.equals(property.values) : property.values != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (values != null ? values.hashCode() : 0);
        return result;
    }
}
