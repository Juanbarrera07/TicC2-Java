module com.calidad.reto4 {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    opens com.calidad.reto4 to javafx.fxml;
    exports com.calidad.reto4;
}