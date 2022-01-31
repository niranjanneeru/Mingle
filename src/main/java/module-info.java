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
    requires java.sql;
    requires org.postgresql.jdbc;

    opens com.txtkm.txtkm to javafx.fxml;

    exports com.txtkm.txtkm.database;
    opens com.txtkm.txtkm.database to javafx.fxml;
    exports com.txtkm.txtkm.utility;
    opens com.txtkm.txtkm.utility to javafx.fxml;
    exports com.txtkm.txtkm.controllers;
    opens com.txtkm.txtkm.controllers to javafx.fxml;
    exports com.txtkm.txtkm;
}