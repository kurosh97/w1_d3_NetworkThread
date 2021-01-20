package com.example.networkingthreads

import android.os.Handler
import java.io.InputStream
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection

import java.net.URL
import javax.net.ssl.HttpsURLConnection


class Conn(
    mHand: Handler,
    text: String
) : Runnable {

    private val myHandler = mHand


    override fun run() {
        try {
            // 1. Create URL object instance
            val myUrl = URL("https://users.metropolia.fi/~jarkkov/koe.txt")
            //2. create url connective instance
            val myConn = myUrl.openConnection() as HttpsURLConnection
            //3. get the input stream
            val istream: InputStream = myConn.inputStream

            //4. read the stream with reader
            val allText = istream.bufferedReader().use { it.readText() }
            val result = StringBuilder()
            result.append(allText)
            val str = result.toString()


            val msg = myHandler.obtainMessage()
            msg.what = 0
            msg.obj = str
            myHandler.sendMessage(msg)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
