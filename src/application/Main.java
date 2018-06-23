package application; 
 
import java.io.IOException; import java.net.URL; 
 
import javafx.application.Application; import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene; import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage; 
 
public class Main extends Application { 
 
  @Override   public void start(Stage primaryStage) throws IOException {
	  // constructing our scene    
//	  URL url = getClass().getResource("LoginWindow.fxml"); 
//	  AnchorPane pane = FXMLLoader.load( url ); 
//	  Scene scene = new Scene( pane );	
//	  // setting the stage  
//	  primaryStage.setScene( scene );  
//	  primaryStage.setTitle( "Hello World wapa-313313983;313207888;313313991;328737739" );   
//	  primaryStage.show();  
//	  
//	  
	  try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
			Parent root;
			root = (Parent) loader.load();
			LoginWindowController CusControl = loader.getController();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			if(getParameters().getRaw().isEmpty()){
				System.out.println("argument is null");
				CusControl.load("192.168.1.17");
			}
			else{
				CusControl.load(getParameters().getRaw().get(0));
			}

			stage.setTitle("Login");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  }
  public static void main(String[] args)
  { 
	  launch(args); 
	  } } 
  