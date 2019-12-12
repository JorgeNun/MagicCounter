package com.example.magiccounter

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StorageTest {

    private lateinit var context: Context
    private lateinit var storage: Storage

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().context
        storage = Storage(context)
        storage.clear()
    }

    @After
    fun tearDown() {
        storage.clear()
    }

    @Test
    fun save() {
        storage.save(13)
        val result = storage.get()
        assertEquals(13, result)
    }

    @Test
    fun saveTwoValuesReturnsLast() {
        storage.save(12)
        storage.save(13)
        val result = storage.get()
        assertEquals(13, result)
    }

    @Test
    fun get() {
        val result = storage.get()
        assertEquals(null, result)
    }

    @Test
    fun clear() {
        storage.save(13)
        storage.clear()
        val result = storage.get()
        assertEquals(null, result)
    }
}