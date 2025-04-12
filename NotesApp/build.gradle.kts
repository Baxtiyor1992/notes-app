// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.9.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.20")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.9.0")
    }
}


plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    id("com.google.devtools.ksp") version "2.1.20-1.0.32" apply false
}