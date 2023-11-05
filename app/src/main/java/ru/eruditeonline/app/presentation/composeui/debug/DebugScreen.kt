package ru.eruditeonline.app.presentation.composeui.debug

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.views.NavigationIcon
import ru.eruditeonline.app.presentation.composeui.views.StateFlipperView
import ru.eruditeonline.app.presentation.ui.debug.DebugViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DebugScreen(navController: NavController, viewModel: DebugViewModel) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val debugDataState by viewModel.debugDataLiveData.observeAsState(LoadableState.Loading())

    LaunchedEffect(Unit) {
        viewModel.callOperations {
            viewModel.initData()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.restartAppLiveEvent.asFlow().collectLatest { state ->
            state.doOnSuccess { isChanged ->
                if (isChanged) {
                    context.startActivity(viewModel.appStarter.createStartIntent(context))
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.debug_title),
                        style = AppTypography.titleLarge,
                    )
                },
                navigationIcon = {
                    NavigationIcon(navController)
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets.statusBars,
    ) { innerPaddings ->
        StateFlipperView(
            state = debugDataState,
            onRetryClick = { viewModel.initData() },
            modifier = Modifier.padding(innerPaddings)
        ) { data ->
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.debug_compose_enabled_switch_label),
                    modifier = Modifier.weight(1f),
                    style = AppTypography.bodyLarge,
                )
                Switch(checked = data.isComposeEnabled, onCheckedChange = { isChecked ->
                    viewModel.setComposeEnabled(isChecked)
                })
            }
        }
    }
}