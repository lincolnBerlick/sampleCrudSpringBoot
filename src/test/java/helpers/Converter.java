package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
