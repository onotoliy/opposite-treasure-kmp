package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.data.Cashbox

class CashboxCacheRepository: ICashboxRepository {
    override suspend fun get(): Cashbox {
        return Cashbox("1000000", "14.06.2025")
    }
}