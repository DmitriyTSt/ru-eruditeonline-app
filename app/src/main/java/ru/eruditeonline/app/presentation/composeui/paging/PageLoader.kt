package ru.eruditeonline.app.presentation.composeui.paging

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PageLoader(modifier: Modifier = Modifier) {
    Box(modifier) {
        CircularProgressIndicator(modifier = Modifier
            .align(Alignment.Center)
            .padding(vertical = 16.dp))
    }
}