module org.imie.appgamelanguage {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.imie.appgamelanguage to javafx.fxml;
    exports org.imie.appgamelanguage;
}