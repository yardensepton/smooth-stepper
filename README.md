# SmoothStepper :sparkles:	
SmoothStepper is a customizable stepper view library for Android, providing a smooth and user-friendly way to navigate through multi-step processes in your application.
This library allows you to create a step-by-step UI with tabs, buttons, and customizable styles.

## Features
Dynamic Tabs: Automatically generate and manage tabs for each step.
Flexible Step Data: Steps can contain any data or UI elements, allowing for a highly customizable experience.
Customizable Buttons: Change button colors, text, and sizes.
Step Validation: Check if the current step's data is valid before proceeding.
Custom Toast Messages: Display custom toast messages based on the step's requirements.
Icon Support: Add icons to tabs for better visual representation.
Clickability Control: Enable or disable tab clickability.

## Installation:
1. Add the following dependency to your build.gradle file:
   ```
dependencies {
    implementation ("com.github.yardensepton:smooth-stepper:1.0.0")
}
```

2. Add the following code to your settings.gradle.kts file (if using Gradle 7.0 and above):
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url =uri("https://jitpack.io") }

    }
}
```
For older versions of Gradle, add the repositories block directly to the build.gradle file of the root project:
```
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}
```



