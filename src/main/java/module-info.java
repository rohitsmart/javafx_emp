module com.pk.ems {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.pk.ems to javafx.fxml;
    exports com.pk.ems;
}