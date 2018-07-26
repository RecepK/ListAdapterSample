package com.rkurban.myapplication.app

import android.view.View
import com.rkurban.corelistadapter.CoreListAdapter
import com.rkurban.myapplication.R

class TestListAdapter : CoreListAdapter<TestModel, TestViewHolder, TestDiffCallback>(TestDiffCallback()) {

    override fun layoutResourceId(): Int= R.layout.layout_row

    override fun provideViewHolder(itemView: View): TestViewHolder =TestViewHolder(itemView)

}