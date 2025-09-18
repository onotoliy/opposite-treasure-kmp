package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.treasure.api.DepositResourceApi
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.treasure.model.PageDeposit
import com.github.onotoliy.opposite.treasure.model.PageEvent
import com.github.onotoliy.opposite.viewmodel.Page
import io.ktor.utils.io.InternalAPI
import kotlin.String

@OptIn(InternalAPI::class)
class DepositeRepository(
    private val api: DepositResourceApi
) {

    suspend fun me(): Deposit {
        return api.getDepositResource1().toRespose()
    }

    suspend fun get(uuid: String): Deposit {
        return api.getDepositResource(uuid).toRespose()
    }

    suspend fun getAll(
        q: String?,
        offset: Int,
        numberOfRows: Int
    ): PageDeposit {
        return api
            .getAllDepositResource(q = q, enable = null, offset = offset, numberOfRows = numberOfRows)
            .toRespose()
    }

    suspend fun getDebts(
        user: String,
        offset: Int,
        numberOfRows: Int
    ): PageEvent {
        return api
            .getDebtsDepositResource(uuid = user, offset = offset, numberOfRows = numberOfRows)
            .toRespose()
    }

    suspend fun create(deposit: Deposit): Deposit {
        return api.createDepositResource(deposit).toRespose()
    }

    suspend fun update(deposit: Deposit): Deposit {
        return api.updateDepositResource(deposit).toRespose()
    }

    suspend fun delete(uuid: String) {
        api.deleteDepositResource(uuid)
    }
}
