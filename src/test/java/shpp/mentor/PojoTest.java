package shpp.mentor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PojoTest {
Pojo test= new Pojo().setCount(7).setName("Test").setCreated_at("1922");
    @Test
    void getCount() {
        int actual=test.getCount();
        int exspected = 7;
        Assertions.assertEquals(actual,exspected);
    }

    @Test
    void getName() {
        String actual=test.getName();
        String exspected = "Test";
        Assertions.assertEquals(actual,exspected);
    }

    @Test
    void getCreated_at() {
        String actual=test.getCreated_at();
        String exspected = "1922";
        Assertions.assertEquals(actual,exspected);
    }
}