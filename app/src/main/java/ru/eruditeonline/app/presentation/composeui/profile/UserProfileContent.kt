package ru.eruditeonline.app.presentation.composeui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.presentation.composeui.base.ObserveDestinations
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.views.StateFlipperView
import ru.eruditeonline.app.presentation.ui.profile.user.UserProfileViewModel

@Composable
fun UserProfileContent(navController: NavController, viewModel: UserProfileViewModel) {
    ObserveDestinations(navController, viewModel)

    val profileState by viewModel.profileLiveData.observeAsState(LoadableState.Loading())

    LaunchedEffect(Unit) {
        viewModel.callOperations {
            viewModel.loadProfile()
        }
    }

    StateFlipperView(
        state = profileState,
        onRetryClick = { viewModel.loadProfile() },
        modifier = Modifier.fillMaxSize(),
    ) { profile ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = profile.avatar,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(150.dp),
            )
            Text(
                text = "${profile.surname} ${profile.name} ${profile.patronymic.orEmpty()}",
                style = AppTypography.titleSmall,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = profile.country?.image,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
                Text(
                    text = buildString {
                        append(profile.country?.name.orEmpty())
                        if (isNotEmpty()) {
                            append(", ")
                        }
                        append(profile.city)
                    },
                    modifier = Modifier.padding(start = 8.dp),
                    style = AppTypography.bodyLarge,
                )
            }
        }
    }
}