module org.example.ormfinalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires java.naming;

    // 👇 Add this so FXML can access your controllers
    opens org.example.ormfinalproject.controller to javafx.fxml;

    // If you need to export models/DTOs as well for reflection
    opens org.example.ormfinalproject.model to javafx.base;

    exports org.example.ormfinalproject; // keep exporting main package if needed
    requires jakarta.persistence;
    requires java.desktop;
    requires java.mail; // Fix "java: cannot access javax.naming.Referenceable" error
    exports org.example.ormfinalproject.controller; // if you want public API access
    opens org.example.ormfinalproject.Entity to org.hibernate.orm.core; // <-- important for Hibernate

}
