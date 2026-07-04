# Karoo Bluetooth Settings Shortcut

🚴‍♂️ **Access your Bluetooth settings directly from your Hammerhead Karoo 2 in-ride screens!**

Tapping the custom **`BT Settings`** data field instantly opens the Bluetooth sensor pairing screen, allowing you to pair new sensors, reconnect devices, or configure settings without having to stop your ride or navigate the settings menu.

---

## 🚀 Features

*   **1-Tap Bluetooth Access:** Quick-launch the native Karoo Bluetooth Settings screen with a single tap on your data grid.
*   **Optimal Integration:** Appears as a standard, native-looking in-ride data field.
*   **Pre-Compiled & Ready:** Download the pre-built APK and install it in seconds.

---

## 🔧 Installation & Setup

1.  **Download the Release APK:**
    *   Go to the **Releases** page of this repository and download `app-debug.apk`.
2.  **Install on your Karoo:**
    *   Connect your Karoo to your computer via USB and enable USB Debugging.
    *   Install the APK using ADB:
        ```bash
        adb install -r app-debug.apk
        ```
3.  **Add the Field to your Ride Screen:**
    *   Go to **Settings -> Profiles**, select or edit a profile, and add the **`BT Settings`** data field to your desired screen.

---

## 🛠 How to Use as a Shortcut Template

This project is built on a universal shortcut template structure. You can customize it to launch **any** Android settings screen, native app, or custom URL.

### 1. Change the Action/App
Open [ShortcutDataType.kt](file:///app/src/main/kotlin/com/example/karoo/shortcut/extension/ShortcutDataType.kt) and update the `intent` inside `startView`:

*   **Launch an app (e.g. Musicolet):**
    ```kotlin
    val intent = context.packageManager.getLaunchIntentForPackage("in.krosbits.musicolet")?.apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    ```
*   **Launch a web link:**
    ```kotlin
    val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://example.com")).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    ```

### 2. Customize Labels & Layout
*   Update strings in [strings.xml](file:///app/src/main/res/values/strings.xml) to change UI names.
*   Modify [shortcut_field.xml](file:///app/src/main/res/layout/shortcut_field.xml) to customize cell text and colors.
*   Replace `app/src/main/res/drawable/ic_shortcut.xml` to change the icon.

### 3. Install Side-by-Side
To run multiple custom shortcuts at once:
1.  Change `namespace` and `applicationId` in `app/build.gradle.kts` to a unique package name.
2.  Change `id="shortcut-extension"` in `extension_info.xml` to a unique ID.
