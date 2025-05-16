module com.example.hospital {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;


    exports com.example.hospital.Vista;
    opens com.example.hospital.Vista to javafx.fxml;
    exports com.example.hospital.Controller;
    opens com.example.hospital.Controller to javafx.fxml;
    exports com.example.hospital.Vista.Admin;
    opens com.example.hospital.Vista.Admin to javafx.fxml;
    exports com.example.hospital.Controller.Admin;
    opens com.example.hospital.Controller.Admin to javafx.fxml;

    exports com.example.hospital.Model;
    opens com.example.hospital.Model to javafx.base, javafx.fxml;
    exports com.example.hospital.Model.Daos;
    opens com.example.hospital.Model.Daos to javafx.base, javafx.fxml;
    exports com.example.hospital.Model.Tablas;
    opens com.example.hospital.Model.Tablas to javafx.base, javafx.fxml;
}