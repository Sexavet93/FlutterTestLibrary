package com.flutterchannel.flutterlib

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {

    private val flutterToNativeChannelName = "com.example.flutter_to_native"
    private val nativeToFlutterChannelName = "com.example.native_to_flutter"

    companion object {
        lateinit var nativeToFlutterChannel: MethodChannel
    }

    private lateinit var flutterToNativeChannel: MethodChannel


    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        flutterToNativeChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, flutterToNativeChannelName)
        nativeToFlutterChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, nativeToFlutterChannelName)

        flutterToNativeChannel.setMethodCallHandler { call, result ->
            when (call.method) {
                "showAndroidView" -> {
                    showAndroidView()
                    result.success("Native method called")
                }
                else -> {
                    result.notImplemented()
                }
            }
        }
    }

    private fun showAndroidView() {
        val intent = Intent(this@MainActivity, TestActivity::class.java)
        startActivity(intent)
    }
}