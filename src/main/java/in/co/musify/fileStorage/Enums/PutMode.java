package in.co.musify.fileStorage.Enums;

/**
 * Created by aparna.n on 23/01/16.
 */
public enum PutMode {
    /**
     * Mode in which the specified entity is an entire replacement (override) of the existing entity.
     * If entity does not exist then it is created, if it exists it is replaced with the specified entity.
     */
    REPLACE,

    /**
     * Mode in which the specified entity provides additional properties to be added to an existing entity. So the
     * specified properties are added to the entity if it exists else an error is raised to indicate that the entity
     * does not exist.
     */
    APPEND
}
