# Degoogled Android Auto

A privacy-focused, open source Android Auto alternative that doesn't rely on Google services or proprietary code.

## Versions

This repository contains two implementations:

1. **Fully Degoogled Version**: Zero Google dependencies, runs as a standalone app on your phone
2. **Minimal Google Version**: Uses minimal Google dependencies to connect with your car's head unit display

## Latest Release

**v2.3.4** - [Download APK](https://github.com/jacks4ever/DegoogledAndroidAuto/releases/download/v2.3.4/DegoogledAndroidAuto-v2.3.4.apk)

New in this version:
- **App renamed to "Degoogled Android Auto"**
- Fixed "Android Auto stopped unexpectedly" error on Nissan Pathfinder and other head units
- Added automatic service recovery when connection is interrupted
- Enhanced service stability with improved error handling
- Added detailed logging for connection issues
- Added automatic retry mechanism after connection failures
- Improved notification handling
- Added special handling for Nissan head units
- Enhanced USB accessory detection with multiple manufacturer/model combinations
- Fixed permission handling for Android 13+ (API 33+)
- Added support for BLUETOOTH_SCAN permission
- Added support for READ_MEDIA_AUDIO and READ_MEDIA_IMAGES permissions
- Added support for NEARBY_WIFI_DEVICES permission
- Fixed app recognition for apps installed via Aurora Store (not just Google Play)
- Added support for alternative messaging apps (WhatsApp Business, Signal, QKSMS, etc.)
- Improved USB accessory detection and connection handling
- Enhanced privacy controls for third-party apps

**Note:** This version includes important fixes for the "Android Auto stopped unexpectedly" error and renames the app to better reflect its purpose.

## Features

- **Privacy-First Navigation**: Uses OpenStreetMap for maps and routing without tracking
  - Optional Waze integration with privacy filtering
- **Offline Voice Assistant**: Local voice command processing without cloud dependencies
- **Open Source Media Player**: Play music from local files or open source streaming services
- **Secure Messaging**: Matrix protocol integration for private, encrypted messaging
  - Integrations with WhatsApp, Microsoft Teams, and Handcent SMS
- **Home Automation**: Control August Lock and other smart home devices
- **Driver-Friendly UI**: Large touch targets, dark mode, and minimal distractions
- **Works Offline**: All core features function without an internet connection
- **Minimal Google Dependencies**: Only essential Google components for car connectivity in the Minimal Google version

## Installation

### Fully Degoogled Version

1. Download the latest APK from the [Releases](https://github.com/jacks4ever/DegoogledAndroidAuto/releases) page
2. Enable "Install from Unknown Sources" in your Android settings
3. Install the APK
4. Grant the requested permissions when prompted

### Minimal Google Version

1. Download the Minimal Google Auto APK from the [Releases](https://github.com/jacks4ever/DegoogledAndroidAuto/releases) page
2. Enable "Install from Unknown Sources" in your Android settings
3. Install the APK
4. Grant the requested permissions when prompted (make sure to grant ALL requested permissions)
5. Connect your phone to your car's USB port or use wireless connection if supported
6. Enable Privacy Mode in the app settings
7. Tap "Connect to Car" to connect to your car's head unit display

**Troubleshooting Connection Issues:**
- If you see "Android Auto closed unexpectedly" error:
  1. Disconnect and reconnect your phone to the car's USB port
  2. Try a different USB cable if available
  3. Make sure to use the USB port labeled for phone connection, not the media-only port
  4. The app will now show more detailed error messages to help diagnose the issue
- If you see "Missing permissions" error:
  1. The app will now show exactly which permissions are missing
  2. Go to Settings > Apps > DegoogledAndroidAuto > Permissions
  3. Grant ALL permissions shown in the error message
  4. Return to the app and try connecting again
- If you see "USB permission required" message:
  1. Tap "Allow" when prompted for USB permission
  2. If not prompted, disconnect and reconnect your phone to the car's USB port
  3. Try a different USB cable if available
- For Nissan Pathfinder owners: This version includes special handling for your vehicle

## Usage

### Navigation
- Tap the Navigation button to open the map view
- Search for destinations or tap on the map
- Choose your preferred route type (car, bicycle, walking)
- Follow turn-by-turn directions with voice guidance

### Voice Assistant
- Tap the microphone button or say the wake word
- Give commands like "Navigate to [location]", "Play [song/artist]", or "Send message to [contact]"
- The assistant processes all commands locally on your device

### Media Player
- Browse your local music library
- Connect to supported open source streaming services
- Create and manage playlists
- Control playback with voice commands

### Messaging
- Set up your Matrix account in Settings
- View and respond to messages with large, easy-to-read text
- Dictate replies using the voice assistant
- Receive notifications for new messages
- Integrations with popular messaging apps:
  - WhatsApp/WhatsApp Business: Send messages and view conversations
  - Microsoft Teams: Chat with colleagues and join meetings
  - Handcent SMS/Signal/QKSMS/Silence: Manage SMS messages with a privacy-focused interface
  
**Note:** The app now supports messaging apps installed from any source, including Aurora Store and F-Droid, not just Google Play Store.

## Privacy & Security

### Fully Degoogled Version

- **No Data Collection**: The app does not collect or transmit any usage data
- **Minimal Permissions**: Only requests permissions essential for functionality
- **Local Processing**: Voice commands and navigation calculations happen on-device
- **Open Source**: All code is available for review and verification
- **End-to-End Encryption**: Messaging uses Matrix protocol's encryption
- **Zero Google Dependencies**: No Google libraries, services, or APIs

### Minimal Google Version

- **Isolation Layer**: All Google components are isolated in a proxy layer
- **Network Filtering**: Blocks all analytics, tracking, and non-essential API calls
- **Minimal Authentication**: Uses only essential Google authentication for the Android Auto protocol
- **Local Processing**: All voice commands are processed locally
- **Network Monitoring**: Monitors and logs all network requests
- **Privacy Mode**: Master switch for all privacy features

## Building from Source

### Prerequisites
- Android Studio 4.2+
- JDK 11+
- Android SDK 30+

### Build Steps
1. Clone the repository: `git clone https://github.com/jacks4ever/DegoogledAndroidAuto.git`
2. Open the project in Android Studio
3. Sync Gradle files
4. Build the project: `./gradlew assembleRelease`
5. Find the APK in `app/build/outputs/apk/release/`

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch: `git checkout -b feature/amazing-feature`
3. Commit your changes: `git commit -m 'Add some amazing feature'`
4. Push to the branch: `git push origin feature/amazing-feature`
5. Open a Pull Request

## License

This project is licensed under the GNU General Public License v3.0 - see the LICENSE file for details.

## Acknowledgments

- [OpenStreetMap](https://www.openstreetmap.org/) for map data
- [OSMDroid](https://github.com/osmdroid/osmdroid) for map rendering
- [Vosk](https://alphacephei.com/vosk/) for offline speech recognition
- [Matrix.org](https://matrix.org/) for the messaging protocol
- [OpenAuto](https://github.com/f1xpl/openauto) for Android Auto protocol research
- [WhatsApp](https://www.whatsapp.com/), [Microsoft Teams](https://www.microsoft.com/en-us/microsoft-teams/), and [Handcent SMS](https://www.handcent.com/) for messaging app integrations
- [Signal](https://signal.org/), [QKSMS](https://github.com/moezbhatti/qksms), and [Silence](https://silence.im/) for alternative SMS app integrations
- [August Lock](https://august.com/) and [Yale Access](https://www.yalehome.com/yale-access) for home automation integration
- [Aurora Store](https://auroraoss.com/) for Google Play Store alternative
- All other open source projects that made this possible

## Branches

- `main`: Contains the fully degoogled version
- `minimal-google-auto`: Contains the minimal Google version that works with car head units