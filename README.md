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

## Components Overview:
| Component | Description |
| --- | --- |
| BaseStepperFragment | An abstract class for creating fragments with step validation logic.|
| StepperAdapter | This adapter is used internally to manage the fragments for each step. It operates behind the scenes and is not directly interacted with by users of the library.  |
| StepperView | The main view component that manages navigation, tabs, and button controls. |

## Component Descriptions:

### BaseStepperFragment
| Method | Description |
| --- | --- |
| initializeUI(View view) |  Abstract method to be overridden by subclasses responsible for setting up and initializing the user interface elements specific to the fragment.  |
| isDataValid()	 | Abstract method to be overridden by subclasses to validate the step's data.|
| loadData() | Abstract method to be overridden by subclasses to load data if needed for that fragment. |
| setToastMessage() | Abstract method to be overridden by subclasses to add the toast message if needed for that step. This method allows each fragment to define a custom validation. |
| setTitle(String title) | Method to set the title for each base fragment. | 
| setTitleSize(float size) | Method to change the title's size | 
| setTitleColor(int color) | Method to change the title's color | 

### StepperView
| Method | Description |
| --- | --- |
| setSteps(FragmentActivity fragmentActivity, List<Fragment> fragments) |  Sets up the steps and initializes the stepper view with the provided fragments  |
| areTabsClickable(boolean areTabsClickable) | Enables or disables tab clickability.|
| addTabsIcons(ArrayList<Integer> drawableIds) | Adds icons to tabs. |
| setButtonsBackgroundColor(int color) | Sets the background color of the previous and next buttons. |
| setButtonsTextColor(int color) | Sets the text color of the previous and next buttons. | 
| setButtonsTextSize(float size) | Sets the text size of the previous and next buttons. |
| setTabsColor(int selectedColor, int unselectedColor) | Sets the text and indicator color for tabs. | 
| setFinishButtonClickListener(FinishButtonClickListener listener) | Sets a listener for the finish button click event.| 


## Usage:
1. Add SmoothStepper to the wanted layout:
```
<com.example.smoothstepper.StepperView
    android:id="@+id/stepperView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```
2. For each step, you will need to create a new fragment class that ```extends BaseStepperFragment```.
   For example:

```
package com.example.myapplication;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.smoothstepper.BaseStepperFragment;

public class StepFragment extends BaseStepperFragment {
EditText editText ;

    @Override
    protected boolean isDataValid() {
        return !editText.getText().toString().trim().isEmpty();
    }

    @Override
    protected void initializeUI(View view) {
        setTitle("Step One Title");
        setTitleColor(Color.GRAY);

        View customView = LayoutInflater.from(getContext()).inflate(R.layout.custom_content_layout, null);
        editText = customView.findViewById(R.id.userInputEditText);

        addCustomContent(customView);
    }

    @Override
    protected void setToastMessage() {
        this.toastMessage = "Please add your name";

    }

    @Override
    protected void loadData() {
        // Load additional data if needed
    }



}
```
Make sure to add a custom layout for each step. For example:
```custom_content_layout.xml```
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/userInputEditText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="@string/enter_your_text_here"
        android:layout_marginTop="16dp"
        android:padding="12dp"
        android:background="@android:drawable/edit_text"/>


</LinearLayout>
```
3. Setup StepperView in Your Activity:
  ```
import com.example.smoothstepper.StepperView;

// Initialize your StepperView
StepperView stepperView = findViewById(R.id.stepperView);

// Create and set up your fragments for each step
List<Fragment> fragments = new ArrayList<>();
fragments.add(new StepFragment());
// Add more fragments as needed

stepperView.setSteps(this, fragments)
    .setFinishButtonClickListener(() -> {
        // Handle finish button click
    });
```
4. Change the stepper as you wish - Add icon list, change tabs colors when selected and unselected
   Complete example:
```
package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.smoothstepper.StepperView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StepperView stepperView = findViewById(R.id.stepperView);
        ArrayList<Integer> icons = new ArrayList<>();
        icons.add(R.drawable.user_icon);
        icons.add(R.drawable.fruit_icon);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new StepFragment());
        fragments.add(new MoreStepFragment());
        stepperView.setButtonsBackgroundColor(Color.BLUE).
                setButtonsTextColor(Color.WHITE)
                .setTabsColor(Color.RED, Color.GRAY)
                .setSteps(this, fragments)
                .areTabsClickable(false).addTabsIcons(icons)
                .setFinishButtonClickListener(finishStepper());
    }

    private StepperView.FinishButtonClickListener finishStepper() {
        return () -> Log.e("MainActivity", "Finish button clicked");
    }
}
```

   









