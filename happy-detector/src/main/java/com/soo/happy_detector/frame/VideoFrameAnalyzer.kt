package com.soo.happy_detector.frame

import androidx.media3.exoplayer.ExoPlayer
import com.soo.happy_detector.HappinessDetector
import com.soo.happy_detector.model.HappinessResult

// exoplayer 연동하여 비디오 프레임 분석

class VideoFrameAnalyzer(
    private val happinessDetector: HappinessDetector
) {
    fun connectToPlayer(exoPlayer: ExoPlayer) {
        // exoplayer 연결
    }

    suspend fun startAnalysis() : HappinessResult? {
        // 프레임 추출 및 분석 로직 구현
        return null
    }

    fun stopAnalysis() {
        // 분석 중단
    }

    fun release() {
        // 리소스 해제
    }
}