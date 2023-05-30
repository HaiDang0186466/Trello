/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package WorkManagementSystem;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author Admin
 */
public class LoginController implements Initializable {
    @FXML
    private AnchorPane main_form;
  
    @FXML
    private AnchorPane admin_form;
   
    @FXML
    private TextField admin_username;
   
    @FXML
    private PasswordField admin_password;
   
    @FXML
    private Button admin_loginBtn;
  
    @FXML
    private Hyperlink admin_hyperlink;

    @FXML
    private AnchorPane employee_form;

    @FXML
    private TextField employee_id;

    @FXML
    private PasswordField employee_password;
    
    @FXML
    private Button employee_loginBtn;
    
    @FXML
    private Hyperlink employee_hyperlink;
    
    //Database tools
   private Connection connect;
   private ResultSet result;
   private PreparedStatement prepare;
   private double x = 0;
   private double y = 0;
   
   public void employeeLogin(){
   
       String employeeData = "SELECT * FORM employee WHERE employee_id = ? and password = ? ";
       connect  = database.connectDb();
       
       try{
           Alert alert;
           
           prepare = connect.prepareStatement(employeeData);
           if (employee_id.getText().isEmpty()
                   || employee_password.getText().isEmpty()){
               alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error Message");
               alert.setHeaderText(null);
               alert.setContentText("Please fill all blank fields");
               alert.showAndWait();
           }else{
           prepare.setString(1,employee_id.getText());
           prepare.setString(2, employee_password.getText());
           
           result = prepare.executeQuery();
           
           if(result.next()){
               
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Login !");
            alert.showAndWait();
            
            employee_loginBtn.getScene().getWindow().hide();
               
            Parent root = FXMLLoader.load(getClass().getResource("employeeDashBoard.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            root.setOnMousePressed((MouseEvent event)->{
                    x = event.getSceneX();
                    y = event.getSceneY();  
                });
                
                root.setOnMouseDragged((MouseEvent event) ->{
                    stage.setX(event.getScreenX() -x);
                    stage.setY(event.getScreenX() -y);
                });
                stage.initStyle(StageStyle.TRANSPARENT);
            
            stage.setScene(scene);
            stage.show();
           }else{
               alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error Message");
               alert.setHeaderText(null);
               alert.setContentText("Wrong Employee ID or Password");
               alert.showAndWait();
           
           }
           }
       
       }catch(Exception e){e.printStackTrace();}
   }
   
   public void adminLogin(){
   String adminData = "SELECT * FROM admin WHERE username = ? and password =? ";
   
   connect = database.connectDb();
   
   try{
       
       Alert alert;
       
       if(admin_username.getText().isEmpty()
           || admin_password.getText().isEmpty()){
           alert = new Alert(AlertType.ERROR);
           alert.setTitle("Error Message");
           alert.setHeaderText(null);
           alert.setContentText("Please fill all blank fields ");
           alert.showAndWait();
       }else{
            prepare = connect.prepareStatement(adminData);
            prepare.setString(1,admin_username.getText());
            prepare.setString(2,admin_password.getText());
            result = prepare.executeQuery();
            
            if(result.next()){
                //then proceed to dashboard form
                
                
                //hide login form
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Login!");
                alert.showAndWait();
                
                admin_loginBtn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("adminDashBoard.fxml"));
                
                
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                
                root.setOnMousePressed((MouseEvent event)->{
                    x = event.getSceneX();
                    y = event.getSceneY();  
                });
                
                root.setOnMouseDragged((MouseEvent event) ->{
                    stage.setX(event.getScreenX() -x);
                    stage.setY(event.getScreenX() -y);
                });
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
              
                
            }else{
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Username or Password");
                alert.showAndWait();
            
            }
       }
       
   }catch(Exception e){e.printStackTrace();}
   }
    
    public void switchForm(ActionEvent event){
        if(event.getSource()== admin_hyperlink){
            admin_form.setVisible(false);
            employee_form.setVisible(true); 
        }else if(event.getSource()== employee_hyperlink){
            admin_form.setVisible(true);
            employee_form.setVisible(false); 
        }
    }
    
    public void close(){
        System.exit(0);
    }

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
