package app.nikhil.kotlinflows.database

import android.content.Context
import kotlinx.coroutines.flow.Flow

class FlowRepository private constructor() {

  companion object {
    private lateinit var database: FlowDatabase
    private lateinit var INSTANCE: FlowRepository

    fun init(context: Context) {
      database = FlowDatabase.getInstance(context)
      INSTANCE = FlowRepository()
    }

    fun getInstance(): FlowRepository = INSTANCE
  }

  fun getAllItems(): Flow<List<Item>> = database.getItemDao().getAllItems()
}