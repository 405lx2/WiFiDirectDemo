package com.example.liangx.wifidirectdemo.feature

import com.example.liangx.wifidirectdemo.feature.ScanListener

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    protected var mWiFiDirectController: WiFiDirectController? = null

    private var mListener: ((pos: String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mWiFiDirectController = WiFiDirectController(this, mListener)
    }


    fun ScanPeersDevice(view: View) {
        mWiFiDirectController?.scanPeers()
    }


}
