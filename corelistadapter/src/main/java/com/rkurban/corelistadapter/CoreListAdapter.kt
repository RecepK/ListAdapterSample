package com.rkurban.corelistadapter

import android.support.annotation.LayoutRes
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


abstract class CoreListAdapter<T, VH : CoreViewHolder<T>, D : DiffUtil.ItemCallback<T>>(diffCallback: D) : ListAdapter<T, VH>(diffCallback) {

    var onItemSelectionListener: ItemSelectionListener<T>? = null

    @LayoutRes
    abstract fun layoutResourceId(): Int

    abstract fun provideViewHolder(itemView: View): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(layoutResourceId(), parent, false)
        return provideViewHolder(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemSelectionListener?.onItemSelected(item, holder.adapterPosition)
        }
    }

}
