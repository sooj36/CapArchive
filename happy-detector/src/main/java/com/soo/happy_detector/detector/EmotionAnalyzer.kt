package com.soo.happy_detector.detector

import com.soo.happy_detector.model.FaceInfo

// TensorFlow lite 활용해 감정 분석

class EmotionAnalyzer {
    suspend fun initialize() {
        // tensorflow lite 모델 초기화
    }

    suspend fun analyzeEmotion(faceInfo: FaceInfo) : Float {
        // 감정 분석 로직 구현 예정
        return 0.0f
    }
    
    fun release() {
        // 리소스 해제
    }
}