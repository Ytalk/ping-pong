module com.pong.pingpong {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.jfoenix;

    opens com.pong.pingpong to javafx.fxml;

    exports com.pong.pingpong;
}