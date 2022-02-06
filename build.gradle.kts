buildscript {
    val composeVersion by extra("1.2.0-alpha02")
    val composeNavigationVersion by extra("2.5.0-alpha01")
    val coroutinesVersion by extra("1.6.0")
    val kotlinVersion by extra("1.6.10")
    val serializeVersion by extra("1.3.2")
    val navVersion by extra("2.3.5")
    val orbitVersion by extra("4.3.1")
    val hiltVersion by extra("2.38.1")
    val timberVersion by extra("5.0.1")
    val roomVersion by extra("2.4.1")

    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    }
}

tasks.register("type", Delete::class) {
    delete(rootProject.buildDir)
}