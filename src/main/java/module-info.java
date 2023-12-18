module com.pong.pingpong {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens com.pong.pingpong to javafx.fxml;
    exports com.pong.pingpong;
}