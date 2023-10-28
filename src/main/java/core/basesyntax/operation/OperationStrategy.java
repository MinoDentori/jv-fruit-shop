package core.basesyntax.operation;

public interface OperationStrategy {
    OperationHandler get(Operation operation);
}
