# Karoo Shortcut Extension Template

A universal, ready-to-build Android template for creating custom in-ride screen shortcut buttons for the **Hammerhead Karoo / Karoo 2** cycling computers.

This template contains an offline copy of the Karoo Extensions SDK (`lib-release.aar`), meaning it can be compiled instantly without requiring any GitHub Package Registry credentials.

---

## How to Customize

### 1. Change the Shortcut Command (Target Action / App)
Open the file [ShortcutDataType.kt](file:///app/src/main/kotlin/com/example/karoo/shortcut/extension/ShortcutDataType.kt) and locate the `startView` method.

Customize the `intent` definition:

*   **Option A: Launch a system settings screen (e.g., Bluetooth Settings)**
    ```kotlin
    val intent = Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    ```
*   **Option B: Launch an offline app by package name (e.g., Musicolet)**
    ```kotlin
    val intent = context.packageManager.getLaunchIntentForPackage("in.krosbits.musicolet")?.apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    ```
*   **Option C: Launch a custom web link in the browser**
    ```kotlin
    val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://example.com")).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    ```

---

### 2. Change the App & Widget Icon

#### Change the Launcher Icon
Replace the vector drawable file at `app/src/main/res/drawable/ic_shortcut.xml` with your own SVG path, or modify the existing path.

#### Change the In-Ride Data Field Layout (Label & Color)
Open the layout file [shortcut_field.xml](file:///app/src/main/res/layout/shortcut_field.xml) and customize:
*   `android:text="Launch App"` to change the text label.
*   `android:background="#1A1A1A"` to change the cell background color.
*   `android:src="@drawable/ic_shortcut"` to change the icon inside the cell.

---

### 3. Change Project Package Name & ID
To install multiple shortcut extensions side-by-side, each extension must have a unique package name and ID.

1.  **Update `app/build.gradle.kts`:**
    Change `namespace` and `applicationId` to a unique name (e.g., `com.example.karoo.mycustomshortcut`).
2.  **Update `extension_info.xml`:**
    Change `id="shortcut-extension"` to a unique ID (e.g., `my-custom-shortcut`).
3.  **Update `strings.xml`:**
    Modify the text labels so they represent your new shortcut on the Karoo UI.

---

## Build & Install

Run the following command in the root folder of the project to compile the app and install it directly to your USB-connected Karoo device:

```powershell
# Using the local Gradle wrapper (highly recommended to prevent version mismatches)
.\gradlew assembleDebug; adb install -r app/build/outputs/apk/debug/app-debug.apk
```

Once installed, go to **Settings -> Profiles**, select/edit a profile, and add your new shortcut data field to an in-ride screen!
