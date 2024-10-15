package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// BEGIN
public class Validator {

    public static void main(String[] args) {
        Address address3 = new Address("USA", null, null, null, "1");
        System.out.println(address3.toString());
        List<String> result3 = validate(address3);
        System.out.println(result3.toString());
        List<String> expected3 = List.of("city", "street", "houseNumber");
    }

    public static List<String> validate(Object obj){
        var validatefields = new ArrayList<String>();

        for (var field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            var isAnnotationSet = field.getAnnotation(NotNull.class);

            try {
                Object value  = field.get(obj);
                if (isAnnotationSet != null && value == null) {
                    validatefields.add(field.getName());
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return validatefields;
    }
}
// END
