# DegoogledAndroidAuto v2.3.2 Release Notes

## What's New

- Fixed "Required permission not granted" error with improved permission handling
- Added support for Android 13+ (API 33+) permissions
- Added USB permission handling for car connections
- Added detailed error messages showing exactly which permissions are missing
- Enhanced permission checking with automatic connection retry
- Added support for BLUETOOTH_SCAN permission
- Added support for READ_MEDIA_AUDIO and READ_MEDIA_IMAGES permissions
- Added support for NEARBY_WIFI_DEVICES permission
- Fixed permission handling for newer Android versions

## Troubleshooting Connection Issues

- If you still see "Missing permissions" error:
  1. The app will now show exactly which permissions are missing
  2. Go to Settings > Apps > DegoogledAndroidAuto > Permissions
  3. Grant ALL permissions shown in the error message
  4. Return to the app and try connecting again
- If you see "USB permission required" message:
  1. Tap "Allow" when prompted for USB permission
  2. If not prompted, disconnect and reconnect your phone to the car's USB port
  3. Try a different USB cable if available

## Installation

1. Download the APK file
2. Enable "Install from Unknown Sources" in your Android settings
3. Install the APK
4. Grant ALL requested permissions when prompted
5. Connect your phone to your car's USB port
6. Enable Privacy Mode in the app settings
7. Tap "Connect to Car" to connect to your car's head unit display