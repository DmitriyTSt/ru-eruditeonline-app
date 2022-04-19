package ru.eruditeonline.app.presentation.ui.diploma

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentSelectDiplomaBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import javax.inject.Inject

class SelectDiplomaFragment : BaseFragment(R.layout.fragment_select_diploma) {

    companion object {
        const val REQUEST_CODE = "select_diploma_request_code"
        const val KEY_DIPLOMA = "key_diploma"
    }

    private val binding by viewBinding(FragmentSelectDiplomaBinding::bind)
    private val viewModel: SelectDiplomaViewModel by appViewModels()

    @Inject lateinit var diplomasAdapter: DiplomasAdapter

    override fun callOperations() {
        viewModel.loadDiplomas()
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        setupRecyclerView()
        stateViewFlipper.setRetryMethod { viewModel.loadDiplomas() }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        diplomasLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
            state.doOnSuccess { diplomas ->
                diplomasAdapter.submitList(diplomas)
            }
        }
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        diplomasAdapter.onItemClick = { diploma ->
            setFragmentResult(REQUEST_CODE, bundleOf(KEY_DIPLOMA to diploma))
            viewModel.navigateBack()
        }
        adapter = diplomasAdapter
    }
}
