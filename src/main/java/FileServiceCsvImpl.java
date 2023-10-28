import core.basesyntax.operation.Operation;
import model.FruitTransaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileServiceCsvImpl implements FileService<FruitTransaction> {
    private static final String DATA_SEPARATOR = ",";
    @Override
    public List<FruitTransaction> read(String filePath) {
        List<FruitTransaction> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String dataLine = reader.readLine();
            while (dataLine != null) {
                String[] dataArray = dataLine.split(DATA_SEPARATOR);
                Operation operation = Operation.fromString(dataArray[0]);
                String fruit = dataArray[1];
                int quantity = Integer.parseInt(dataArray[2]);
                list.add(new FruitTransaction(operation, fruit, quantity));
                dataLine = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void write(String filePath, String toWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(toWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
