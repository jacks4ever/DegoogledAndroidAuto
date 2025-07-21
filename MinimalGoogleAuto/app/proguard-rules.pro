# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Keep the minimal Google components we need for Android Auto
-keep class com.google.android.gms.auth.** { *; }
-keep class com.google.android.gms.common.** { *; }

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# OpenStreetMap
-dontwarn org.osmdroid.**
-keep class org.osmdroid.** { *; }

# ExoPlayer
-keep class com.google.android.exoplayer2.** { *; }

# Vosk
-keep class org.vosk.** { *; }
-keep class com.alphacephei.vosk.** { *; }

# Matrix SDK
-keep class org.matrix.android.sdk.** { *; }

# OkHttp
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

# Kotlin Coroutines
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# Block analytics and tracking
-keep class com.degoogled.minimalandroidauto.network.NetworkBlocker { *; }