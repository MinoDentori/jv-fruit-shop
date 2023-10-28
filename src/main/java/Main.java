import core.basesyntax.operation.*;
import model.DailyReport;
import model.FruitTransaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static FileService<FruitTransaction>  fileServiceCsv = new FileServiceCsvImpl();
    static OperationStrategy operationStrategy;
    public static void main(String[] args) {
        Map<Operation, OperationHandler> operationOperationHandlerMap = new HashMap<>();
        operationOperationHandlerMap.put(Operation.BALANCE, new BalanceOperationHandler());
        operationOperationHandlerMap.put(Operation.SUPPLY, new SupplyOperationHandler());
        operationOperationHandlerMap.put(Operation.PURCHASE, new PurchaseOperationHandler());
        operationOperationHandlerMap.put(Operation.RETURN, new ReturnOperationHandler());
        operationStrategy = new OperationStrategyImpl(operationOperationHandlerMap);
        List<FruitTransaction> listOfTransactions = fileServiceCsv.read("src/test.csv");
        Map<String, Integer> map = new HashMap<>();
        for (FruitTransaction transaction : listOfTransactions) {
            OperationHandler operationHandler = operationStrategy.get(transaction.operation());
            operationHandler.perform(transaction.fruit(), transaction.quantity(), map);
        }
        DailyReport report = new DailyReport(map);
        fileServiceCsv.write("result.csv", report.createDailyReportString());

    }
}
