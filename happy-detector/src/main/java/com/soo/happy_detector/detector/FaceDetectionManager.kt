package com.soo.happy_detector.detector

import android.graphics.Bitmap
import android.graphics.RectF
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.soo.happy_detector.model.FaceInfo
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

// ML Kit 구현
class FaceDetectionManager {

    private var faceDetector : FaceDetector? = null
    private var isInitialized = false

    // ML Kit 얼굴 인식 초기화
    suspend fun initialize() {
        if (isInitialized) return

        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST) // 빠른 속도
            //PERFORMANCE_MODE_ACCURATE // 정확도
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL) // 랜드마크 감지
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL) // 행복도 필욘
            .setMinFaceSize(0.10f) // 화면의 10% 이상인 얼굴만 감지
            .enableTracking() // 얼굴 추적 활성
            .build()

        faceDetector = FaceDetection.getClient(options)
        isInitialized = true
    }
    suspend fun detectFaces(bitmap: Bitmap) : List<FaceInfo> {

        // 이미지 준비
        val mlKitImage = InputImage.fromBitmap(bitmap, 0)

        // 얼굴 감지 (콜백 -> suspend 로 변환)
        val faces = detectFacesFromMLKit(mlKitImage)

        // 결과 반환
        return faces.map { face -> convertToFaceInfo(face) }
    }

    private suspend fun detectFacesFromMLKit(image: InputImage) : List<Face> =
        suspendCancellableCoroutine { continuation ->
            val detector = faceDetector ?: run {
                continuation.resumeWithException(
                    IllegalArgumentException("Face detector가 초기화되지 않았습니다")
                )
                return@suspendCancellableCoroutine
            }
            detector.process(image)
                .addOnSuccessListener { faces ->
                    continuation.resume(faces) // 성공 시, 결과 반환
                }
                .addOnFailureListener { error ->
                    continuation.resumeWithException(error)
                }
        }


    private suspend fun convertToFaceInfo(face: Face) :FaceInfo {
        //
        return FaceInfo(
            boundingBox = RectF(
                face.boundingBox.left.toFloat(),
                face.boundingBox.top.toFloat(),
                face.boundingBox.right.toFloat(),
                face.boundingBox.bottom.toFloat()

        ),
            happinessScore = face.smilingProbability ?: 0.0f,
            faceConfidence = face.trackingId?.toFloat() ?: 0.8f,
            )
    }


    // 초기화 상태 확인 (외부 접근용)
    fun isInitialized() : Boolean = isInitialized

    fun release() {
        // 리소스 해제
        faceDetector?.close()
        faceDetector = null
        isInitialized = false

    }
}