package com.example.stopwatch.model.stopwatchImpl

interface TimestampProvider {
    fun getMilliseconds(): Long
}