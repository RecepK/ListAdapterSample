package com.rkurban.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.rkurban.corelistadapter.ItemSelectionListener
import com.rkurban.myapplication.api.Api
import com.rkurban.myapplication.api.ApiHelper
import com.rkurban.myapplication.app.TestListAdapter
import com.rkurban.myapplication.app.TestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.function.Consumer

class MainActivity : AppCompatActivity() {

    private val adapter: TestListAdapter by lazy { TestListAdapter() }
    private var service: Api = ApiHelper.getService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        setUI()
    }

    private val itemSelection = object : ItemSelectionListener<TestModel> {
        override fun onItemSelected(item: TestModel, position: Int) {
            if (item.name != null)
                mToast(item.name)
        }
    }

    private fun initUI() {
        recyclerView.adapter = adapter
        adapter.onItemSelectionListener = itemSelection
    }

    private fun setUI() {
        service.apiList("2").enqueue(object : Callback<List<TestModel>> {
            override fun onResponse(call: Call<List<TestModel>>, response: Response<List<TestModel>>) {
                adapter.submitList(response.body())
            }

            override fun onFailure(call: Call<List<TestModel>>, t: Throwable) {
                mToast(t.message!!)
            }
        })
    }

    private fun setUI2() {
        service.apiListRx("2")
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.newThread())
                .subscribe { testModels -> adapter.submitList(testModels) }
    }

    private fun setUI3() {

        val consumer = Consumer<List<TestModel>> { t -> adapter.submitList(t) }


        Observable.just(getList())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(consumer)


        //.subscribe(Consumer<List<TestModel>> { testModels -> adapter.submitList(testModels) })
    }


    fun mToast(msg: Any) {
        Toast.makeText(this@MainActivity, msg.toString(), Toast.LENGTH_SHORT).show()
    }


    fun getList(): List<TestModel> {
        val list = ArrayList<TestModel>()
        list.add(TestModel(0, "a"))
        list.add(TestModel(1, "b"))
        list.add(TestModel(2, "c"))
        list.add(TestModel(3, "d"))
        return list
    }

}

private fun <T> Observable<T>.subscribe(consumer: Consumer<T>) {

}
