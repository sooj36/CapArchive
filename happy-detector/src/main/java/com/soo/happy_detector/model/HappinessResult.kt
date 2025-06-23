package com.soo.happy_detector.model

// 행복 감정 분석 결과 담은 클래스

data class HappinessResult(
    // 행복 표정 감지 여부
    val isHappy : Boolean,

    // 행복 감정 신뢰도 (0.0 - 1.0)
    val confidence : Float,

    // 감지 얼굴 정보 리스트
    val face : List<FaceInfo>,

    // 분석 시간 (밀리초 기준(
    val processingTimeMs : Long = 0L,
)