module com.izhiman.izhimansimpledictionary {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.izhiman.izhimansimpledictionary to javafx.fxml;
    exports com.izhiman.izhimansimpledictionary;
}