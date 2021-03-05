package com.adclock.util

import com.sun.javaws.Launcher
import java.io.File
import java.net.URL
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

// Modified from https://stackoverflow.com/questions/42401781/how-to-find-all-classes-in-a-package-using-reflection-in-kotlin
fun findSubclasses(packagename: String, superClass: KClass<*>): List<KClass<*>> {
    // Translate the package name into an absolute path
    var name = packagename
    if (!name.startsWith("/")) {
        name = "/$name"
    }
    name = name.replace('.', '/')

    // Get a File object for the package
    val url: URL =
        Launcher::class.java.getResource(name) ?: throw IllegalArgumentException("Package $packagename not found.")
    val directory = File(url.file)

    val foundClasses = mutableListOf<KClass<*>>()
    if (directory.exists()) {
        // Get the list of the files contained in the package
        directory.walk()
            .filter { f -> f.isFile && '$' !in f.name && f.name.endsWith(".class") }
            .forEach {
                val fullyQualifiedClassName = packagename +
                        it.canonicalPath.removePrefix(directory.canonicalPath)
                            .dropLast(6) // remove .class
                            .replace('\\', '.')
                            .replace('/', '.')
                try {
                    // Try to create an instance of the object
                    val kotlinClass = Class.forName(fullyQualifiedClassName).kotlin
                    if (kotlinClass.isSubclassOf(superClass))
                        foundClasses += kotlinClass
                } catch (cnfex: ClassNotFoundException) {
                    System.err.println(cnfex)
                } catch (iex: InstantiationException) {
                    // We try to instantiate an interface
                    // or an object that does not have a
                    // default constructor
                } catch (iaex: IllegalAccessException) {
                    // The class is not public
                }
            }
    }
    return foundClasses
}