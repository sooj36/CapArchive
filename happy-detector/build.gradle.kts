plugins {
//    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-parcelize")

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

        // NDK 설정 - YOLOv8 성능 최적화
        ndk {
            abiFilters += listOf("arm64-v8a", "armeabi-v7a")
        }

        // 모델 파일 압축 방지
        androidResources {
            noCompress += listOf("tflite", "lite", "bin")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false // 후에 릴리스 때, true 로 변경 예정
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // GPU Delegate 최적화
            buildConfigField("boolean", "USE_GPU_DELEGATE", "true")
        }
        debug {
            isMinifyEnabled = false
            buildConfigField("boolean", "USE_GPU_DELEGATE", "false")  // 디버그에서는 CPU 사용
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }

    // 패키징 옵션
    packaging {
        resources {
            excludes += listOf(
                "/META-INF/{AL2.0,LGPL2.1}",
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/license.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/notice.txt",
                "META-INF/ASL2.0"
            )
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Jetpack Compose (YOLOv8 UI)
    implementation(platform(libs.androidx.compose.bom.v20240200))
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.androidx.runtime.livedata)

    // ML Kit (감정 분석)
    implementation(libs.face.detection)

    // tensorflow lite
    implementation(libs.tensorflow.lite)
    implementation(libs.tensorflow.lite.support)

    // CameraX (실시간 프레임 캡처)
    implementation(libs.androidx.camera.camera2.v131)
    implementation(libs.androidx.camera.lifecycle.v131)
    implementation(libs.androidx.camera.view.v131)
    implementation(libs.androidx.camera.extensions)

    // TensorFlow Lite (YOLOv8 추론)
    implementation(libs.tensorflow.lite)
    implementation(libs.tensorflow.lite.gpu)  // GPU 가속
    implementation(libs.tensorflow.lite.support)
    implementation(libs.tensorflow.lite.metadata)

    // exoplayer (프레임 추출)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.common)

    // coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // 이미지 처리 (Bitmap 변환 최적화)
    implementation(libs.androidx.palette.ktx)

    // 성능 모니터링
    implementation(libs.androidx.tracing)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)


}