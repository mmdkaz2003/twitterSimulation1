module com.project.twittersimulation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;





    exports com.project.twittersimulation.model;
    opens com.project.twittersimulation.model to javafx.fxml;


    exports com.project.twittersimulation;
    opens com.project.twittersimulation to javafx.fxml;
}