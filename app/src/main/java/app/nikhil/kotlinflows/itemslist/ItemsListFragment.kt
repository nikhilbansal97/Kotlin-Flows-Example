package app.nikhil.kotlinflows.itemslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import app.nikhil.kotlinflows.R.layout
import app.nikhil.kotlinflows.databinding.FragmentFlowCollectBinding
import kotlinx.coroutines.flow.collect

class ItemsListFragment : Fragment(layout.fragment_flow_collect) {

  private var binding: FragmentFlowCollectBinding? = null
  private val viewModel by viewModels<ItemsListViewModel>()
  private val itemAdapter by lazy { ItemsAdapter() }

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

    initRecyclerView()
    collectFlow()

    return view
  }

  private fun collectFlow() {
    viewModel.allItemsFlow.observe(viewLifecycleOwner, {
      it?.let { items -> itemAdapter.submitList(items) }
    })
  }

  private fun initRecyclerView() {
    binding?.recyclerView?.apply {
      layoutManager = LinearLayoutManager(requireContext())
      adapter = itemAdapter
    }
  }
}