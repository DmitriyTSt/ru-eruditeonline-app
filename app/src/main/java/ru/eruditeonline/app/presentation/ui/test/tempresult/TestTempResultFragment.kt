package ru.eruditeonline.app.presentation.ui.test.tempresult

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentTestTempResultBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

class TestTempResultFragment : BaseFragment(R.layout.fragment_test_temp_result) {
    private val binding by viewBinding(FragmentTestTempResultBinding::bind)
    private val viewModel: TestTempResultViewModel by appViewModels()
    private val args: TestTempResultFragmentArgs by navArgs()

    override fun callOperations() {
        viewModel.checkTest(args.data)
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        tempResultLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
        }
    }
}
