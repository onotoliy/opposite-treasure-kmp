package com.github.onotoliy.opposite.ui.transactions.models

import com.github.onotoliy.opposite.repositories.FileRepository
import com.github.onotoliy.opposite.repositories.TransactionRepository
import com.github.onotoliy.opposite.repositories.newTransaction
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.viewmodel.AbstractViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.DrawableResource

class TransactionViewModel(
    private val files: FileRepository,
    private val transactions: TransactionRepository,
    private val uuid: String
) : AbstractViewModel<Transaction>() {

    private val _receipts = MutableStateFlow<List<DrawableResource>>(mutableListOf())

    val receipts: StateFlow<List<DrawableResource>> = _receipts

    override suspend fun get(): Transaction = transactions.get(uuid)
    override suspend fun delete(uuid: String) = transactions.delete(uuid)

    override suspend fun loadAdditionalValues() {
        _receipts.value = mutableListOf(files.download())
    }

    override val defaultValue: Transaction
        get() = newTransaction()

}
