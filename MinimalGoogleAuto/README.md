# Minimal Google Auto

A privacy-focused Android Auto alternative that uses minimal Google dependencies while providing a full-featured driving experience.

## Overview

Minimal Google Auto is designed to work with your car's head unit display while minimizing Google dependencies. It replaces all Google services with open-source alternatives where possible, and isolates the minimal required Google components for car connectivity.

### Features

- **Navigation**: Uses OpenStreetMap for maps and routing
- **Waze Integration**: Privacy-focused Waze navigation with analytics blocking
- **Voice Assistant**: Offline voice recognition with Vosk
- **Media Playback**: Local and streaming media playback with ExoPlayer
- **Messaging**: Matrix protocol for secure messaging
- **Privacy Protection**: Network filtering and monitoring to block tracking

## Privacy Approach

This app takes a unique approach to privacy:

1. **Isolation Layer**: All Google components are isolated in a proxy layer
2. **Network Filtering**: Blocks all analytics, tracking, and non-essential API calls
3. **Minimal Authentication**: Uses only essential Google authentication for the Android Auto protocol
4. **Local Processing**: All voice commands are processed locally
5. **Network Monitoring**: Monitors and logs all network requests

## Requirements

- Android 7.0 (API 24) or higher
- Car with Android Auto support
- Internet connection (for initial setup, can work offline afterward)
- Storage space for offline maps

## Installation

1. Download the APK from the releases page
2. Enable "Install from Unknown Sources" in your device settings
3. Install the APK
4. Grant the requested permissions
5. Download the Vosk model for offline voice recognition
6. Download offline maps for your region

## Usage

1. Connect your phone to your car's USB port or use wireless connection if supported
2. Launch Minimal Google Auto
3. Enable Privacy Mode
4. Tap "Connect to Car"
5. Your phone will connect to your car's head unit display

## Privacy Settings

- **Privacy Mode**: Master switch for all privacy features
- **Block Analytics**: Blocks all Google analytics and tracking
- **Minimal Authentication**: Uses only essential Google authentication
- **Offline Mode**: Uses only offline features (requires downloaded maps)
- **Network Monitoring**: Monitors and logs all network requests
- **Waze Privacy Protection**: Blocks Waze analytics and tracking while allowing essential navigation features

## Technical Details

### Minimal Google Dependencies

This app uses only these Google components:
- `com.google.android.gms:play-services-auth`: For minimal authentication
- Android Auto protocol libraries (accessed via reflection)

### Open Source Components

- **Maps**: OSMDroid for map rendering
- **Voice**: Vosk for offline voice recognition
- **Media**: ExoPlayer for media playback
- **Messaging**: Matrix SDK for secure messaging

### Network Security

- Network security policy enforces HTTPS for all connections
- Domain pinning for essential Google domains
- Blocking of known analytics and tracking domains
- Waze-specific network filtering to block non-essential requests

## Building from Source

1. Clone the repository
2. Open the project in Android Studio
3. Build the project

## License

This project is licensed under the GNU General Public License v3.0 - see the LICENSE file for details.

## Acknowledgements

- [OSMDroid](https://github.com/osmdroid/osmdroid) for map rendering
- [Waze](https://www.waze.com/) for navigation data (with privacy protections)
- [Vosk](https://alphacephei.com/vosk/) for offline voice recognition
- [ExoPlayer](https://github.com/google/ExoPlayer) for media playback
- [Matrix SDK](https://github.com/matrix-org/matrix-android-sdk2) for messaging
- [OpenAuto](https://github.com/f1xpl/openauto) for Android Auto protocol research