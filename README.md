# Degoogled Android Auto

A privacy-focused, open source Android Auto alternative that doesn't rely on Google services or proprietary code.

## Versions

This repository contains two implementations:

1. **Fully Degoogled Version**: Zero Google dependencies, runs as a standalone app on your phone
2. **Minimal Google Version**: Uses minimal Google dependencies to connect with your car's head unit display

## Latest Release

**v2.2.0** - [Download APK](https://github.com/jacks4ever/DegoogledAndroidAuto/releases/download/v2.2.0/MinimalGoogleAuto-v2.2.0.apk)

New in this version:
- WhatsApp, Microsoft Teams, and Handcent SMS integrations
- Enhanced privacy controls for third-party apps
- Improved stability and performance
- Bug fixes and UI enhancements

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
4. Grant the requested permissions when prompted
5. Connect your phone to your car's USB port or use wireless connection if supported
6. Enable Privacy Mode in the app settings
7. Tap "Connect to Car" to connect to your car's head unit display

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
  - WhatsApp: Send messages and view conversations
  - Microsoft Teams: Chat with colleagues and join meetings
  - Handcent SMS: Manage SMS messages with a privacy-focused interface

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
- [August Lock](https://august.com/) for home automation integration
- All other open source projects that made this possible

## Branches

- `main`: Contains the fully degoogled version
- `minimal-google-auto`: Contains the minimal Google version that works with car head units