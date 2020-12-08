package app.nikhil.kotlinflows.itemslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import app.nikhil.kotlinflows.database.FlowRepository

class ItemsListViewModel(application: Application) : AndroidViewModel(application) {

  private val repository by lazy { FlowRepository.getInstance() }

  val allItemsFlow = repository.getAllItems().asLiveData()
}