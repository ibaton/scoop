pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
rootProject.name = "scoop"
include(":app")
include(":newsapi")
include(":newsrepository")
include(":newsstorage")
