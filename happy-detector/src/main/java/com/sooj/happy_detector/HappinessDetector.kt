package com.sooj.happy_detector

import android.graphics.Bitmap
import com.sooj.happy_detector.model.HappinessResult

// 행복 감정 탐지 위한 메인 인터페이스

interface HappinessDetector {
    suspend fun  initialize() // 초기화
    suspend fun analyzeFrame(bitmap: Bitmap) : HappinessResult // 비트맵 이미지에서 감정 분석

    fun isInitialized() : Boolean // detector 초기화 여부

    fun release() // 디텍터 리소스 해제
}