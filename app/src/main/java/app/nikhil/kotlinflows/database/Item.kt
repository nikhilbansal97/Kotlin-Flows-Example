package app.nikhil.kotlinflows.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
  @PrimaryKey
  val id: Int,
  val name: String
)