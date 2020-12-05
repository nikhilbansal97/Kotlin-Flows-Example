package app.nikhil.kotlinflows.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
  @Query("SELECT * FROM item")
  fun getAllItems(): Flow<List<Item>>
}