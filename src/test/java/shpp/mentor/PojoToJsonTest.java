package shpp.mentor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PojoToJsonTest {
    Pojo testPojo = new Pojo().setName("Test").setCount(7);

    @Test
    void getJson() {
        String actualJson = PojoToJson.getJson(testPojo);
        String exspectedJson = "{\"count\":7,\"name\":\"Test\",\"created_at\":null}";
        Assertions.assertEquals(actualJson,exspectedJson);
    }

    @Test
    void getName() {
        int actualName = testPojo.getName().length()>0?1:0;
        int exspectedSize = 1;
        Assertions.assertEquals(actualName,exspectedSize);
    }
}