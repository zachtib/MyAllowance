package com.zachtib.myallowance

import android.content.SharedPreferences
import com.zachtib.android.preferences.TypedPreferences

class AllowancePreferences(prefs: SharedPreferences) : TypedPreferences(prefs) {
    var isConfigured by preference(false)
}