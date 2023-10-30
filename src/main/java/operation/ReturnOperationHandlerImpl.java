package operation;

import model.FruitTransaction;

import java.util.Map;

public class ReturnOperationHandlerImpl implements OperationHandler {
    private final Map<String, Integer> fruitQuantity;

    public ReturnOperationHandlerImpl(Map<String, Integer> fruitQuantity) {
        this.fruitQuantity = fruitQuantity;
    }

    @Override
    public void perform(FruitTransaction fruitTransaction) {
        String fruit = fruitTransaction.getFruit();
        int currentQuantity = fruitQuantity.get(fruit);
        fruitQuantity.replace(fruit, currentQuantity + fruitTransaction.getQuantity());
    }
}
