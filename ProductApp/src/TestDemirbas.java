import java.io.*;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
public class TestDemirbas extends  Application{

    private int sifre=123;

    public int getSifre() {
        return sifre;
    }

    public void setSifre(int sifre) {
        this.sifre = sifre;
    }

    public static void main(String[] args) {

        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        showLoginScreen();
    }

    public void inloggeduser(String user) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Successful login");
        alert.setHeaderText("Successful login");
        String s = user + " logged in!";
        alert.setContentText(s);
        alert.show();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void unloggeduser(String user){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Not Succesful");
        alert.setHeaderText("Not Succesful");
        String s = user + " did not logged in";
        alert.setContentText(s);
        alert.show();

    }
    public void showLoginScreen() throws FileNotFoundException {
        Stage stage = new Stage();
        stage.setTitle("Sisteme Giriş Yapın");
        stage.setResizable(false);
        VBox box = new VBox();
        box.setPadding(new Insets(10));

        box.setAlignment(Pos.CENTER);//Merkeze alma

        Label label = new Label("Firmanızın ismini ve şifrenizi girin");

        TextField textUser = new TextField();
        textUser.setPromptText("Name");
        PasswordField textPass = new PasswordField();
        textPass.setPromptText("Password");
        Button btnLogin = new Button();
        btnLogin.setText("Login");
        Scene scene = new Scene(box, 250, 200);
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!isInt(textUser.getText()) && !textUser.getText().isEmpty() && !textPass.getText().isEmpty()) {
                    if (Integer.parseInt(textPass.getText()) == sifre && textUser.getText().length() >= 3) {
                        inloggeduser(textUser.getText());
                        stage.close();

                        Parent fxml = null;
                        try {
                            fxml = FXMLLoader.load(getClass().getResource("FXMLdoc.fxml"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Scene sc = new Scene(fxml, 800, 500);
                        stage.setTitle(textUser.getText());
                        stage.setScene(sc);
                        stage.show();
                    } else {
                        unloggeduser(textUser.getText());
                    }
                }
                else{
                    label.setText("Hatali kullanici girisi ! ");
                }
            }
        });
        box.getChildren().add(label);
        box.getChildren().add(textUser);
        box.getChildren().add(textPass);
        box.getChildren().add(btnLogin);

        stage.setScene(scene);
        stage.show();
    }
    public boolean isInt(String x){
        try{
            if(Integer.parseInt(x)>0){
                return true;
            }
            else{
                return true;
            }
        }catch(NumberFormatException ex){
            return false;
        }
    }
}
