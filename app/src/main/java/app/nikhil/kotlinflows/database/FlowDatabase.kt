package app.nikhil.kotlinflows.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import app.nikhil.kotlinflows.database.FlowDatabase.Companion.DATABASE_VERSION

@Database(entities = [Item::class], version = DATABASE_VERSION, exportSchema = false)
abstract class FlowDatabase : RoomDatabase() {

  companion object {
    private var INSTANCE: FlowDatabase? = null
    private const val DATABASE_NAME = "items.db"
    const val DATABASE_VERSION = 1

    fun getInstance(context: Context): FlowDatabase {
      return synchronized(this) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context, FlowDatabase::class.java, DATABASE_NAME)
            .addCallback(object : RoomDatabase.Callback() {
              override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL("INSERT INTO item (id, name) VALUES (1, 'Item 1')")
              }
            })
            .build()
        }
        return@synchronized INSTANCE!!
      }
    }
  }

  abstract fun getItemDao(): ItemDao
}