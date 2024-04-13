module com.pk.ems {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires javafx.web;

    opens com.pk.ems to javafx.fxml;
    exports com.pk.ems;
    opens com.pk.ems.auth to javafx.fxml;

    
}