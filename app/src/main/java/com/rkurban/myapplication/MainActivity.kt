package com.rkurban.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.rkurban.corelistadapter.ItemSelectionListener
import com.rkurban.myapplication.api.Api
import com.rkurban.myapplication.api.ApiHelper
import com.rkurban.myapplication.app.TestListAdapter
import com.rkurban.myapplication.app.TestModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<List<TestModel>> {

    private val adapter: TestListAdapter by lazy { TestListAdapter() }
    private var service: Api = ApiHelper.getService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        service.apiList("2").enqueue(this@MainActivity)
    }

    private fun initUI() {
        recyclerView.adapter = adapter
        adapter.onItemSelectionListener = itemSelection
    }

    private val itemSelection = object : ItemSelectionListener<TestModel> {
        override fun onItemSelected(item: TestModel, position: Int) {
            if (item.name != null)
                mToast(item.name)
        }
    }

    fun mToast(msg: Any) {
            Toast.makeText(this@MainActivity, msg.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<List<TestModel>>?, response: Response<List<TestModel>>?) {
        adapter.submitList(response?.body())
    }

    override fun onFailure(call: Call<List<TestModel>>?, t: Throwable?) {
        mToast(t?.message!!)
    }

}

