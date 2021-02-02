package com.example.flutter_platformview_example

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.navigation.ui.NavigationView
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.platform.PlatformView

class SecondWidget internal constructor(context: Context, id: Int, messenger: BinaryMessenger) : PlatformView, MethodCallHandler {
    private val view: View
    private val methodChannel: MethodChannel
    val navView: NavigationView

    override fun getView(): View {
        return view
    }

    init {
        Mapbox.getInstance(context, context.getString(R.string.mapbox_access_token))
        view = LayoutInflater.from(context).inflate(R.layout.second_widget, null)

        navView = view.findViewById(R.id.navigationView)
        navView?.onCreate(null)

        methodChannel = MethodChannel(messenger, "plugins/second_widget_$id")
        methodChannel.setMethodCallHandler(this)
    }

    override fun onMethodCall(methodCall: MethodCall, result: MethodChannel.Result) {
        when (methodCall.method) {
            "ping" -> ping(methodCall, result)
            else -> result.notImplemented()
        }
    }

    private fun ping(methodCall: MethodCall, result: Result) {
        result.success(null)
    }

    override fun dispose() {
    }
}