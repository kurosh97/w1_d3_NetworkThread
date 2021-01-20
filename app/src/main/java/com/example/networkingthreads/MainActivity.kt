package com.example.networkingthreads

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isNetworkAvailable()) {
            val myRunnable = Conn(mHandler, "Koe.txt")
            val myThread = Thread(myRunnable)
            myThread.start()
        }
    }


    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(inputMsg: Message) {
            if (inputMsg.what == 0) {
                tv_network_txt.text = inputMsg.obj.toString()
            }
        }
    }





    private fun isNetworkAvailable(): Boolean =
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).isDefaultNetworkActive
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }
}