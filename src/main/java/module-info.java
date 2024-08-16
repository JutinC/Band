module com.example.band {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.band to javafx.fxml;
    exports com.example.band;
}