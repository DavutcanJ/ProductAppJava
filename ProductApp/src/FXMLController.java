import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;


public class FXMLController implements Initializable,IFile{
        @FXML
        private TextField tid;
        @FXML
        private TextField tname;
        @FXML
        private TextField texpdate;
        @FXML
        private TextField tstock;
        @FXML
        private TextField tsearch;
        @FXML
        private Label toplabel = new Label();
        @FXML
        private Label kontrolabel = new Label();
        @FXML
        private TableColumn<Urun, String> data_id;
        @FXML
        private TableColumn<Urun, String> data_name;
        @FXML
        private TableColumn<Urun, String> data_exp;
        @FXML
        private TableColumn<Urun, Integer> data_stock;
        @FXML
        private TableView<Urun> data_table;
        DemirbasFirma firma = new OzelSirket();
        Date date = new Date();

        private void loadTableData() throws FileNotFoundException {
                int i =0;
                File file = new File("src\\urunbilgisi.txt");
                try {
                        if (!file.exists()) {
                                throw new FileNotFoundException();
                        }
                        else {
                                Scanner sc = new Scanner(file);
                                while(sc.hasNext()) {
                                        firma.Urun_list.add(new Urun(sc.next(),sc.next(),sc.next(),sc.nextInt()));
                                        data_table.getItems().add(firma.Urun_list.get(i));
                                        i++;
                                }
                        }
                }
                catch(FileNotFoundException ex){
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setContentText("Dosyadan veri çekilemedi kayıtlı verileri görüntülemek için dosya yolunu kontrol edin !");
                        a.show();
                }

        }

        @FXML
        private void setCellTable(){
                data_id.setCellValueFactory(new PropertyValueFactory<Urun,String>("id"));
                data_name.setCellValueFactory(new PropertyValueFactory<Urun,String>("name"));
                data_stock.setCellValueFactory(new PropertyValueFactory<Urun,Integer>("stock"));
                data_exp.setCellValueFactory(new PropertyValueFactory<Urun,String>("expdate"));

                }

        public void fileupdate() throws FileNotFoundException {
                File filer = new File("src\\urunbilgisi.txt");
                if (filer.exists())
                        filer.delete();
                PrintWriter input = new PrintWriter(filer);
                for(Urun tempurun : firma.Urun_list){
                        String mystr = tempurun.getId()+" "+tempurun.getName()+" "+tempurun.getExpdate()+" "+ String.valueOf(tempurun.getStock());
                        input.write(mystr+"\n");
                }
                input.close();
        }
        @FXML
        private void onaddbutton() throws FileNotFoundException {
                boolean bool = false;
                if(isInt(tid.getText()) && isString(tname.getText()) && isDate(texpdate.getText()) && isInt(tstock.getText())) {
                        kontrolabel.setText(" ");
                        Urun tempurun = new Urun(tid.getText(), tname.getText(), texpdate.getText(), Integer.parseInt(tstock.getText()));
                        for(Urun urun: firma.Urun_list){
                                if(Objects.equals(urun.getId(), tid.getText())){
                                kontrolabel.setText("Bu bilgide ürün listede bulunmaktadır");
                                bool = true;
                                }
                        }
                        if(!bool){
                                firma.Urun_list.add(tempurun);
                                data_table.getItems().add(tempurun);
                                fileupdate();
                                data_table.getSelectionModel().clearSelection();
                                kontrolabel.setText("Ürün eklendi !");
                        }
                }
                else{
                        kontrolabel.setText("Hatalı veya boş veri  girildi tekrar deneyin");
                }
        }
        @FXML
        private void ondeletebutton(){
                Urun tempurun = data_table.getSelectionModel().getSelectedItem();
                if(tempurun != null) {
                        firma.Urun_list.remove(tempurun);
                        data_table.getItems().remove(tempurun);
                        data_table.getSelectionModel().clearSelection();
                        kontrolabel.setText("Ürün silindi !");
                }
                else {
                        kontrolabel.setText("Ürün seçiniz");
                }
                try {
                        fileupdate();
                } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                }
        }
        @FXML
        private void onChangeButtonClick(){
                Urun tempurun = data_table.getSelectionModel().getSelectedItem();
                if(tempurun != null){
                        tid.setText(tempurun.getId());
                        tname.setText(tempurun.getName());
                        texpdate.setText(tempurun.getExpdate());
                        tstock.setText(String.valueOf(tempurun.getStock()));
                        data_table.getItems().remove(tempurun);
                        firma.Urun_list.remove(tempurun);
                        data_table.getSelectionModel().clearSelection();
                        kontrolabel.setText("Ürünü düzenleyip ekleyiniz !");
                }else{
                        kontrolabel.setText("Önce tablodan ürün seçiniz !");
                }
        }
        @FXML
        private void onSearchButtonClick(){
                boolean bool = false ;
                if(isInt(tsearch.getText())){

                for(Urun tempurun : firma.Urun_list){
                        if(Objects.equals(tempurun.getId(), tsearch.getText())){
                                tid.setText(tempurun.getId());
                                tname.setText(tempurun.getName());
                                texpdate.setText(tempurun.getExpdate());
                                tstock.setText(String.valueOf(tempurun.getStock()));
                                bool = true;
                                kontrolabel.setText("Ürün bulundu !");
                                firma.Urun_list.remove(tempurun);
                                data_table.getItems().remove(tempurun);
                                try {
                                        fileupdate();
                                } catch (FileNotFoundException e) {
                                        continue;
                                }
                                break;
                        }
                }
                }else{
                        kontrolabel.setText("Hatalı veri tipi girdisi!");
                }
                if(!bool){
                    kontrolabel.setText("Hatalı id girdisi!");
                }

        }
        @Override
        public boolean isString(String s){

                try{
                        if(Integer.parseInt(s)>0){
                                return false;

                        }else{
                                return false;
                        }
                }catch(NumberFormatException ex){
                        return true;
                }
        }
        @Override
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
        public boolean isDate(String x){
                int a =0;
                String[] newx;
                newx = x.split("/");

                for (String s : newx) {

                        if (isInt(s)) {
                                a++;
                        }
                }
                return a == newx.length;
        }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                tid.setPromptText("Enter a number");
                tname.setPromptText("Enter a name");
                texpdate.setPromptText("Type in '/' format");
                tstock.setPromptText("Enter a number ");
                tsearch.setPromptText("Enter an id to search");
                kontrolabel.setText("Aralarında boşluk olmadan verileri girin !");
                firma.setDate(date.getDate() + "/" + date.getMonth() + "/" + (date.getYear()+1900));
                
                toplabel.setText(date.getDate() + " " + date.getMonth() + " " + (date.getYear()+1900)+" Tarihli "+ firma.Message()+" Listesi");
                setCellTable();
                try {
                        loadTableData();
                } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                }
        }
}