package com.pong.pingpong;

import javafx.scene.control.Alert;

/**
 * Represents a custom exception class for handling number format errors.
 * Extends the RuntimeException class.
 */
public class MatchInterfaceException extends RuntimeException {

    String message;
    String title;

    /**
     * Constructor that initializes the message and title field with the given parameters.
     *
     * @param message the error message
     */
    public MatchInterfaceException(String message, String title) {
        this.title = title;
        this.message = message;
    }

    /**
     * Displays an error message to the user using a JavaFX Alert dialog.
     */
    public void showMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}