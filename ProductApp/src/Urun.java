public class Urun {
    private String name;
    private String id;
    private int stock ;
    private String expdate;
    Urun(){
        this.name="";
        this.id ="";
        this.stock=0;
        this.expdate ="";
    }
    Urun(String id, String name,String expdate, int stock) {
        this.id = id;
        this.name = name;
        this.expdate = expdate;
        this.stock = stock;
    }
    public String getExpdate() {
        return expdate;
    }
    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}