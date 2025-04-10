module metropolia.patricsc.otp.dblocalization {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens metropolia.patricsc.otp.dblocalization to javafx.fxml;
    exports metropolia.patricsc.otp.dblocalization;
}