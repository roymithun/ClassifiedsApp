package com.inhouse.classifiedsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.inhouse.classifiedsapp.core.model.ClassifiedAd
import com.inhouse.classifiedsapp.databinding.ListItemBinding

class ClassifiedsAdListAdapter(private val clickListener: OnClickListener) :
    ListAdapter<ClassifiedAd, ClassifiedsAdListAdapter.ViewHolder>(ClassifiedAdItemDiffCallback()) {

    inner class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(classifiedAd: ClassifiedAd, clickListener: OnClickListener) {
            binding.classifiedAd = classifiedAd
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    interface OnClickListener {
        fun onClick(classifiedAd: ClassifiedAd)
    }

    class ClassifiedAdItemDiffCallback : DiffUtil.ItemCallback<ClassifiedAd>() {
        override fun areItemsTheSame(oldItem: ClassifiedAd, newItem: ClassifiedAd): Boolean =
            oldItem.uid == newItem.uid &&
                    oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: ClassifiedAd, newItem: ClassifiedAd): Boolean =
            oldItem == newItem
    }
}
