module com.example.parcialfinalpoo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.parcialfinalpoo to javafx.fxml;
    exports com.example.parcialfinalpoo;
}