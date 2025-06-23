plugins {
//    alias(libs.plugins.android.application)
//    alias(libs.plugins.kotlin.android)
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

}

android {
    namespace = "com.sooj.happy_detector"
    compileSdk = 35

    defaultConfig {
//        applicationId = "com.sooj.happy_detector"
        minSdk = 27
        targetSdk = 35
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // ML Kit (감정 분석)
    implementation(libs.face.detection)

    // tensorflow lite
    implementation(libs.tensorflow.lite)
    implementation(libs.tensorflow.lite.support)

    // exoplayer (프레임 추출)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.common)

    // coroutines
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)



}