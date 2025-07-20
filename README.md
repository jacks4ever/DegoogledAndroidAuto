# Degoogled Android Auto

A privacy-focused, open-source alternative to Android Auto that doesn't rely on Google services or proprietary APIs. This application provides a complete car infotainment system with navigation, media playback, messaging, and voice control, all without any Google dependencies.

## Features

- **Navigation**: Uses OpenStreetMap data with MapLibre for rendering and GraphHopper for routing
  - Offline maps for navigation without internet
  - Turn-by-turn directions with voice guidance
  - Search for locations, points of interest, and addresses
  - Route alternatives and traffic avoidance (when online)
  
- **Voice Assistant**: Offline voice recognition with Vosk for hands-free control
  - Completely offline speech recognition
  - Natural language processing for command interpretation
  - Voice feedback with offline text-to-speech
  - Customizable wake word and commands
  
- **Media Player**: Integrated media playback with libVLC, supporting local files and streaming
  - Play local music files from your device
  - Stream from open source services (when online)
  - Playlist management and library browsing
  - Audio focus management for navigation announcements
  
- **Messaging**: Secure messaging using the Matrix protocol
  - End-to-end encrypted messaging
  - Read incoming messages aloud
  - Voice-to-text for sending messages
  - Notification management with priority filtering
  
- **Privacy-Focused**: No Google services, Firebase, analytics, or proprietary APIs
  - Zero telemetry or data collection
  - No Google Play Services dependency
  - Minimal permissions required
  - Open source and auditable code
  
- **Offline-First**: All features work without an internet connection
  - Pre-downloaded maps and voice models
  - Local media playback
  - Cached messaging for offline viewing
  
- **Driver-Friendly UI**: Large touch targets, dark mode, and minimal distractions
  - High contrast interface for visibility
  - Large buttons and text for easy interaction while driving
  - Voice control for essential functions
  - Distraction minimization features

## Installation

### Requirements

- Android 6.0 (API level 23) or higher
- Permission for location, microphone, storage, and Bluetooth
- At least 500MB of free storage for offline maps and voice models

### Installation Steps

1. Download the latest APK from the [Releases](https://github.com/jacks4ever/DegoogledAndroidAuto/releases) page
2. Enable installation from unknown sources in your device settings
3. Install the APK
4. Launch the app and follow the setup wizard to:
   - Download offline maps for your region
   - Set up the voice assistant
   - Configure your Matrix account for messaging (optional)
   - Set up media sources

## Usage

### Navigation

1. Tap the Navigation icon in the main menu
2. Search for a destination using the search bar
3. Select a destination from the search results
4. Review the route details and tap "Start Navigation"
5. Follow the turn-by-turn directions

Voice commands:
- "Navigate to [destination]"
- "Find [point of interest]"
- "Stop navigation"

### Media Player

1. Tap the Media icon in the main menu
2. Browse your media library or playlists
3. Tap on a song, album, or playlist to start playback
4. Use the playback controls to play/pause, skip tracks, etc.

Voice commands:
- "Play [song/artist/album]"
- "Pause music"
- "Next track"
- "Previous track"

### Messaging

1. Tap the Messaging icon in the main menu
2. View your conversations
3. Tap on a conversation to view and send messages
4. Use the voice button to dictate messages

Voice commands:
- "Send message to [contact] saying [message]"
- "Read my messages"
- "Reply with [message]"

### Voice Assistant

1. Tap the microphone icon or say the wake word (if configured)
2. Speak your command clearly
3. The assistant will process your request and respond

## Building from Source

### Prerequisites

- Android Studio Arctic Fox (2020.3.1) or newer
- JDK 11 or newer
- Git
- 4GB+ RAM for building

### Dependencies

This project uses the following open-source libraries:

- **MapLibre GL** (com.mapbox.mapboxsdk:mapbox-android-sdk:9.6.0) - For map rendering
- **GraphHopper** (com.graphhopper:graphhopper-core:5.3) - For routing
- **Vosk** (com.alphacephei:vosk-android:0.3.32) - For offline speech recognition
- **libVLC** (org.videolan.android:libvlc-all:3.5.0) - For media playback
- **Matrix SDK** (org.matrix.android:matrix-sdk-android:1.5.0) - For secure messaging
- **Koin** (io.insert-koin:koin-android:3.2.0) - For dependency injection
- **Room** (androidx.room:room-runtime:2.4.2) - For local database
- **AndroidX** components - For UI and lifecycle management

All dependencies are open source and do not rely on Google Play Services or other proprietary libraries.

### Build Steps

1. Clone the repository:
   ```
   git clone https://github.com/jacks4ever/DegoogledAndroidAuto.git
   ```

2. Open the project in Android Studio

3. Sync Gradle files

4. Download required assets:
   ```
   ./gradlew downloadVoskModel downloadMapData
   ```

5. Build the debug version:
   ```
   ./gradlew assembleDebug
   ```

6. Install on your device:
   ```
   ./gradlew installDebug
   ```

### Creating a Release Build

1. Create a keystore file (if you don't have one):
   ```
   keytool -genkey -v -keystore degoogled-auto.keystore -alias degoogled -keyalg RSA -keysize 2048 -validity 10000
   ```

2. Configure signing in `app/build.gradle`:
   ```groovy
   signingConfigs {
       release {
           storeFile file("../degoogled-auto.keystore")
           storePassword "your-store-password"
           keyAlias "degoogled"
           keyPassword "your-key-password"
       }
   }
   ```

3. Build the release version:
   ```
   ./gradlew assembleRelease
   ```

4. The signed APK will be in `app/build/outputs/apk/release/`

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Privacy and Security

### Privacy Features

- **No Google Dependencies**: The app doesn't use any Google services, APIs, or libraries
- **No Tracking**: No analytics, telemetry, or usage tracking of any kind
- **Minimal Permissions**: Only requests permissions that are absolutely necessary
- **Local Processing**: Voice commands are processed entirely on-device
- **Offline Operation**: All core features work without internet access
- **End-to-End Encryption**: Messaging uses Matrix protocol with E2EE
- **Open Source**: All code is open source and can be audited

### Security Measures

- **Regular Security Updates**: Dependencies are kept up-to-date with security patches
- **Secure Connections**: All network connections use TLS/SSL
- **No Unnecessary Services**: No background services running when not needed
- **Permission Isolation**: Each feature only accesses the permissions it needs
- **Data Encryption**: Local data is encrypted using AndroidX Security
- **No Ads or Trackers**: No third-party advertising or tracking libraries

### Permissions Explained

- **LOCATION**: Required for navigation and routing
- **MICROPHONE**: Required for voice commands
- **STORAGE**: Required for offline maps and media playback
- **BLUETOOTH**: Required for car connectivity
- **INTERNET** (optional): Only used when explicitly requested by user

## License

This project is licensed under the GNU General Public License v3.0 - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- [MapLibre](https://maplibre.org/) for the open-source map rendering engine
- [OpenStreetMap](https://www.openstreetmap.org/) for the map data
- [GraphHopper](https://www.graphhopper.com/) for the routing engine
- [Vosk](https://alphacephei.com/vosk/) for the offline speech recognition
- [libVLC](https://www.videolan.org/vlc/libvlc.html) for the media playback
- [Matrix](https://matrix.org/) for the secure messaging protocol
- [Koin](https://insert-koin.io/) for the dependency injection framework
- [Room](https://developer.android.com/jetpack/androidx/releases/room) for the database library
