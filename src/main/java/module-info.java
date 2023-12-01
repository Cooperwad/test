module com.example.csc214groupproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csc214groupproject to javafx.fxml;
    exports com.example.csc214groupproject;
}