package app.nikhil.kotlinflows

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import app.nikhil.kotlinflows.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {

  private val activityScope = CoroutineScope(Dispatchers.Main)
  private lateinit var binding: ActivityMainBinding

  companion object {
    private const val TAG = "MainActivity"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

    // This is just the definition of the flow.
    // It has not started emitting yet.
    val flow = flow {
      Log.d(TAG, "Started emitting values.");
      for (i in 1..10) {
        delay(2000)
        emit(i)
      }
    }

    activityScope.launch {
      // Start collector one
      async { collectFlow(flow, true) }
      // Wait
      delay(2000)
      // Start collector two
      async { collectFlow(flow, false) }
    }
  }

  private suspend fun collectFlow(
    flow: Flow<Int>,
    isFirst: Boolean
  ) {
    // Flow starts to emit when the collection starts.
    Log.d(TAG, "Started collecting values.");
    flow.collect { value ->
      when {
        isFirst -> binding.tvCollectorOne.text = "Collector 1: Got $value"
        else -> binding.tvCollectorTwo.text = "Collector 2: Got $value"
      }
    }
  }
}