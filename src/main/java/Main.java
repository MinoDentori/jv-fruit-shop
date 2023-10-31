import core.basesyntax.app.FruitShopApp;
import core.basesyntax.dao.FruitQuantityDaoImpl;
import core.basesyntax.dao.FruitTransactionDaoImpl;
import core.basesyntax.model.Operation;
import core.basesyntax.operation.BalanceOperationHandlerImpl;
import core.basesyntax.operation.OperationHandler;
import core.basesyntax.operation.OperationStrategyImpl;
import core.basesyntax.operation.PurchaseOperationHandlerImpl;
import core.basesyntax.operation.ReturnOperationHandlerImpl;
import core.basesyntax.operation.SupplyOperationHandlerImpl;
import core.basesyntax.service.file.CsvFileReader;
import core.basesyntax.service.file.CsvFileWriter;
import core.basesyntax.service.parser.FruitTransactionDataParser;
import core.basesyntax.service.performer.FruitTransactionPerformer;
import core.basesyntax.service.reporter.FruitReporterImpl;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final String TEST_DATA_FILE_NAME = "data.csv";
    private static final String TEST_RESULT_FILE_NAME = "result.csv";

    public static void main(String[] args) {
        Map<Operation, OperationHandler> operationOperationHandlerMap = new HashMap<>();
        fillOperationMap(operationOperationHandlerMap);
        FruitShopApp fruitShopApp = new FruitShopApp(
                TEST_DATA_FILE_NAME, TEST_RESULT_FILE_NAME,
                new CsvFileWriter(), new CsvFileReader(),
                new FruitReporterImpl(new FruitQuantityDaoImpl()),
                new FruitTransactionPerformer(
                        new OperationStrategyImpl(operationOperationHandlerMap),
                        new FruitTransactionDaoImpl()),
                new FruitTransactionDataParser(new FruitTransactionDaoImpl()));
        fruitShopApp.createDailyReport();
    }

    private static void fillOperationMap(
            Map<Operation, OperationHandler> operationOperationHandlerMap) {
        operationOperationHandlerMap.put(Operation.BALANCE, new BalanceOperationHandlerImpl(
                new FruitQuantityDaoImpl()));
        operationOperationHandlerMap.put(Operation.SUPPLY, new SupplyOperationHandlerImpl(
                new FruitQuantityDaoImpl()));
        operationOperationHandlerMap.put(Operation.PURCHASE, new PurchaseOperationHandlerImpl(
                new FruitQuantityDaoImpl()));
        operationOperationHandlerMap.put(Operation.RETURN, new ReturnOperationHandlerImpl(
                new FruitQuantityDaoImpl()));
    }
}
