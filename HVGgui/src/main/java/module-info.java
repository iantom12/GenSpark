module com.example.hvggui {
    requires javafx.controls;
    requires javafx.fxml;
    exports com.example.hvggui;
    opens com.example.hvggui to
            javafx.fxml;
}

