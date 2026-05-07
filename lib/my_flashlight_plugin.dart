import 'dart:io';
import 'package:flutter/services.dart';

class MyFlashlightPlugin {
  static const MethodChannel _channel = MethodChannel('my_flashlight_plugin');

  static Future<void> toggleLight() async {
    if (!Platform.isAndroid) {
      throw UnsupportedError('Platform not supported');
    }
    await _channel.invokeMethod('toggleLight');
  }
}
