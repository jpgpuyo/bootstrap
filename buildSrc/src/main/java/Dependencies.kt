import java.text.SimpleDateFormat
import java.util.*

fun getVersionNumber(): Int {
    val dateFormat = SimpleDateFormat("yyMMddHH")
    val versionNumber = dateFormat.format(Date())
    return versionNumber.toInt()
}

object Versions {
    const val kotlin = "1.6.21"
    val versionCode = getVersionNumber()
    const val versionName = "2.0.0"
    const val coroutines = "1.6.3"
    const val coroutinesAndroid = "1.6.3"
    const val serialization = "1.3.3"
    const val ktor = "2.0.2"
    const val koin = "3.1.6"
    const val sqldelight = "2.0.0-alpha02"
    const val coreKtx = "1.8.0"
    const val navigation = "2.5.3"
    const val klock = "2.4.1"
    const val multiplatformSettings = "0.8"

    //Jetpack Compose
    const val compose = "1.2.0-beta01"
    const val navCompose = "2.5.3"
    const val accompanist = "0.27.1"
    const val constraintLayout = "1.0.1"

    //Test
    const val junit = "4.12"
    const val androidXTestJUnit = "1.1.3"
    const val testCore = "1.3.0"
    const val mockito = "3.11.2"
    const val mockitoKotlin2 = "2.2.0"
    const val mockitoInline = "3.12.4"

    object Android {
        const val compileSdk = 33
        const val targetSdk = 33
        const val minSdk = 24
        val versionCode = Versions.versionCode
        const val versionName = Versions.versionName
    }

    object Common {
        const val targetSdk = Android.targetSdk
        const val minSdk = Android.minSdk
        val versionCode = Android.versionCode
        const val versionName = Android.versionName
    }
}

object Dependencies {
    object Root {
        const val android = "com.android.tools.build:gradle:7.0.2"
        const val cocoapods = "co.touchlab:kotlinnativecocoapods:0.6"
        const val sqldelight = "com.squareup.sqldelight:gradle-plugin:${Versions.sqldelight}"
        const val google = "com.google.gms:google-services:4.3.10"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-gradle:2.7.1"
        const val appdistribution = "com.google.firebase:firebase-appdistribution-gradle:2.0.0"
        const val serialization = "serialization"
    }

    object Android {
        const val core = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.0"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val fragment = "androidx.fragment:fragment-ktx:1.3.6"
        const val recycler = "androidx.recyclerview:recyclerview:1.2.0"
        const val material = "com.google.android.material:material:1.4.0"
        const val qrzxing = "com.google.zxing:core:3.4.0"

        const val maps = "com.google.android.libraries.maps:maps:3.1.0-beta"
        const val mapsKtx = "com.google.maps.android:maps-v3-ktx:3.2.0"
        const val playServicesMaps = "com.google.android.gms:play-services-maps:18.0.0"
        const val playServicesLocation = "com.google.android.gms:play-services-location:18.0.0"
        const val volley = "com.android.volley:volley:1.2.1"
    }

    object DI {
        const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
        const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
        const val koinExt = "io.insert-koin:koin-android-ext:${Versions.koin}"
        const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Firebase {
        const val firebaseBoM = "com.google.firebase:firebase-bom:29.0.0"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
        const val messaging = "com.google.firebase:firebase-messaging-ktx"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val foundationLayout =
            "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayout}"

        const val coilCompose = "io.coil-kt:coil-compose:2.2.2"
        const val accompanistNavigationAnimation =
            "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
        const val accompanistSwipeToRefresh =  "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}"
    }

    object Remote {
        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
        const val ktorClientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val ktorClientContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
        const val ktorClientSerializationJson = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
        const val ktorClientAuth = "io.ktor:ktor-client-auth:${Versions.ktor}"
        const val ktorLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        const val ktorClientMock = "io.ktor:ktor-client-mock:${Versions.ktor}"
        object Android {
            const val ktorClientCore = "io.ktor:ktor-client-android:${Versions.ktor}"
        }

        object Native {
            const val ktorClientCore = "io.ktor:ktor-client-ios:${Versions.ktor}"
        }
    }

    object Common {
        object Main {
            const val coroutines =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
            const val klock = "com.soywiz.korlibs.klock:klock:${Versions.klock}"
            const val multiplatformSettings =
                "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}"
        }

        object Android {
            const val core = "androidx.core:core-ktx:${Versions.coreKtx}"
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
            const val sqldelightDriver =
                "com.squareup.sqldelight:android-driver:${Versions.sqldelight}"
        }

        object iOS {
            const val ktorClientCore = "io.ktor:ktor-client-ios:${Versions.ktor}"
            const val sqldelightDriver =
                "com.squareup.sqldelight:native-driver:${Versions.sqldelight}"
        }
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val androidXTestJUnit = "androidx.test.ext:junit:${Versions.androidXTestJUnit}"
        const val mockitoKotlin2 = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin2}"
        const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        const val testCore = "androidx.test:core:${Versions.testCore}"

        const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
        const val kotlinTestJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"

        const val composeUiTest = "androidx.compose.ui:ui-test:${Versions.compose}"
        const val composeUiTestJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
        const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    }

}