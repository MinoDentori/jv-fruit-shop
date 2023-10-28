package core.basesyntax.operation;

import java.util.Map;

public interface OperationHandler {
    void perform(String fruit, int quantity,  Map<String, Integer> fruitQuantity);
}
