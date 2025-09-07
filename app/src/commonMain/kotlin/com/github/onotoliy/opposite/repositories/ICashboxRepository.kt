package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.data.Cashbox

interface ICashboxRepository {

    suspend fun get(): Cashbox

}