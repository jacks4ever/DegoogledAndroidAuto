# Degoogled Android Auto

A privacy-focused, open source Android Auto alternative that doesn't rely on Google services or proprietary code.

## Features

- **Privacy-First Navigation**: Uses OpenStreetMap for maps and routing without tracking
- **Offline Voice Assistant**: Local voice command processing without cloud dependencies
- **Open Source Media Player**: Play music from local files or open source streaming services
- **Secure Messaging**: Matrix protocol integration for private, encrypted messaging
- **Driver-Friendly UI**: Large touch targets, dark mode, and minimal distractions
- **Works Offline**: All core features function without an internet connection
- **No Google Dependencies**: Zero Google libraries, services, or APIs

## Installation

1. Download the latest APK from the [Releases](https://github.com/jacks4ever/DegoogledAndroidAuto/releases) page
2. Enable "Install from Unknown Sources" in your Android settings
3. Install the APK
4. Grant the requested permissions when prompted

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

## Privacy & Security

- **No Data Collection**: The app does not collect or transmit any usage data
- **Minimal Permissions**: Only requests permissions essential for functionality
- **Local Processing**: Voice commands and navigation calculations happen on-device
- **Open Source**: All code is available for review and verification
- **End-to-End Encryption**: Messaging uses Matrix protocol's encryption

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
- All other open source projects that made this possible