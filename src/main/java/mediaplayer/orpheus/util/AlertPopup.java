package mediaplayer.orpheus.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Objects;
import java.util.Optional;

public class AlertPopup {
    private String message;
    private String title;

    /**
     * Simple AlertPopup using JavaFX Alert
     * @param title String of your title
     * @param message String of your message
     */
    public AlertPopup(String title, String message) {
        setMessage(message);
        setTitle(title);
    }

    /**
     * Internal method to avoid repeating code.
     * Sets the alert information.
     * @param name
     */
    private void alertInformation(Alert name){
        name.setTitle(getTitle());
        name.setHeaderText(null);
        name.setContentText(getMessage());
        /*
        name.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        // Apply the custom style class
        name.getDialogPane().getStyleClass().add("dialog-pane");
        */
        name.showAndWait();
    }

    /**
     * Show ERROR type alert
     */
    public void showError() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        alertInformation(error);
    }

    /**
     * show INFORMATION type alert
     */
    public void showInformation(){
        Alert information = new Alert(Alert.AlertType.INFORMATION);
        alertInformation(information);
    }

    /**
     * show WARNING type alert
     */
    public void showWarning(){
        Alert warning = new Alert(Alert.AlertType.WARNING);
        alertInformation(warning);
    }

    /**
     * show CONFIRMATION type ALERT
     * @param buttonConfirm Text inside button
     * @param buttonDecline Text inside button
     * @return boolean, true if OK is pressed, false otherwise.
     */
    public boolean showConfirmation(String buttonConfirm, String buttonDecline){
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle(getTitle());
        confirmation.setHeaderText(null);
        confirmation.setContentText(getMessage());

        ButtonType buttonTypeYes = new ButtonType(buttonConfirm);
        ButtonType buttonTypeNo = new ButtonType(buttonDecline);
        confirmation.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = confirmation.showAndWait();

        // Return true if user chose Yes, false otherwise including if they close the window.
        return result.isPresent() && result.get() == buttonTypeYes;
    }
//region Getter and setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String Message) {
        this.message = Message;
    }
//endregion
}
