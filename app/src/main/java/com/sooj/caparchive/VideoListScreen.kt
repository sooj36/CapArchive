package com.sooj.caparchive

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream


data class VideoItem (
    val id : String,
    val title : String,
    val resourceId : Int,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoListScreen(
    navController: NavController = rememberNavController(),
    folderName : String
) {
    // list
    val sampleVideoItem = listOf(
        VideoItem(
            id = "1",
            title = "sample_ver1",
            resourceId = R.raw.sample_1,
        ),

        VideoItem(
            id = "2",
            title = "sample_ver2",
            resourceId = R.raw.sample_2,
        ),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(folderName) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "뒤로 가기"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(sampleVideoItem) { video ->
                VideoItemCard(
                    video = video,
                    onVideoClick = {
                        navController.navigate("video_detail/${video.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun VideoItemCard(
   video : VideoItem,
   onVideoClick : () -> Unit,
) {
    val context = LocalContext.current
    var thumbnail by remember { mutableStateOf<Bitmap?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    
    // 썸네일 생성
    LaunchedEffect(video.resourceId) {
        withContext(Dispatchers.IO) {
            try {
                val videoUri = Uri.parse("android.resource://${context.packageName}/${video.resourceId}")
                
                // raw 리소스를 임시 파일로 복사
                val inputStream = context.resources.openRawResource(video.resourceId)
                val tempFile = File(context.cacheDir, "temp_video_${video.id}.mp4")
                val outputStream = FileOutputStream(tempFile)
                
                inputStream.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }
                
                // MediaMetadataRetriever를 사용해 썸네일 생성
                val retriever = MediaMetadataRetriever()
                val bitmap = try {
                    retriever.setDataSource(tempFile.absolutePath)
                    retriever.getFrameAtTime(1000000) // 1초 시점의 프레임
                } catch (e: Exception) {
                    null
                } finally {
                    retriever.release()
                }
                
                thumbnail = bitmap
                tempFile.delete() // 임시 파일 삭제
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onVideoClick()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 비디오 썸네일
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                when {
                    isLoading -> {
                        // 로딩 중
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(32.dp)
                                    .padding(8.dp)
                            )
                        }
                    }
                    thumbnail != null -> {
                        // 썸네일 표시
                        Image(
                            bitmap = thumbnail!!.asImageBitmap(),
                            contentDescription = "Video thumbnail",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    else -> {
                        // 썸네일 생성 실패시 기본 아이콘
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Video",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                
                // 재생 버튼 오버레이
                Surface(
                    modifier = Modifier.size(32.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play Video",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Text(
                text = video.title,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}