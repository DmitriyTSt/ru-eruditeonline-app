package ru.eruditeonline.app.presentation.composeui.views

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import ru.eruditeonline.app.R

@Composable
fun NavigationIcon(navController: NavController) {
    IconButton(onClick = { navController.popBackStack() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
        )
    }
}