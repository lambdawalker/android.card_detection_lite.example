# Card Detection Lite Example

This project is a sample application that demonstrates the usage of the [android.card_detection_lite](https://github.com/lambdawalker/android.card_detection_lite) library for real-time card detection using the device's camera.

## Features

*   Detects id cards in a live camera feed.
*   Built with Jetpack Compose.
*   Uses the `CardDetectorLite` composable for easy integration.
*   Handles camera permissions gracefully using the permissions-compose library.
*   Provides a simple example of how to receive and handle card detection events.

## Usage

The core of this example is the `CardDetectorLite` composable. Here's how it's used in `MainActivity.kt`:

```kotlin

HandleCameraPermission {
    val isDetectionEnabled by mainViewModel.isDetectionEnabled.collectAsStateWithLifecycle()

    CardDetectorLite(
        modifier = Modifier.fillMaxSize(),
        modelPath = ModelCatalog.TfLite.modelPath,
        classLabels = ModelCatalog.TfLite.classes,
        cardClasses = ModelCatalog.TfLite.cardClasses,
        useGpu = true,
        scoreThreshold = 0.50f,
        showBoundingBoxes = true,
        showClassNames = true,
        showFlashlightSwitch = true,
        isDetectionEnabled = isDetectionEnabled,
        onCardDetection = mainViewModel::onDetection
    )
}
```

The `onCardDetection` callback receives a `CardDetection` object, which can then be processed as shown in `MainViewModel.kt`.

## Dependencies

Here are the dependencies used in this project:

### libs.versions.toml

```toml
[versions]
carddetectionlite = "0.1.0-B2"
tfmodel = "0.1.0-B2"
permissioncompose = "0.0.1-B0"

[libraries]
af-cdl-core = { module = "com.apexfission.android.carddetectionlite:core", version.ref = "carddetectionlite" }
af-cdl-sentinel = { module = "com.apexfission.android.carddetectionlite:sentinel-card-model-TFY11640F16", version.ref = "tfmodel" }
af-permission-compose = { module = "com.apexfission.android.permissionscompose:core", version.ref = "permissioncompose" }
```

### app/build.gradle.kts

```kotlin
dependencies {
    implementation(libs.af.cdl.core)
    implementation(libs.af.cdl.sentinel)
    implementation(libs.af.permission.compose)
}
```

## Building and Running

1.  Clone this repository.
2.  Open the project in Android Studio.
3.  Build and run the application on an Android device or emulator.

You will need to grant camera permission to use the app.
