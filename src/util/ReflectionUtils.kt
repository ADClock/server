package com.adclock.util

import java.io.File
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

// Modified from https://stackoverflow.com/questions/42401781/how-to-find-all-classes-in-a-package-using-reflection-in-kotlin
fun findSubclasses(packagename: String, superClass: KClass<*>): List<KClass<*>> {
    // Translate the package name into an absolute path
    val absolute = ".$packagename".replace(Regex("\\.+"), "/")

    // Get a File object for the package
    val url = superClass.java.getResource(absolute) ?: throw IllegalArgumentException("Package $packagename not found.")
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
                    val possibleClass = Class.forName(fullyQualifiedClassName).kotlin
                    if (possibleClass.isSubclassOf(superClass))
                        foundClasses += possibleClass
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