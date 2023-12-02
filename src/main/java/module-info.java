module com.example.csc214groupproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.tika.core;
    requires java.xml;
    requires org.jsoup;


    opens com.example.csc214groupproject to javafx.fxml;
    exports com.example.csc214groupproject;
}