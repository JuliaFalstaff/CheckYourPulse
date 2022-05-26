object Config {
    const val applicationId = "com.example.checkyourpulse"
    const val minSdkVersion = 23
    const val targetSdkVersion = 31
    const val compileSdkVersion = 31
    const val buildToolsVersion = "30.0.3"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val KOIN = "3.1.6"
    const val LIFECYCLE = "2.4.1"
    const val LIFECYCLE_EXTENSIONS = "2.2.0"
    const val COROUTINES_ANDROID = "1.5.2"
    const val COROUTINES_CORE = "1.5.2"
    const val COROUTINES_ADAPTER = "0.9.2"
    const val COROUTINES_PLAY_SERVICES = "1.2.1"
}

object Dependencies {
    //Koin
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${Versions.KOIN}"
    const val KOIN_CORE = "io.insert-koin:koin-core:${Versions.KOIN}"

    //Lifecycle
    const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE_EXTENSIONS}"

    //Coroutines
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_ANDROID}"
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_CORE}"
    const val COROUTINES_ADAPTER = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.COROUTINES_ADAPTER}"
    const val COROUTINES_PLAY_SERVICES = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.COROUTINES_PLAY_SERVICES}"
}