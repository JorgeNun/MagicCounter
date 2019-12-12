package com.example.magiccounter

import android.content.Context

class Storage(private val context: Context) : StorageContract {

    override fun save(value: Int) {
        val sharedPrefs = context.getSharedPreferences("Storage", Context.MODE_PRIVATE).edit()
        sharedPrefs.putInt("value", value)
        sharedPrefs.apply()
    }

    override fun get(): Int? {
        val sharedPrefs = context.getSharedPreferences("Storage", Context.MODE_PRIVATE)
        return if (sharedPrefs.contains("value")) sharedPrefs.getInt("value", 0) else null
    }

    override fun clear() {
        val sharedPrefs = context.getSharedPreferences("Storage", Context.MODE_PRIVATE)
        sharedPrefs.edit().remove("value").apply()
    }
}

interface StorageContract {
    fun save(value: Int)

    fun get(): Int?

    fun clear()
}
