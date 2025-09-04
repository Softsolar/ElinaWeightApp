
package com.example.elinaweight.data
import androidx.room.*
@Dao interface WeightDao {
  @Query("SELECT * FROM weights ORDER BY week ASC") suspend fun getAll(): List<WeightEntry>
  @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun upsert(entry: WeightEntry)
  @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun upsertAll(list: List<WeightEntry>)
  @Query("DELETE FROM weights") suspend fun clear()
}
