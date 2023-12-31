package com.pong.pingpong;

import javafx.scene.control.Alert;

/**
 * Represents a custom exception class for handling number format errors.
 * Extends the RuntimeException class.
 */
public class NumberFormatException extends RuntimeException {

    String message;
    String error_name = "OBJECTIVE SCORE ERROR";

    /**
     * Constructor that initializes the message field with the provided message.
     *
     * @param message the error message
     */
    public NumberFormatException(String message) {
        this.message = message;
    }

    /**
     * Displays an error message to the user using a JavaFX Alert dialog.
     */
    public void showMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(error_name);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}