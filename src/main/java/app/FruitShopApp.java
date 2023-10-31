package app;

import static storages.TransactionStorage.transactionList;

import files.FileReader;
import files.FileWriter;
import java.util.HashMap;
import java.util.Map;
import model.FruitTransaction;
import model.Operation;
import operation.BalanceOperationHandlerImpl;
import operation.PurchaseOperationHandlerImpl;
import operation.ReturnOperationHandlerImpl;
import operation.SupplyOperationHandlerImpl;
import operation.OperationHandler;
import operation.OperationStrategy;
import operation.OperationStrategyImpl;
import reporter.Reporter;

public class FruitShopApp {
    private static final String TEST_DATA_FILE_NAME = "data.csv";
    private static final String TEST_RESULT_FILE_NAME = "result.csv";
    private final Map<Operation, OperationHandler> operationOperationHandlerMap = new HashMap<>();
    private final FileWriter fileWriter;
    private final FileReader fileReader;
    private final Reporter reporter;
    private final OperationStrategy operationStrategy =
            new OperationStrategyImpl(operationOperationHandlerMap);

    public FruitShopApp(FileWriter fileWriter, FileReader fileReader, Reporter reporter) {
        this.fileWriter = fileWriter;
        this.fileReader = fileReader;
        this.reporter = reporter;
    }

    public void createDailyReport() {
        fillOperationMap();
        fileReader.readFromFile(TEST_DATA_FILE_NAME);
        performTransactionList();
        fileWriter.writeIntoFile(TEST_RESULT_FILE_NAME, reporter.createReport());
    }

    private void fillOperationMap() {
        operationOperationHandlerMap.put(Operation.BALANCE, new BalanceOperationHandlerImpl());
        operationOperationHandlerMap.put(Operation.SUPPLY, new SupplyOperationHandlerImpl());
        operationOperationHandlerMap.put(Operation.PURCHASE, new PurchaseOperationHandlerImpl());
        operationOperationHandlerMap.put(Operation.RETURN, new ReturnOperationHandlerImpl());
    }

    private void performTransactionList() {
        for (FruitTransaction transaction : transactionList) {
            OperationHandler operationHandler = operationStrategy.get(transaction.getOperation());
            operationHandler.perform(transaction);
        }
    }
}
