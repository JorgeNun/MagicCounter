package com.example.magiccounter

class MagicCounter(private val randomNumberContract: RandomNumberContract, private val storage: StorageContract) {

    fun increment(): Int {
        val value = storage.get() ?: 0
        val randomIncrement = randomNumberContract.getRandomNumber()
        return if (randomIncrement > 100) {
            storage.save(randomIncrement)
            randomIncrement
        } else {
            storage.save(value + 1)
            value + 1
        }
    }

    fun decrement(): Int {
        val value = (storage.get() ?: 0)
        storage.save(value - 1)
        return value - 1
    }
}

interface RandomNumberContract {
    fun getRandomNumber(): Int
}