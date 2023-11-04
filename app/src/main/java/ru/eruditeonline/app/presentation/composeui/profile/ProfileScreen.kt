package ru.eruditeonline.app.presentation.composeui.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.composeui.model.Screen
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.ui.profile.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel,
) {
    val isAuthorized by viewModel.isAuthorizedLiveData.observeAsState(false)

    LaunchedEffect(Unit) {
        viewModel.resolveAuthState()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.profile_title))
                }
            )
        }
    ) { innerPaddings ->
        Column(
            Modifier
                .padding(innerPaddings)
        ) {
            ProfileMenuItem(
                text = stringResource(R.string.profile_search_results_button_label),
                icon = R.drawable.ic_search_results,
                onClick = {
                    navController.navigate(Screen.SearchResults.route)
                }
            )
            if (isAuthorized) {
                ProfileMenuItem(
                    text = stringResource(R.string.profile_user_results_button_label),
                    icon = R.drawable.ic_user_results,
                    onClick = {
                        navController.navigate(Screen.UserResults.route)
                    }
                )
            }
            ProfileMenuItem(
                text = stringResource(R.string.profile_common_results_button_label),
                icon = R.drawable.ic_common_results,
                onClick = {
                    navController.navigate(Screen.CommonResults.route)
                }
            )
            ProfileMenuItem(
                text = stringResource(R.string.profile_settings_button_label),
                icon = R.drawable.ic_settings,
                onClick = {
                    navController.navigate(Screen.Settings.route)
                }
            )
            ProfileMenuItem(
                text = stringResource(R.string.profile_information_button_label),
                icon = R.drawable.ic_info,
                onClick = {
                    navController.navigate(Screen.Info.route)
                }
            )
        }
    }
}

@Composable
private fun ProfileMenuItem(text: String, @DrawableRes icon: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                .align(Alignment.CenterVertically),
        )
        Text(
            text = text,
            style = AppTypography.bodyLarge,
            modifier = Modifier
                .padding(start = 12.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                .align(Alignment.CenterVertically),
        )
    }
}