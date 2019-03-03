package com.zachtib.myallowance

import org.threeten.bp.LocalDateTime


class Cache<T> {
    var value: Pair<T, LocalDateTime>? = null

    suspend fun getValue(getter: suspend () -> T): T {
        value?.let {
            if (LocalDateTime.now() > it.second) {
                expire()
            }
        }
        val tmp = value
        return if (tmp == null) {
            val newValue = getter()
            value = newValue to LocalDateTime.now().plusMinutes(10)
            newValue
        } else {
            tmp.first
        }
    }

    fun expire() {
        value = null
    }
}