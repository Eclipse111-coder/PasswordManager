module com.example.passwordman.passwordmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.passwordman.passwordmanager to javafx.fxml;
    exports com.example.passwordman.passwordmanager;
}