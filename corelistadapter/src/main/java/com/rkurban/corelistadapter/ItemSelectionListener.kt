package com.rkurban.corelistadapter

interface ItemSelectionListener<T> {

    fun onItemSelected(item: T, position: Int)
}