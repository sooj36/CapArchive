package com.caparchive.app.presentation.ui.image

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetailScreen(
    navController: NavController,
    imageId: String
) {
    // 임시 더미 데이터 (나중에 ViewModel로 교체)
    val imageDetail = remember {
        ImageDetail(
            id = imageId,
            imagePath = "", // 실제 이미지 경로
            title = "캡쳐 상세",
            ocrTexts = listOf(
                OcrTextItem(
                    id = "1",
                    text = "이것은 OCR로 인식된 첫 번째 텍스트입니다.",
                    isHighlighted = false
                ),
                OcrTextItem(
                    id = "2",
                    text = "중요한 정보가 포함된 두 번째 텍스트입니다.",
                    isHighlighted = true
                ),
                OcrTextItem(
                    id = "3",
                    text = "일반적인 세 번째 텍스트 내용입니다.",
                    isHighlighted = false
                )
            )
        )
    }

    var highlightedTexts by remember {
        mutableStateOf(imageDetail.ocrTexts.map { it.copy() })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = imageDetail.title,
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "뒤로가기"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 이미지 섹션
            item {
                ImageSection(
                    imagePath = imageDetail.imagePath,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // OCR 텍스트 제목
            item {
                Text(
                    text = "인식된 텍스트",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            // OCR 텍스트 리스트
            items(highlightedTexts) { ocrText ->
                OcrTextCard(
                    ocrText = ocrText,
                    onHighlightToggle = { textId ->
                        highlightedTexts = highlightedTexts.map { text ->
                            if (text.id == textId) {
                                text.copy(isHighlighted = !text.isHighlighted)
                            } else {
                                text
                            }
                        }
                        // TODO: ViewModel을 통해 하이라이트 상태 저장
                    }
                )
            }
        }
    }
}

@Composable
fun ImageSection(
    imagePath: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentAlignment = Alignment.Center
        ) {
            // 실제로는 AsyncImage나 Image 컴포넌트 사용
            // 임시 플레이스홀더
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack, // 임시 아이콘
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "이미지 플레이스홀더",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun OcrTextCard(
    ocrText: OcrTextItem,
    onHighlightToggle: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (ocrText.isHighlighted) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = buildHighlightedText(ocrText.text, ocrText.isHighlighted),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = { onHighlightToggle(ocrText.id) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = if (ocrText.isHighlighted) "하이라이트 해제" else "하이라이트",
                    tint = if (ocrText.isHighlighted) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
        }
    }
}

@Composable
private fun buildHighlightedText(
    text: String,
    isHighlighted: Boolean
): AnnotatedString {
    return buildAnnotatedString {
        if (isHighlighted) {
            withStyle(
                style = SpanStyle(
                    background = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                    fontWeight = FontWeight.Medium
                )
            ) {
                append(text)
            }
        } else {
            append(text)
        }
    }
}

// 데이터 클래스들
data class ImageDetail(
    val id: String,
    val imagePath: String,
    val title: String,
    val ocrTexts: List<OcrTextItem>
)

data class OcrTextItem(
    val id: String,
    val text: String,
    val isHighlighted: Boolean
)