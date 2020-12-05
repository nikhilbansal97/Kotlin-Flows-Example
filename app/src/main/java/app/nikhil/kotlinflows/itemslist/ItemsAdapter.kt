package app.nikhil.kotlinflows.itemslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.nikhil.kotlinflows.database.Item
import app.nikhil.kotlinflows.databinding.LayoutItemBinding
import app.nikhil.kotlinflows.itemslist.ItemsAdapter.ItemViewHolder

val DIFF_CALLBACK by lazy {
  object : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(
      oldItem: Item,
      newItem: Item
    ): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
      oldItem: Item,
      newItem: Item
    ): Boolean {
      return oldItem == newItem
    }

  }
}

class ItemsAdapter : ListAdapter<Item, ItemViewHolder>(DIFF_CALLBACK) {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ItemViewHolder {
    return ItemViewHolder(
      LayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }

  override fun onBindViewHolder(
    holder: ItemViewHolder,
    position: Int
  ) {
    holder.bind()
  }

  inner class ItemViewHolder(private val binding: LayoutItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind() {
      val item = getItem(adapterPosition)
      binding.itemTextView.text = item?.name ?: ""
    }
  }
}