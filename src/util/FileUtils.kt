package com.adclock.util

import java.io.File

fun File.createNewFileAndFolder() {
    if (!parentFile.exists())
        parentFile.mkdirs()
    createNewFile()
}

fun File.createOrEmptyFile() {
    if (exists())
        writeText("")
    else
        createNewFileAndFolder()
}

fun File.readLinesIndexed(action: (Int, String) -> Unit) = useLines { it.forEachIndexed(action) }