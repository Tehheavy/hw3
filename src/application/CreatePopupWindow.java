package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreatePopupWindow {
	public CreatePopupWindow(String Message) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("NotificationWindow.fxml"));
        Parent root = (Parent) loader.load();
        NotificationWindowController Controller = loader.getController();
        Controller.SetText(Message);
        Stage stage = new Stage();
        stage.setTitle("Notification");
        stage.setScene(new Scene(root));
    	stage.show();
	}


}
