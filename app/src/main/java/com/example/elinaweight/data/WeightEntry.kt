
package com.example.elinaweight.data
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "weights")
data class WeightEntry(@PrimaryKey(autoGenerate = true) val id: Long = 0, val dateIso: String, val week: Int, val weightGrams: Int, val isAveraged: Boolean)
