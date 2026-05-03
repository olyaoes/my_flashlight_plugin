
import 'my_flashlight_plugin_platform_interface.dart';

class MyFlashlightPlugin {
  Future<String?> getPlatformVersion() {
    return MyFlashlightPluginPlatform.instance.getPlatformVersion();
  }
}
