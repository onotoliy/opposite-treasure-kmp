package com.github.onotoliy.opposite.repositories

import com.github.opposite.treasure.shared.EventCacheRepository
import com.github.opposite.treasure.shared.IEventRepository

val events: IEventRepository = EventCacheRepository()