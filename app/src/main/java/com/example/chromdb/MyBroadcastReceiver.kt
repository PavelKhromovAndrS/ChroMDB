package com.example.chromdb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

private const val CONNECT_CHANGE = "CONNECT_CHANGE"

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, CONNECT_CHANGE, Toast.LENGTH_LONG).show()
    }
}