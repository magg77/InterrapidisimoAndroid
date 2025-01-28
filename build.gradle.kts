
buildscript {
    val kotlinVersionRoot by extra("1.9.0")

    val navVersionRoot by extra("2.8.5")
    val gsonVersion by extra("2.10")
    val loggingInterceptor by extra("4.11.0")
    val coroutinesAndroid by extra("1.7.0")

    val recyclerView by extra("1.3.1")
    val recyclerviewSelection by extra("1.1.0")

    val retrofit by extra("2.9.0")
    val hiltVersion by extra("2.51.1")

    val apiUrlBaseDev by extra("https://www.api.com./")
    val apiUrlBaseProd by extra("https://www.api.com./")

    repositories {
        google()
        mavenCentral()
    }

    dependencies {

        classpath(libs.gradle.v880)               //AGP version

        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra["kotlinVersionRoot"]}")

        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${rootProject.extra["navVersionRoot"]}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${rootProject.extra["hiltVersion"]}")


    }
}

plugins {

    id("com.android.application") version "8.8.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false

    id("com.google.devtools.ksp") version "1.9.0-1.0.12" apply false
    id("com.google.dagger.hilt.android") version("2.44") apply false
}

allprojects {
    repositories {
        //google()
        //mavenCentral()
    }
}