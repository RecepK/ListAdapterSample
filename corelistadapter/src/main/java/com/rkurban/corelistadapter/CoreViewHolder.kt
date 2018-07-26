package com.rkurban.corelistadapter

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class CoreViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)
}