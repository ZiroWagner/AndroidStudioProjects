buildscript{
    dependencies{
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.52") //cambiado 2.52 old 2.47 old 2.45
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.android.library") version "8.1.4" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
