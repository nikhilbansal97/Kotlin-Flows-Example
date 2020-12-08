package app.nikhil.kotlinflows.itemslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import app.nikhil.kotlinflows.database.FlowRepository

class ItemsListViewModel : ViewModel() {

  private val repository by lazy { FlowRepository.getInstance() }

  val allItemsFlow = repository.getAllItems().asLiveData()
}