package ru.eruditeonline.app.presentation.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentDashboardBinding
import ru.eruditeonline.app.presentation.extension.addLinearSpaceItemDecoration
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.app.presentation.ui.dashboard.mainsection.MainSectionsAdapter
import javax.inject.Inject

class DashboardFragment : BaseFragment(R.layout.fragment_dashboard) {

    override val showBottomNavigationView: Boolean = true

    private val binding by viewBinding(FragmentDashboardBinding::bind)
    private val viewModel: DashboardViewModel by appViewModels()

    private val padding16 by lazy { resources.getDimensionPixelSize(R.dimen.padding_16) }

    @Inject lateinit var mainSectionsAdapter: MainSectionsAdapter

    override fun callOperations() {
        viewModel.loadMainSections()
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        appBarLayout.fitTopInsetsWithPadding()
        setupList()
        Unit
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        mainSectionsLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
            state.doOnSuccess { list ->
                mainSectionsAdapter.submitList(list)
            }
        }
    }

    override fun applyBottomNavigationViewPadding(view: View, bottomNavigationViewHeight: Int) {
        binding.recyclerView.updatePadding(bottom = bottomNavigationViewHeight + padding16)
    }

    private fun setupList() = with(binding) {
        mainSectionsAdapter.onCompetitionItemClick = viewModel::openCompetition
        recyclerView.apply {
            adapter = mainSectionsAdapter
            addLinearSpaceItemDecoration(R.dimen.padding_16)
        }
    }
}
