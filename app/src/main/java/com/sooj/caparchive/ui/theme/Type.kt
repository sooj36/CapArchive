package com.sooj.caparchive.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sooj.caparchive.R

// Paperlogy 폰트 패밀리 정의
val PaperlogyFontFamily = FontFamily(
  Font(R.font.paperlogy_1_thin, FontWeight.Thin),           // 100
  Font(R.font.paperlogy_2_extralight, FontWeight.ExtraLight), // 200
  Font(R.font.paperlogy_3_light, FontWeight.Light),         // 300
  Font(R.font.paperlogy_4_regular, FontWeight.Normal),      // 400
  Font(R.font.paperlogy_5_medium, FontWeight.Medium),       // 500
  Font(R.font.paperlogy_6_semibold, FontWeight.SemiBold),   // 600
  Font(R.font.paperlogy_7_bold, FontWeight.Bold),           // 700
  Font(R.font.paperlogy_8_extrabold, FontWeight.ExtraBold), // 800
  Font(R.font.paperlogy_9_black, FontWeight.Black)          // 900
)

// Material Design 3 Typography with Paperlogy font
val Typography = Typography(
  // Display styles
  displayLarge = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 57.sp,
    lineHeight = 64.sp,
    letterSpacing = (-0.25).sp
  ),
  displayMedium = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 45.sp,
    lineHeight = 52.sp,
    letterSpacing = 0.sp
  ),
  displaySmall = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 36.sp,
    lineHeight = 44.sp,
    letterSpacing = 0.sp
  ),
  
  // Headline styles
  headlineLarge = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 32.sp,
    lineHeight = 40.sp,
    letterSpacing = 0.sp
  ),
  headlineMedium = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 28.sp,
    lineHeight = 36.sp,
    letterSpacing = 0.sp
  ),
  headlineSmall = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 24.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp
  ),
  
  // Title styles
  titleLarge = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
  ),
  titleMedium = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.15.sp
  ),
  titleSmall = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.1.sp
  ),
  
  // Body styles
  bodyLarge = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
  ),
  bodyMedium = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.25.sp
  ),
  bodySmall = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.4.sp
  ),
  
  // Label styles
  labelLarge = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.1.sp
  ),
  labelMedium = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
  ),
  labelSmall = TextStyle(
    fontFamily = PaperlogyFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
  )
)