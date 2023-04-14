import java.util.ArrayList;


public abstract class DemirbasFirma{
    private final String message = "Demirbaş";
    ArrayList<Urun> Urun_list = new ArrayList<>();

    public ArrayList<Urun> getUrun_list() {
        return Urun_list;
    }

    public void setUrun_list(ArrayList<Urun> urun_list) {
        Urun_list = urun_list;
    }

    private String name;
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    DemirbasFirma(String name, String date){
        this.name=name;
        this.date = date;
    }
    DemirbasFirma(){
        this.name=" ";
        this.date =" ";
    }

    public abstract String Message();
}
