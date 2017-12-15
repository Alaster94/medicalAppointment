package validation;

import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.*;

public class TextFieldValidation {
    public static boolean isPasswordMatched(PasswordField tf1, PasswordField tf2){
        boolean b = false;
        if (tf1.getText().equals(tf2.getText())){
            b = true;
        }
        return b;
    }
    public static boolean isPasswordMatched(PasswordField tf1, PasswordField tf2, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        tf2.getStyleClass().remove("error");
        if (!isPasswordMatched(tf1,tf2)){
            b = false;
            msg = errorMessage;
            tf2.getStyleClass().add("error");
        }
        lb.setText(msg);
        return b;
    }
    public static boolean isValidEmail(TextField tf){
        boolean b = false;
        String pattern = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        //String pattern = "";
        if (tf.getText().matches(pattern)){
            b = true;
        }
        return b;
    }
    public static boolean isValidEmail(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        tf.getStyleClass().remove("error");
        if (!isValidEmail(tf)){
            b = false;
            msg = errorMessage;
            tf.getStyleClass().add("error");
        }
        lb.setText(msg);
        return b;
    }

    public static boolean isTextFieldNotEmpty(TextField tf){
        boolean b = false;
        if (tf.getText().length() != 0 || !tf.getText().isEmpty()){
            b = true;
        }
        return b;
    }
    public static boolean isTextFieldNotEmpty(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        tf.getStyleClass().remove("error");
        if (!isTextFieldNotEmpty(tf)){
            b = false;
            msg = errorMessage;
            tf.getStyleClass().add("error");
        }
        lb.setText(msg);
        return b;
    }
//    public static boolean isDatePickerNotEmpty(DatePicker tf){
//        boolean b = false;
//        if (tf.getText().length() != 0 || !tf.getText().isEmpty()){
//            b = true;
//        }
//        return b;
//    }
//    public static boolean isDatePickerNotEmpty(DatePicker tf, Label lb, String errorMessage){
//        boolean b = true;
//        String msg = null;
//        tf.getStyleClass().remove("error");
//        if (!isDatePickerNotEmpty(tf)){
//            b = false;
//            msg = errorMessage;
//            tf.getStyleClass().add("error");
//        }
//        lb.setText(msg);
//        return b;
//    }
    public static boolean isTextAreaNotEmpty(TextArea tf){
        boolean b = false;
        if (tf.getText().length() != 0 || !tf.getText().isEmpty()){
            b = true;
        }
        return b;
    }
    public static boolean isTextAreaNotEmpty(TextArea tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        tf.getStyleClass().remove("error");
        if (!isTextAreaNotEmpty(tf)){
            b = false;
            msg = errorMessage;
            tf.getStyleClass().add("error");
        }
        lb.setText(msg);
        return b;
    }

    public static boolean istexFieldTypeNumber(TextField tf){
        boolean b = false;
        if (tf.getText().matches("([0-9]+(\\.[0-9]+)?)+"))
            b = true;
        return b;
    }
    public static boolean istexFieldTypeNumber(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg = null;
        tf.getStyleClass().remove("error");
        if (!istexFieldTypeNumber(tf)){
            b = true;
            msg = errorMessage;
            tf.getStyleClass().add("error");
        }
        lb.setText(msg);
        return b;
    }
}
