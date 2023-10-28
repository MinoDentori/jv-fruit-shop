package model;

import core.basesyntax.operation.Operation;

public record FruitTransaction(Operation operation, String fruit, int quantity) {}