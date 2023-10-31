package core.basesyntax.operation;

import core.basesyntax.dao.FruitTransactionDao;
import core.basesyntax.model.FruitTransaction;

public class PurchaseOperationHandlerImpl implements OperationHandler {
    private final FruitTransactionDao fruitTransactionDao;

    public PurchaseOperationHandlerImpl(FruitTransactionDao fruitTransactionDao) {
        this.fruitTransactionDao = fruitTransactionDao;
    }

    @Override
    public void perform(FruitTransaction fruitTransaction) {
        String fruit = fruitTransaction.getFruit();
        int currentQuantity = fruitTransactionDao.get(fruit);
        fruitTransactionDao.replace(fruit, currentQuantity - fruitTransaction.getQuantity());
    }
}
