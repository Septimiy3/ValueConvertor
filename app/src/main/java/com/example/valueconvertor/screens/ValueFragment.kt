package com.example.valueconvertor.screens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import com.example.valueconvertor.R
import com.example.valueconvertor.adapter.Adapter
import com.example.valueconvertor.api.Api
import com.example.valueconvertor.data.Valute
import com.example.valueconvertor.data.ValuteInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_value.*
import java.util.*

class ValueFragment : Fragment(R.layout.fragment_value){

    private val mApi: Api? = Api.Instance.getApi()
    private val timer: Timer = Timer(Runnable { getValue() }, 5 * 80000)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getValue()

        timer.start()

        btn_check.setOnClickListener{
            getValue()
        }
    }

    fun getValue(){
        mApi!!.getValute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                data -> getData(data)
            }
    }

    fun getData(data:ValuteInfo){
        var listitem: ArrayList<Map<String, Valute>> = arrayListOf()

        for (i in 0 until data.valutes.size){
            listitem.add(data.valutes)
        }
        var adapter = Adapter(requireActivity(),R.layout.list_item,listitem)
        recycler_view.adapter = adapter
    }


    class Timer(val task: Runnable, val interval: Long) {

        private val handler = Handler(Looper.getMainLooper())
        private var isStopped = true

        private val mainTask = object : Runnable{
            override fun run() {
                if (!isStopped) {
                    task.run()
                    handler.postDelayed(this, interval)
                }
            }
        }

        fun start() {
            isStopped = false
            handler.postDelayed(mainTask, 0)
        }

        fun stop() {
            isStopped = true
        }
    }

}