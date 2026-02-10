package ru.eruditeonline.app.presentation.composeui.information

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.presentation.composeui.base.BottomNavigationSpaceWithInset
import ru.eruditeonline.app.presentation.composeui.base.ObserveDestinations
import ru.eruditeonline.app.presentation.composeui.base.appViewModel
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.views.NavigationIcon
import ru.eruditeonline.app.presentation.composeui.views.StateFlipperView
import ru.eruditeonline.app.presentation.ui.information.InformationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationScreen(viewModel: InformationViewModel = appViewModel()) {
    viewModel.ObserveDestinations()
    val webPagesState by viewModel.webPagesLiveData.observeAsState(LoadableState.Loading())

    LaunchedEffect(Unit) {
        viewModel.callOperations {
            viewModel.loadWebPages()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.information_title)) },
                navigationIcon = { NavigationIcon(viewModel::navigateBack) },
            )
        },
        contentWindowInsets = WindowInsets.systemBars,
    ) { innerPaddings ->
        StateFlipperView(
            state = webPagesState,
            onRetryClick = viewModel::loadWebPages,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
        ) { pages ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                        .size(150.dp)
                        .clip(CircleShape),
                )
                Text(
                    text = viewModel.appVersionText,
                    style = AppTypography.bodySmall,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    Modifier.padding(16.dp),
                    colors = CardDefaults.cardColors().copy(
                        contentColor = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.surface),
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(0.dp),
                    ) {
                        pages.forEach { page ->
                            Text(
                                text = page.name,
                                style = AppTypography.bodyLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { viewModel.openWebPage(page) }
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                            )
                        }
                    }
                }
                BottomNavigationSpaceWithInset(innerPaddings, 16.dp)
            }
        }
    }
}
