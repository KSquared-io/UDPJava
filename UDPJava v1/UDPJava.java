package frontend;

import backend.PacketSendDemo;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Kev
 */
public class UDPJava extends Application {
    
    public PacketSendDemo packetSender;
    
@Override
    public void start(Stage primaryStage) {
        //create primaryStage with title using grid layout
        primaryStage.setTitle("CST4905 UDP Packet - Prof. Oudjehane - Kevin Kovack");
        GridPane grid = new GridPane();
        
        //set up grid
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Send a UDP Packet");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 1, 1);

        //host label
        Label userName = new Label("Host:");
        grid.add(userName, 0, 1);

        //TextField for host target
        TextField hostTextField = new TextField();
        grid.add(hostTextField, 0, 2);

        //label for console output
        Label consoleScreen = new Label("Console:");
        grid.add(consoleScreen, 0, 3);

        //TextArea to display console information
        TextArea consoleArea = new TextArea();
        consoleArea.setEditable(false); //this area is not editable
        grid.add(consoleArea, 0, 4);
        
        //create Reset button to clear fields
        Button clr = new Button("Reset");
        HBox hbClr = new HBox(10);
        hbClr.setMinWidth(60);
        hbClr.setAlignment(Pos.BOTTOM_LEFT);
        hbClr.getChildren().add(clr);
        grid.add(hbClr, 1, 2);
        
        //create button to initiate UDP sending
        Button btn = new Button("Send UDP Packet");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 5);
        
        //information text for events
        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 7);
        
        //create packet receive label
        Label packetLabel = new Label("Scanning Port 2000:");
        grid.add(packetLabel, 0, 8);
        
        //create packet message:
        TextArea packetText = new TextArea();
        grid.add(packetText, 0, 9);
        
        //UDP button action
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                String hostTarget = hostTextField.getText();
                if(hostTarget.isEmpty()){
                    hostTarget = "www.google.com";
                    hostTextField.setText("www.google.com");
                }
                consoleArea.setText(packetSender.sendPacketTo(hostTarget));
                actiontarget.setText(hostTarget+" attempted.");
            }
        });
        
        //Reset button action
        clr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                hostTextField.setText("");
                consoleArea.setText("");
                actiontarget.setText("");
            }
        });
        
        //create and show scene
        Scene scene = new Scene(grid, 650, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args); //launches program
    }
    
}
