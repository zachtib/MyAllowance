package com.zachtib.android.preferences.validation

import com.zachtib.android.preferences.DelegatedPreference


fun <T> DelegatedPreference<T>.validate(validator: (T) -> Boolean) =
    ValidatedPreference(this, validator)