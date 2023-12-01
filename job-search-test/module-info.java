module com.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires org.apache.tika.core;
    requires java.xml;


    opens com.frontend to javafx.fxml;
    exports com.frontend;
}