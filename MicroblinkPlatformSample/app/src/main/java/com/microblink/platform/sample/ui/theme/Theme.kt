package com.microblink.platform.sample.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.microblink.ux.theme.Cobalt

private val LightColorScheme = lightColorScheme(
    primary = Cobalt,
)

@Composable
fun MicroblinkPlatformTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}