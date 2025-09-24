package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.treasure.api.CashboxResourceApi
import com.github.onotoliy.opposite.treasure.client.infrastructure.HttpResponse
import com.github.onotoliy.opposite.treasure.model.Cashbox
import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.readRemaining
import io.ktor.utils.io.readText

class CashboxRepository(private val api: CashboxResourceApi) {
    @OptIn(InternalAPI::class)
    suspend fun get(): Cashbox {
        return api.getCashboxResource().toRespose()
    }
}
