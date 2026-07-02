package com.example.karoo.shortcut.extension

import io.hammerhead.karooext.extension.KarooExtension

class ShortcutExtension : KarooExtension("shortcut-extension", "1.0") {
    override val types by lazy {
        listOf(
            ShortcutDataType(this)
        )
    }
}
