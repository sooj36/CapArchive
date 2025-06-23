package com.soo.happy_detector.model

import android.graphics.RectF

// 감지된 얼굴 정보 담는 데이터 클래스

data class FaceInfo(

    // 얼굴 영역 경계 박스
    val boundingBox : RectF,

    // 얼굴 행복 점수 (0.0 - 1.0)
    val happinessScore : Float,

    // 얼굴 인식 신뢰도 (0.0 - 1.0)
    val faceConfidence : Float,
)
