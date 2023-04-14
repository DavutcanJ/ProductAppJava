import java.io.FileNotFoundException;

public interface IFile {
    void fileupdate() throws FileNotFoundException;
    boolean isInt(String x);
    boolean isString(String x);
    boolean isDate(String x);
}
