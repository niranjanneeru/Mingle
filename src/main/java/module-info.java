module com.txtkm.txtkm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    requires javafx.graphics;

    opens com.txtkm.txtkm to javafx.fxml;
    exports com.txtkm.txtkm;
}