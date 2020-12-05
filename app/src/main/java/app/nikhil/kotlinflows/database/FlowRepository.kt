package app.nikhil.kotlinflows.database

import android.content.Context

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

  fun getAllItems() = database.getItemDao()
    .getAllItems()
}