package core.basesyntax.operation;

import java.util.Map;

public class PurchaseOperationHandler implements OperationHandler {
    @Override
    public void perform(String fruit, int quantity,  Map<String, Integer> fruitQuantity) {
        int currentQuantity = fruitQuantity.get(fruit);
        fruitQuantity.put(fruit, currentQuantity - quantity);
    }
}
