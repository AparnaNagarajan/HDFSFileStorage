package in.co.musify.fileStorage.Atom;

import in.co.musify.fileStorage.Atom.Property;

import java.util.Map;

/**
 * Created by aparna.n on 23/01/16.
 */
public interface NamespaceView {

        String getNamespace();

        String getId();

        Map<String, Property> getProperties();
}
