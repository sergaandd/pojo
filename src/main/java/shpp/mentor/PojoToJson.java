package shpp.mentor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class PojoToJson {
    static final int MAX_LETTERS = 15;
    static final int MIN_LETTERS = 4;
    private PojoToJson() {
    }
    public static String getJson(Pojo p) {
        ObjectMapper oM = new ObjectMapper();
//        oM.registerModule(new JavaTimeModule());
//        oM.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            return oM.writeValueAsString(p);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getName() {//Generate of random string for test
        int rndInt;
        String alph = "abcdefjhigklmnostupqrvwxyz1234567890";
        String randomString = "";
        for (int i = 0; i < ((Math.random() * (MAX_LETTERS - MIN_LETTERS)) + MIN_LETTERS); i++) {
            rndInt = (int) Math.round(Math.random() * 100) % alph.length();
            randomString += alph.charAt(rndInt);
        }
        return randomString;
    }


}
