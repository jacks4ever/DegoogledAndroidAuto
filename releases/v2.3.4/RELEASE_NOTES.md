# DegoogledAndroidAuto v2.3.4 Release Notes

## What's New

- **App renamed to "Degoogled Android Auto"**
- Fixed "Android Auto stopped unexpectedly" error on Nissan Pathfinder and other head units
- Added automatic service recovery when connection is interrupted
- Enhanced service stability with improved error handling
- Added detailed logging for connection issues
- Added automatic retry mechanism after connection failures
- Improved notification handling
- Added special handling for Nissan head units
- Enhanced USB accessory detection with multiple manufacturer/model combinations

## Troubleshooting Connection Issues

- If you see "Android Auto stopped unexpectedly" error:
  1. The app will now automatically attempt to reconnect
  2. If it still fails, disconnect and reconnect your phone to the car's USB port
  3. Try a different USB cable if available
  4. Make sure to use the USB port labeled for phone connection, not the media-only port
  5. The app will now show more detailed error messages to help diagnose the issue

- If you see "Missing permissions" error:
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