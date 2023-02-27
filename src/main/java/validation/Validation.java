package validation;



import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.time.LocalDate;


public class Validation {

    public static boolean date_Validator(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {


            return false;
        } else {
            return true;
        }
    }

    public static boolean only_number_Validator(String input) {
        if (input != null) {
            try {
                float record = Float.parseFloat(input);
                return true;

            } catch (Exception e) {

                return false;
            }
        }
        else {
            return false;
        }

    }
}
