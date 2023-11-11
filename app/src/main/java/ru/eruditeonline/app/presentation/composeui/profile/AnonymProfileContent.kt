package ru.eruditeonline.app.presentation.composeui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.composeui.base.ObserveDestinations
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.ui.profile.anonym.AnonymProfileViewModel

@Composable
fun AnonymProfileContent(navController: NavController, viewModel: AnonymProfileViewModel) {
    ObserveDestinations(navController, viewModel)

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.img_logo),
            contentDescription = null,
            modifier = Modifier.size(150.dp),
        )
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column {
                Button(onClick = { viewModel.openLogin() }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = stringResource(R.string.profile_login_button_label), style = AppTypography.titleSmall)
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { viewModel.openRegistration() }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = stringResource(R.string.registration_title), style = AppTypography.titleSmall)
                }
            }
        }
    }
}
