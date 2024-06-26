// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
    }
}

plugins {
    id("com.android.application") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false

    id("com.google.dagger.hilt.android") version "2.48" apply false

    id ("com.android.library") version "7.4.2" apply false
//    id ("androidx.room") version "2.6.1" apply false
}