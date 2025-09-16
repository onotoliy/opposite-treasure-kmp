package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.treasure.api.DepositResourceApi
import com.github.onotoliy.opposite.treasure.model.Deposit
import kotlin.String

class IDepositeRepository(
    private val api: DepositResourceApi
) {

    suspend fun get(uuid: String): Deposit {
        return api.getDepositResource(uuid).body()
    }

    suspend fun getAll(
        q: String?,
        offset: Int,
        numberOfRows: Int
    ): List<Deposit> {
        return api.getAllDepositResource(
            q = q,
            enable = null,
            offset = offset,
            numberOfRows = numberOfRows
        ).body().context ?: listOf()
    }

    suspend fun create(deposit: Deposit): Deposit {
        return api.createDepositResource(deposit).body()
    }

    suspend fun update(deposit: Deposit): Deposit {
        return api.updateDepositResource(deposit).body()
    }

    suspend fun delete(uuid: String) {
        api.deleteDepositResource(uuid)
    }
}
