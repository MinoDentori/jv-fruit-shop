import java.util.List;

public interface FileService <T>{

    List<T> read(String filePath);
    void write(String filePath, String toWrite);
}
