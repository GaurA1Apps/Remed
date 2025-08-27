package com.app.health.remed.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import remed.composeapp.generated.resources.Inter
import remed.composeapp.generated.resources.Res


/**
 * Returns the body font family for the app, constructed using Inter_Bold Normal.
 */
@Composable
fun bodyFontFamily(): FontFamily = FontFamily(
    Font(
        resource = Res.font.Inter,
        weight = FontWeight.Normal,
    )
)

/**
 * Returns the display font family for the app, constructed using Inter_Bold ExtraBold.
 */
@Composable
fun displayFontFamily(): FontFamily = FontFamily(
    Font(
        resource = Res.font.Inter,
        weight = FontWeight.ExtraBold,
    )
)


/**
 * Returns the custom app Typography, using displayFontFamily and bodyFontFamily based on Material3 best practices.
 */
@Composable
fun AppTypography(): Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = displayFontFamily(),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 48.sp, // Large display, for splash/title screens
        lineHeight = 56.sp,
        letterSpacing = 0.sp, // No extra spacing for display
    ),
    displayMedium = TextStyle(
        fontFamily = displayFontFamily(),
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 48.sp,
        letterSpacing = 0.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = displayFontFamily(),
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp,
    ),

    headlineLarge = TextStyle(
        fontFamily = displayFontFamily(),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 28.sp, // Section headers
        lineHeight = 36.sp,
        letterSpacing = 0.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = displayFontFamily(),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = displayFontFamily(),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
    ),

    titleLarge = TextStyle(
        fontFamily = bodyFontFamily(),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp, // Card title or major interface section
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = bodyFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp, // Subtitles
        lineHeight = 22.sp,
        letterSpacing = 0.1.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = bodyFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),

    bodyLarge = TextStyle(
        fontFamily = bodyFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp, // App main text size
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = bodyFontFamily(),
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.2.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = bodyFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp, // Helper, extra info
        lineHeight = 16.sp,
        letterSpacing = 0.2.sp,
    ),

    labelLarge = TextStyle(
        fontFamily = bodyFontFamily(),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp, // Buttons, active chips
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = bodyFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp, // Secondary controls/chips
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = bodyFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),
)
