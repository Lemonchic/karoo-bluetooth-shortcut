package com.example.karoo.shortcut.extension

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.karoo.shortcut.R
import io.hammerhead.karooext.extension.DataTypeImpl
import io.hammerhead.karooext.internal.Emitter
import io.hammerhead.karooext.internal.ViewEmitter
import io.hammerhead.karooext.models.StreamState
import io.hammerhead.karooext.models.ViewConfig

class ShortcutDataType(private val context: Context) : DataTypeImpl("shortcut-extension", "shortcut-field") {

    override fun startStream(emitter: Emitter<StreamState>) {
        // Since we are only using this field as an interactive button, 
        // we set the stream status as NotAvailable.
        emitter.onNext(StreamState.NotAvailable)
    }

    override fun startView(context: Context, config: ViewConfig, emitter: ViewEmitter) {
        val remoteViews = RemoteViews(context.packageName, R.layout.shortcut_field)

        // =========================================================================
        // HOW TO CONFIGURE YOUR SHORTCUT COMMAND:
        // =========================================================================
        // Option A: Launch a specific Android settings screen (e.g., Bluetooth Settings)
        // val intent = Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS)
        //
        // Option B: Launch an app by its package name (e.g., Musicolet)
        // val intent = context.packageManager.getLaunchIntentForPackage("in.krosbits.musicolet")
        //
        // Option C: Launch any specific intent action or custom URI
        // val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://example.com"))
        // =========================================================================

        // By default, this template launches the Bluetooth settings panel.
        // Change the intent target below to customize the shortcut behavior.
        val intent = Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        // Support both older and newer Android versions for PendingIntent flags
        val flags = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            flags
        )

        // Bind the pending intent to click events on the button layout container
        remoteViews.setOnClickPendingIntent(R.id.shortcut_button, pendingIntent)

        // Push the update to the Karoo in-ride screen view emitter
        emitter.updateView(remoteViews)
    }
}
