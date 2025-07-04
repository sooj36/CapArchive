# SmileCapture PRD

## 1. 프로젝트 개요

**한 줄 요약:** 웃는 순간을 자동으로 감지해서 셀카를 촬영해주는 스마일 감지 카메라 앱

**핵심 가치:**  
앱이 알아서 사용자의 가장 행복하고 자연스러운 모습을 포착해줍니다. 진짜 즐거워서 웃는 그 순간 그대로를 담을 수 있어요

## 2. 기술 스택 & 아키텍처

### 멀티모듈 구조 (2모듈)
```
📦 SmileCapture
├── [Multi_1] app (Main Application) 
│   ├── 📁 presentation/
│   │   ├── ui/
│   │   │   └── Jetpack Compose
│   │   ├── viewmodel/
│   │   │   └── MVVM
│   │   └── navigation/
│   │       └── Navigation Components
│   ├── 📁 domain/
│   │   ├── usecase/
│   │   │   └── CapturePhotoUseCase
│   │   ├── repository/
│   │   │   └── CameraRepository Interface
│   │   └── model/
│   │       └── Domain Models
│   ├── 📁 data/
│   │   ├── repository/
│   │   │   └── CameraRepositoryImpl
│   │   ├── datasource/
│   │   │   └── Local Storage
│   │   └── mapper/
│   │       └── Data Mappers
│   └── 📁 di/
│       └── Hilt Configuration
│
└── [Multi_2] happy-detector (ML Module)
    ├── 📁 domain/
    │   ├── usecase/
    │   │   └── HappyDetectorUseCase
    │   ├── repository/
    │   │   └── HappyDetectorRepository Interface
    │   └── model/
    │       ├── SmileResult
    │       ├── EmotionResult
    │       └── PersonBox (MVP 이후 UI 가이드라인 기능에서 추가 예정)
    ├── 📁 data/
    │   ├── repository/
    │   │   └── HappyDetectorRepositoryImpl
    │   ├── datasource/
    │   │   ├── PersonDetector (YOLOv8n TFLite)
    │   │   └── EmotionAnalyzer (FER TFLite)
    │   └── pipeline/
    │       └── AnalysisPipeline (ML 모델들을 조합하는 실행 파이프라인)
    └── 📁 di/
        └── HappyDetectorModule (의존성 주입 설정)
    
```

### 핵심 기술
- **UI**: Jetpack Compose + Material 3
- **Architecture**: Clean Architecture + MVVM
- **DI**: Dagger Hilt
- **Camera**: CameraX
- **ML**: YOLOv8n (객체탐지) + FER (감정분석) → TFLite 변환
- **State Management**: StateFlow + Compose State

## 3. 유저 플로우

### 메인 화면
1. **스마일 촬영 버튼**: 중앙 대형 버튼
2. **갤러리 버튼**: 촬영한 사진들 확인
3. **설정 버튼**: 감지 민감도 조절

### 카메라 화면 (핵심 플로우)
1. **카메라 실행**: 전면 카메라 자동 실행
2. **얼굴 감지**: 실시간 얼굴 인식 (ML Kit)
3. **웃음 감지**: 웃음 정도 실시간 분석 (TFLite)
4. **자동 촬영**: 웃음 임계값 초과 시 자동 셔터
5. **결과 표시**: 촬영된 사진 미리보기
6. **저장/재촬영**: 사진 저장 또는 다시 촬영

### 갤러리 화면
1. **사진 목록**: 촬영된 스마일 사진들
2. **사진 상세보기**: 확대/공유/삭제 기능

## 4. 핵심 기능

### 실시간 스마일 감지
- **얼굴 검출**: ML Kit Face Detection API
- **감정 분석**: TFLite 기반 웃음 감지 모델
- **임계값 설정**: 웃음 강도 0.7 이상 시 촬영
- **오탐지 방지**: 연속 3프레임 웃음 감지 시 촬영

### 카메라 최적화
- **CameraX 통합**: 안정적인 카메라 제어
- **실시간 분석**: ImageAnalysis Use Case 활용
- **프리뷰 성능**: 60fps 유지, 배터리 최적화
- **권한 관리**: 카메라/저장소 권한 처리

### UI/UX 최적화
- **웃음 피드백**: 실시간 웃음 게이지 표시
- **촬영 가이드**: 얼굴 인식 가이드라인
- **카운트다운**: 웃음 감지 후 1초 카운트다운
- **성공 피드백**: 촬영 성공 애니메이션

### 성능 최적화
- **프레임 샘플링**: 초당 10-15프레임으로 ML 분석
- **메모리 관리**: Bitmap 재사용, 즉시 해제
- **배터리 효율**: 백그라운드 시 카메라 중지
- **멀티스레딩**: ML 추론은 백그라운드 스레드

### ML 모델 최적화
- **경량화 모델**: TFLite 최적화된 웃음 감지 모델
- **추론 속도**: 100ms 이내 실시간 처리
- **정확도**: 다양한 조명/각도에서 안정적 감지
- **오탐지 방지**: 연속 프레임 검증 로직

### 사용자 경험
- **직관적 UI**: 원터치 촬영 시작
- **실시간 피드백**: 웃음 감지 상태 시각화
- **빠른 응답**: 웃음 감지 후 즉시 촬영
- **자연스러운 타이밍**: 웃음 정점에서 촬영

## 7. 성공 지표

### 기술적 지표
- **웃음 감지 정확도**: 85% 이상
- **촬영 응답 시간**: 웃음 감지 후 500ms 이내
- **앱 실행 속도**: 3초 이내 카메라 활성화
- **배터리 소모**: 연속 사용 30분 이상

### 사용자 경험 지표
- **성공적 촬영률**: 80% 이상 (자연스러운 웃음 포착)
- **재촬영 빈도**: 20% 이하
- **앱 사용 시간**: 평균 세션 5분 이상

### 메모리 효율성
1. 실시간 듀얼 ML 처리
2. 모바일 메모리 제약
3. 연속 프레임 처리
4. 멀티모듈 메모리 격리

---

