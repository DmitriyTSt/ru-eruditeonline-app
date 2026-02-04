package ru.dmitriyt.android.plugins.util

import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

@JvmInline
value class Command(
    val command: String,
)

fun Command.run(workingDir: File): String? {
    try {
        val parts = this.command.split("\\s".toRegex())
        val proc = ProcessBuilder(*parts.toTypedArray())
            .directory(workingDir)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        proc.waitFor(60, TimeUnit.SECONDS)
        return proc.inputStream.bufferedReader().readText().trim()
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }
}