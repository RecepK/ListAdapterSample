package com.rkurban.myapplication.app

import android.support.v7.util.DiffUtil

class TestDiffCallback : DiffUtil.ItemCallback<TestModel>() {

    override fun areItemsTheSame(oldItem: TestModel?, newItem: TestModel?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: TestModel?, newItem: TestModel?): Boolean {
        return oldItem?.name == newItem?.name
    }

}