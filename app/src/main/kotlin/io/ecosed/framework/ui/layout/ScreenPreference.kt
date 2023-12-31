package io.ecosed.framework.ui.layout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.viewpager2.widget.ViewPager2
import io.ecosed.framework.ui.preview.ScreenPreviews
import io.ecosed.framework.ui.widget.Preference

@Composable
fun ScreenPreference(
    preferenceUpdate: (ViewPager2) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Preference(
            modifier = Modifier.fillMaxSize(),
            update = preferenceUpdate
        )
    }
}

@Composable
@ScreenPreviews
fun ScreenPreferencePreview() {
    ScreenPreference { modifier ->
//        Box(
//            modifier = modifier,
//            contentAlignment = Alignment.Center,
//        ) {
//            Text(
//                text = "Preference Preview",
//                textAlign = TextAlign.Center,
//                style = MaterialTheme.typography.titleLarge
//            )
//        }
    }
}