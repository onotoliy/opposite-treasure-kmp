package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.treasure.api.CashboxResourceApi
import com.github.onotoliy.opposite.treasure.model.Cashbox

class ICashboxRepository(private val api: CashboxResourceApi) {
    suspend fun get(): Cashbox {
        return api.getCashboxResource().body()
    }
}