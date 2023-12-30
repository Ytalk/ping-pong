package com.pong.pingpong;

import javafx.scene.control.Alert;

public class NumberFormatException extends RuntimeException{
    
    String message;
    String error_name = "OBJECTIVE SCORE ERROR";

    public NumberFormatException(String message){
        this.message = message;
    }


    public void showMessage(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(error_name);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}