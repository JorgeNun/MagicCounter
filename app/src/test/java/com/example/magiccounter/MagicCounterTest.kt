package com.example.magiccounter

import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class MagicCounterTest {

    private lateinit var storage: StorageContract

    @Before
    fun setUp() {
        storage = givenStorage()
        storage.clear()
    }

    @After
    fun tearDown() {
        storage.clear()
    }

    @Test
    fun `when random number is bigger than 100 we storage this value`() {
        MagicCounter(givenRandomNumber(101), storage).increment()
        val storedValue = storage.get()

        assertEquals(101, storedValue)
    }

    @Test
    fun `when random number is lower than 100 we add one to the stored value`() {
        storage.save(13)
        MagicCounter(givenRandomNumber(99), storage).increment()
        val storedValue = storage.get()

        assertEquals(14, storedValue)
    }

    @Test
    fun `when decrementing we save the stored value less one`() {

        storage.save(13)
        MagicCounter(givenRandomNumber(), storage).decrement()
        val storedValue = storage.get()

        assertEquals(12, storedValue)
    }
}

fun givenStorage() = object : StorageContract {

    private var storedValue: Int? = null

    override fun save(value: Int) {
        storedValue = value
    }

    override fun clear() {
        storedValue = null
    }

    override fun get(): Int? = storedValue
}

fun givenRandomNumber(randomNumber: Int = 0) = object : RandomNumberContract {
    override fun getRandomNumber(): Int = randomNumber
}