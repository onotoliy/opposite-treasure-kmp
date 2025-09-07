package com.github.onotoliy.opposite.repositories

import org.jetbrains.compose.resources.DrawableResource

interface IFileRepository {
    suspend fun download(uuid: String): DrawableResource
}