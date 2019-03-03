package com.zachtib.android.preferences

import kotlin.properties.ReadWriteProperty

interface DelegatedPreference<T> : ReadWriteProperty<Any?, T>