package com.sooj.happy_detector.detector

import android.graphics.Bitmap
import android.graphics.RectF
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.sooj.happy_detector.model.FaceInfo
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

// 모듈 간 겹합도 낮춰 메인 앱에서 쉽게 올 진입점 제공
class FaceDetectionManager {

    // 메인 앱에서 탐지 결과를 받을 수 있는 콜백 인터페이스
    interface DetectionCallback {
        fun
    }


}