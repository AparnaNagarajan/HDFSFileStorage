package in.co.musify.fileStorage.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by abilash.s on 16/01/16.
 */
public class JSONHelpers {
    public static Object readJson(String pathToJson, String className) throws ClassNotFoundException, IOException {
        Class aclass = Class.forName(className);
        ObjectMapper mapper = new ObjectMapper();
        if(JSONHelpers.class.getClassLoader().getResource(pathToJson) == null) {
            throw new RuntimeException("tomcat unable to find the resource file pathToJson");
        }
        Object bean = mapper.readValue(JSONHelpers.class.getClassLoader().getResource(pathToJson), aclass);
        return bean;
    }
}