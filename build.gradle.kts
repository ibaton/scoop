buildscript {
    val composeVersion by extra("1.0.5")
    val kotlinVersion by extra("1.5.31")
    val serializeVersion by extra("1.3.2")
    val navVersion by extra("2.3.5")
    val orbitVersion by extra("4.3.1")
    val hiltVersion by extra("2.38.1")

    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
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