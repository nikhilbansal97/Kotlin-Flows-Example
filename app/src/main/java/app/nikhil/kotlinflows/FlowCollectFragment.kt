package app.nikhil.kotlinflows

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.nikhil.kotlinflows.databinding.FragmentFlowCollectBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FlowCollectFragment : Fragment(R.layout.fragment_flow_collect) {

  private val fragmentScope = CoroutineScope(Dispatchers.Main)
  private var binding: FragmentFlowCollectBinding? = null

  companion object {
    private const val TAG = "FlowCollectFragment"
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = super.onCreateView(inflater, container, savedInstanceState)
    binding = view?.let { FragmentFlowCollectBinding.bind(it) }

    val flow = createFlow()
    collect(flow)

    return view
  }

  private fun collect(flow: Flow<Int>) {
    fragmentScope.launch {
      // Start collector one
      val result1 = async { collectFlow(flow, true) }
      // Wait
      delay(2000)
      // Start collector two
      val result2 = async { collectFlow(flow, false) }

      listOf(result1, result2).awaitAll()
    }
  }

  // This is just the definition of the flow.
  // It has not started emitting yet.
  private fun createFlow(): Flow<Int> = flow {
    Log.d(TAG, "Started emitting values.")
    for (i in 1..10) {
      delay(2000)
      emit(i)
    }
  }

  private suspend fun collectFlow(
    flow: Flow<Int>,
    isFirst: Boolean
  ) {
    // Flow starts to emit when the collection starts.
    Log.d(TAG, "Started collecting values.")
    flow.collect { value ->
      when {
        isFirst -> binding?.tvCollectorOne?.text = "Collector 1: Got $value"
        else -> binding?.tvCollectorTwo?.text = "Collector 2: Got $value"
      }
    }
  }
}