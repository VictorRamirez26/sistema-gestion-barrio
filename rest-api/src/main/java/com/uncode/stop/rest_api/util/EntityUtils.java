package com.uncode.stop.rest_api.util;

import java.lang.reflect.Field;

public class EntityUtils {

    /**
     * Trims all non-final String fields in the given object.
     * 
     * @param obj The object whose String fields are to be trimmed.
     * @throws IllegalAccessException If a field is inaccessible.
     */
    public static void trimStringFields(Object obj) {
        if (obj == null) {
            return;
        }

        // Get all fields of the object's class
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            // Check if the field is of type String and is not final
            if (field.getType() == String.class && !java.lang.reflect.Modifier.isFinal(field.getModifiers())) {
                field.setAccessible(true); // Allow access to private fields

                // Get the value of the field
                String value;
                try {
                    value = (String) field.get(obj);

                    // Trim the value if it's non-null
                    if (value != null) {
                        field.set(obj, value.trim());
                    }
                } catch (Exception e) {
                }

            }
        }
    }

    // public static void main(String[] args) {
    // // Create an object with String fields
    // var obj = new Usuario();
    // obj.setCuenta(" Hello ");
    // obj.setClave(" World ");

    // try {
    // // Trim the String fields
    // trimStringFields(obj);

    // // Print the trimmed fields
    // System.out.println(obj.getCuenta()); // Output: "Hello"
    // System.out.println(obj.getClave()); // Output: "World"
    // } catch (IllegalAccessException e) {
    // e.printStackTrace();
    // }
    // }
}
