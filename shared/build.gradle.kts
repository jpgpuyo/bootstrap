plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
}

android {
    namespace = "com.jpuyo.bootstrap.shared"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {}
    }
    flavorDimensions.add("default")
    productFlavors {
        create("mock") {
            dimension = "default"
        }
        create("dev") {
            dimension = "default"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.6.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")

    with(Dependencies.DI) {
        implementation(koinCore)
    }

    with(Dependencies.Common.Main) {
        implementation(coroutines)
        implementation(klock)
        implementation(multiplatformSettings)
    }

    with(Dependencies.Remote) {
        implementation(serialization)
        implementation(ktorClientCore)
        implementation(ktorClientContentNegotiation)
        implementation(ktorClientSerializationJson)
        implementation(ktorClientAuth)
        implementation(ktorLogging)
        implementation(ktorClientMock)
        implementation(Dependencies.Remote.Android.ktorClientCore)
    }

    with(Dependencies.Firebase) {
        implementation(platform(firebaseBoM))
        implementation(analytics)
        implementation(crashlytics)
        implementation(messaging)
    }
}