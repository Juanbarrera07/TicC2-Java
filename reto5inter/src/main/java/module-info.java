module com.example.reto5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.reto5 to javafx.fxml;
    exports com.example.reto5;
}