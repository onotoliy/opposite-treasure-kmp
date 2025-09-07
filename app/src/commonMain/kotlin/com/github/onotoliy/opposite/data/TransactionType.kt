package com.github.onotoliy.opposite.data

enum class TransactionType(label: String) {
    NONE("Не выбрано"),
    COST("Расход"),
    CONTRIBUTION("Взнос"),
    WRITE_OFF("Списание с депозита"),
    PAID("Платеж"),
    EARNED("Заработано");
}