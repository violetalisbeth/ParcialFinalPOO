module com.example.parcialfinalpoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.parcialfinalpoo to javafx.fxml;
    exports com.example.parcialfinalpoo;
}