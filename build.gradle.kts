buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath(Dependencies.Root.android)
        classpath(Dependencies.Root.google)
        classpath(Dependencies.Root.crashlytics)
        classpath(kotlin(Dependencies.Root.serialization, version = Versions.kotlin))
    }
}
group = "com.mcc"
version = "1.0"

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}