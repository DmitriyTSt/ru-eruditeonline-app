package ru.eruditeonline.app.presentation.composeui.competition

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ru.eruditeonline.app.presentation.composeui.model.Screen
import kotlin.random.Random

@Composable
fun CompetitionScreen(id: Int, navController: NavController, viewModel: ComposeCompetitionViewModel) {

    LaunchedEffect(id) {
        viewModel.init(id)
    }

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier.align(Alignment.Center)
        ) {
            Text(text = "Competition $id")
            Button(onClick = { navController.navigate(Screen.Competition.route(Random.nextInt())) }) {
                Text(text = "NEXT")
            }
        }
    }
}