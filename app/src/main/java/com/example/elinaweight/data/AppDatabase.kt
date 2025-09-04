
package com.example.elinaweight.data
import android.content.Context
import androidx.room.*
import kotlinx.coroutines.*
@Database(entities=[WeightEntry::class], version=1, exportSchema=false)
abstract class AppDatabase: RoomDatabase(){ abstract fun dao(): WeightDao
  companion object { @Volatile private var INSTANCE: AppDatabase? = null
    fun get(ctx: Context)= INSTANCE ?: synchronized(this){ Room.databaseBuilder(ctx, AppDatabase::class.java, "elina.db").fallbackToDestructiveMigration().build().also{ db-> INSTANCE=db; CoroutineScope(Dispatchers.IO).launch{ if(db.dao().getAll().isEmpty()) db.dao().upsertAll(SeedData.entries()) } } }
  }
}
