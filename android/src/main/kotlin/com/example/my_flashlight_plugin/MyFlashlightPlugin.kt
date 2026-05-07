package com.example.my_flashlight_plugin

import android.content.Context
import android.hardware.camera2.CameraManager
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

class MyFlashlightPlugin: FlutterPlugin, MethodCallHandler {
  private lateinit var channel : MethodChannel
  private lateinit var context: Context
  private var isTorchOn = false

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "my_flashlight_plugin")
    channel.setMethodCallHandler(this)
    context = flutterPluginBinding.applicationContext
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "toggleLight") {
      try {
          val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
          val cameraId = cameraManager.cameraIdList[0]
          isTorchOn = !isTorchOn
          cameraManager.setTorchMode(cameraId, isTorchOn)
          result.success(null)
      } catch (e: Exception) {
          result.error("CAMERA_ERROR", e.message, null)
      }
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}