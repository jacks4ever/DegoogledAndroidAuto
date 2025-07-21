# DegoogledAndroidAuto v2.3.3 Release Notes

## What's New

- Fixed "Android Auto closed unexpectedly" error on Nissan Pathfinder
- Added special handling for Nissan head units
- Enhanced USB accessory detection with multiple manufacturer/model combinations
- Added detailed logging for USB accessory connections
- Improved error handling with better error messages
- Added multiple connection methods for different head unit types
- Enhanced projection status handling
- Added better error recovery for connection failures
- Fixed permission handling for Android 13+ (API 33+)

## Troubleshooting Connection Issues

- If you see "Android Auto closed unexpectedly" error:
  1. Disconnect and reconnect your phone to the car's USB port
  2. Try a different USB cable if available
  3. Make sure to use the USB port labeled for phone connection, not the media-only port
  4. The app will now show more detailed error messages to help diagnose the issue

- If you still see "Missing permissions" error:
  1. The app will now show exactly which permissions are missing
  2. Go to Settings > Apps > DegoogledAndroidAuto > Permissions
  3. Grant ALL permissions shown in the error message
  4. Return to the app and try connecting again

## Installation

1. Download the APK file
2. Enable "Install from Unknown Sources" in your Android settings
3. Install the APK
4. Grant ALL requested permissions when prompted
5. Connect your phone to your car's USB port
6. Enable Privacy Mode in the app settings
7. Tap "Connect to Car" to connect to your car's head unit display