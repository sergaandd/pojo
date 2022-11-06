package shpp.mentor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileOpen {
    PropertyFileOpen() {}
    public static final String FILE_NAME = "myProp.properties";
    static FileInputStream input;
public static Properties openPropertyFile() {
    try {
        Properties property = new Properties();
        property.load(new FileInputStream(FILE_NAME));
        return property;
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
}
