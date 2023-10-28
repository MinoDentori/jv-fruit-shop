package core.basesyntax.operation;

import java.util.Map;

public class BalanceOperationHandler implements OperationHandler {
    @Override
    public void perform(String fruit, int quantity,  Map<String, Integer> fruitQuantity) {
        fruitQuantity.put(fruit, quantity);
    }
}
