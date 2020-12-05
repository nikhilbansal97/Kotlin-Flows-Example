package app.nikhil.kotlinflows

import android.app.Application
import app.nikhil.kotlinflows.database.FlowRepository

class FlowApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    FlowRepository.init(this)
  }
}