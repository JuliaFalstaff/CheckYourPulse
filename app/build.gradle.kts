plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
    id ("com.google.gms.google-services")
}

android {
    compileSdk = Config.compileSdkVersion
    buildToolsVersion = Config.buildToolsVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Releases.versionCode
        versionName = Releases.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility (JavaVersion.VERSION_1_8)
        targetCompatibility (JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.4.32")
    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.4.1")
    implementation ("com.google.android.material:material:1.6.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.google.firebase:firebase-firestore-ktx:24.1.2")
    testImplementation ("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("droidx.test.espresso:espresso-core:3.4.0")

    //Koin
    implementation (Dependencies.KOIN_ANDROID)
    implementation (Dependencies.KOIN_CORE)

    //ViewModel and LiveData
    implementation (Dependencies.LIFECYCLE)
    implementation (Dependencies.LIFECYCLE_VIEWMODEL)
    implementation (Dependencies.LIFECYCLE_LIVEDATA)
    implementation (Dependencies.LIFECYCLE_EXTENSIONS)

    //Coroutines
    implementation (Dependencies.COROUTINES_ANDROID)
    implementation (Dependencies.COROUTINES_CORE)
    implementation (Dependencies.COROUTINES_ADAPTER)
    implementation (Dependencies.COROUTINES_PLAY_SERVICES)
}