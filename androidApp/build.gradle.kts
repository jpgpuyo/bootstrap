plugins {
    id("com.android.application")
    kotlin("android")
    //Uncomment this line to enable firebase
    id("com.google.gms.google-services")

    //Uncomment this line to enable crashlytics
    id("com.google.firebase.crashlytics")
}

dependencies {
    implementation(project(":shared"))
    implementation(Dependencies.Common.Main.multiplatformSettings)

    with(Dependencies.Android) {
        implementation(core)
        implementation(appCompat)
        implementation(constraintLayout)
        implementation(navigationFragment)
        implementation(navigationUi)
        implementation(fragment)
        implementation(recycler)
        implementation(material)
    }

    with(Dependencies.DI) {
        implementation(koinCore)
        implementation(koinAndroid)
        implementation(koinCompose)
    }

    with(Dependencies.Firebase) {
        implementation(platform(firebaseBoM))
        implementation(analytics)
        implementation(crashlytics)
        implementation(messaging)
    }

    with(Dependencies.Compose) {
        implementation(ui)
        implementation(uiGraphics)
        implementation(foundationLayout)
        implementation(material)
        implementation(navigation)
        implementation(coilCompose)
        implementation(accompanistNavigationAnimation)
        implementation(accompanistSwipeToRefresh)
        implementation(uiTooling)
        implementation(constraintLayout)
    }

    with(Dependencies.Test) {
        testImplementation(junit)
        androidTestImplementation(androidXTestJUnit)
        testImplementation(testCore)
        testImplementation(mockitoKotlin2)
        testImplementation(mockitoInline)
        testImplementation(coroutinesTest)
        testImplementation(kotlin("test"))

        // Compose testing dependencies
        androidTestImplementation(composeUiTest)
        androidTestImplementation(composeUiTestJUnit)
        debugImplementation(composeUiTestManifest)
    }
}

android {
    compileSdkVersion(Versions.Android.compileSdk)
    defaultConfig {
        applicationId = "com.jpuyo.bootstrap"
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    flavorDimensions("default")
    productFlavors {
        create("mock") {
            dimension = "default"
            applicationId = "com.jpuyo.bootstrap"
            applicationIdSuffix = ".mock"
            resValue("string", "app_name", "Bootstrap Mock")
        }
        create("dev") {
            dimension = "default"
            applicationId = "com.jpuyo.bootstrap"
            applicationIdSuffix = ".dev"
            resValue("string", "app_name", "Bootstrap Dev")
        }
    }

    packagingOptions {
        exclude("META-INF/licenses/**")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}