package com.widget.listcomposecard.core

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class CircularBuffer<T>(capacity: Int) {
    private val buffer: MutableList<T?>
    private var readIndex = 0
    private var writeIndex = 0
    private var size = 0
    private var fullBuffer = false
    private val lock: Lock = ReentrantLock()

    init {
        buffer = ArrayList(capacity)
        for (i in 0 until capacity) {
            buffer.add(null)
        }
    }

    fun writeAsync(value: T) {
        lock.lock()
        try {
            write(value)
        } finally {
            lock.unlock()
        }
    }

    fun buffer(): List<T?> {
        return buffer
    }

    fun writeRow(list: List<T>) {
        lock.lock()
        try {
            for (element in list) {
                write(element)
            }
        } finally {
            lock.unlock()
        }
    }

    fun write(value: T) {
        buffer[writeIndex] = value
        writeIndex = (writeIndex + 1) % buffer.size
        size++
        if (writeIndex == readIndex) {
            fullBuffer = true
            readIndex = (readIndex + 1) % buffer.size
            size = buffer.size - 1
        }
    }

    fun readRow(orderedSize: Int): List<T?> {
        lock.lock()
        return try {
            val result: MutableList<T?> = ArrayList()
            val cycles = orderedSize.coerceAtMost(size())
            for (i in 0 until cycles) {
                val value = read()
                result.add(value)
            }
            result
        } finally {
            lock.unlock()
        }
    }

    fun readAsync(): T? {
        lock.lock()
        return try {
            read()
        } finally {
            lock.unlock()
        }
    }

    private fun read(): T? {
        if (!fullBuffer && readIndex == writeIndex) {
            return null
        }
        val value = buffer[readIndex]
        size--
        readIndex = (readIndex + 1) % buffer.size
        fullBuffer = false
        return value
    }

    val isEmpty: Boolean
        get() = !fullBuffer && readIndex == writeIndex

    fun capacity(): Int {
        return buffer.size
    }

    operator fun get(index: Int): T? {
        return buffer[(readIndex + index) % buffer.size]
    }

    fun getDirect(index: Int): T? {
        return buffer[index]
    }

    public fun getData(): List<T?> {
        val orderedSize = size()
        val result: MutableList<T?> = java.util.ArrayList()
        val cycles = Math.min(orderedSize, size())
        for (i in 0 until cycles) {
            val value = read()
            result.add(value)
        }
        return result
    }

    fun trace(): String {
        val result = StringBuilder()
        for (i in 0 until capacity()) {
            val value = buffer[i]
            val strValue = value?.toString() ?: "-"
            if (i == readIndex && i == writeIndex) {
                result.append("[").append(strValue).append("]")
            } else if (i == readIndex) {
                result.append("(").append(strValue).append(")")
            } else if (i == writeIndex) {
                result.append("[").append(strValue).append("]")
            } else {
                result.append("{").append(strValue).append("}")
            }
        }
        return result.toString()
    }

    val isFull: Boolean
        get() = fullBuffer

    fun setFull(full: Boolean) {
        this.fullBuffer = full
    }

    fun writeIndex(): Int {
        return writeIndex
    }

    fun readIndex(): Int {
        return readIndex
    }

    fun size(): Int {
        return size
    }

    fun setWriteIndex(writeIndex: Int) {
        this.writeIndex = writeIndex
    }

    fun setReadIndex(readIndex: Int) {
        this.readIndex = readIndex
    }

    fun setSize(size: Int) {
        this.size = size
    }
}
