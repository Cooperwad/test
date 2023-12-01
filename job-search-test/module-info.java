module com.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;


    opens com.frontend to javafx.fxml;
    exports com.frontend;
}