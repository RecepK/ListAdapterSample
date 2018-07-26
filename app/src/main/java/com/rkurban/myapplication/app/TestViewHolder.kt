package com.rkurban.myapplication.app

import android.view.View
import com.rkurban.corelistadapter.CoreViewHolder
import kotlinx.android.synthetic.main.layout_row.view.*

class TestViewHolder(itemView: View) : CoreViewHolder<TestModel>(itemView) {

    override fun bind(item: TestModel) {
        itemView.tv.text = item.name
        itemView.tv.isClickable = false
    }

}