module com.example.mintic {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mintic to javafx.fxml;
    exports com.example.mintic;
}