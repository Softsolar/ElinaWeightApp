
package com.example.elinaweight.data
import kotlinx.coroutines.*
class WeightRepository(private val dao: WeightDao){ suspend fun all()=withContext(Dispatchers.IO){ dao.getAll() }; suspend fun save(e:WeightEntry)=withContext(Dispatchers.IO){ dao.upsert(e) }; suspend fun clear()=withContext(Dispatchers.IO){ dao.clear() } }
