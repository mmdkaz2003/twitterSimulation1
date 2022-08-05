module com.project.twittersimulation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.project.twittersimulation to javafx.fxml;
    exports com.project.twittersimulation;
}