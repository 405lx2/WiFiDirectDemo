package com.example.liangx.wifidirectdemo.feature

import com.example.liangx.wifidirectdemo.feature.ScanListener

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.p2p.WifiP2pDeviceList
import android.net.wifi.p2p.WifiP2pManager
import android.os.Looper
import android.util.Log
import android.widget.ListAdapter
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.liangx.wifidirectdemo.feature.R.id.list_devices


/**
 * Created by liangx on 2018/3/18.
 */

class WiFiDirectController : WifiP2pManager.ActionListener, WifiP2pManager.ChannelListener, WifiP2pManager.PeerListListener {
    private val TAG = "WiFiDirectController"
    private var manager: WifiP2pManager? = null
    private var isWifiP2pEnabled = false

    private var intentFilter = IntentFilter()
    private var channel: WifiP2pManager.Channel? = null

    private var mContext: Context? = null

    private var mListener: ScanListener? = null


    constructor(context: Context, ((pos: String) -> Unit))
    {
        manager = (context.getSystemService(Context.WIFI_P2P_SERVICE)) as WifiP2pManager
        channel = manager?.initialize(context, Looper.getMainLooper(), this)
        mContext = context
        mListener = scanListener

    }

    fun scanPeers() {
        manager?.discoverPeers(channel, this);
    }

    override fun onSuccess() {
        Log.i(TAG, "Seem success?")
        manager?.requestPeers(channel, this)

    }

    override fun onFailure(reason: Int) {
        Log.i(TAG, "Fail and reason is " + reason.toString())
    }

    override fun onChannelDisconnected() {
        Log.i(TAG, "Disconnect!")
    }

    override fun onPeersAvailable(peers: WifiP2pDeviceList) {
        Log.i(TAG, "Ah, we found something")
        Log.i(TAG, peers.toString())
        mListener?.setUI()
    }
}