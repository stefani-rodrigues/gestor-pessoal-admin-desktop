module com.senac.gestorpessoaladmimconfig {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires com.fasterxml.jackson.databind;


    opens com.senac.gestorpessoaladmimconfig to javafx.fxml;
    opens com.senac.gestorpessoaladmimconfig.model to org.hibernate.orm.core;
    exports com.senac.gestorpessoaladmimconfig;
    exports com.senac.gestorpessoaladmimconfig.controller;
    opens com.senac.gestorpessoaladmimconfig.controller to javafx.fxml;
    exports com.senac.gestorpessoaladmimconfig.model to com.fasterxml.jackson.databind;
}