package com.github.onotoliy.opposite.viewmodel.transactions

import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.repositories.IFileRepository
import com.github.onotoliy.opposite.repositories.ITransactionRepository
import com.github.onotoliy.opposite.repositories.newTransaction
import com.github.onotoliy.opposite.viewmodel.AbstractViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.DrawableResource

class TransactionViewModel(
    private val files: IFileRepository,
    private val transactions: ITransactionRepository,
    private val uuid: String
) : AbstractViewModel<Transaction>() {

    private val _receipts = MutableStateFlow<List<DrawableResource>>(mutableListOf())

    val receipts: StateFlow<List<DrawableResource>> = _receipts

    override suspend fun get(): Transaction = transactions.get(uuid)

    override suspend fun loadAdditionalValues() {
        _receipts.value = mutableListOf(files.download(uuid))
    }

    override val defaultValue: Transaction
        get() = newTransaction(-1000, -1000, -1000)

}
