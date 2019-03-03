package com.zachtib.android.preferences

import com.zachtib.android.preferences.validation.PreferencesValidationException


fun <T : TypedPreferences> T.edit(block: T.() -> Unit) {
    try {
        beginEditing()
        block()
        commit()
    } catch (e: PreferencesValidationException) {
        rollback()
    }
}