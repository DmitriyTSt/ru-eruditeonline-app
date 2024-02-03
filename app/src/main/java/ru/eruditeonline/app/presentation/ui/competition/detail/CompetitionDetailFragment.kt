package ru.eruditeonline.app.presentation.ui.competition.detail

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.CompetitionItem
import ru.eruditeonline.app.databinding.FragmentCompetitionDetailBinding
import ru.eruditeonline.app.presentation.extension.addLinearSpaceItemDecoration
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.doOnEnd
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.extension.load
import ru.eruditeonline.app.presentation.extension.setDifficulty
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import javax.inject.Inject

class CompetitionDetailFragment : BaseFragment(R.layout.fragment_competition_detail) {
    private val binding by viewBinding(FragmentCompetitionDetailBinding::bind)
    private val viewModel: CompetitionDetailViewModel by appViewModels()
    private val args: CompetitionDetailFragmentArgs by navArgs()

    @Inject lateinit var infosAdapter: CompetitionInfosAdapter
    @Inject lateinit var buttonsAdapter: CompetitionTestButtonsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater
            .from(requireContext())
            .inflateTransition(android.R.transition.move)
            .doOnEnd(viewModel::setAnimationEnd)
    }

    override fun callOperations() {
        viewModel.loadCompetitionItem(args.id)
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        stateViewFlipper.setRetryMethod { viewModel.loadCompetitionItem(args.id) }
        if (args.transitionName != null && args.imageUrl != null) {
            imageView.transitionName = args.transitionName
            imageView.load(args.imageUrl)
        }
        setupInfos()
        setupButtons()
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        competitionItemLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
            state.doOnSuccess { item ->
                bindCompetitionItem(item)
            }
        }
    }

    private fun setupInfos() = with(binding.content.recyclerViewInfos) {
        adapter = infosAdapter
        addLinearSpaceItemDecoration()
    }

    private fun setupButtons() = with(binding.content.recyclerViewButtons) {
        buttonsAdapter.onItemClick = viewModel::openTest
        adapter = buttonsAdapter
        addLinearSpaceItemDecoration(R.dimen.padding_16)
    }

    private fun bindCompetitionItem(item: CompetitionItem) = with(binding.content) {
        binding.toolbar.title = item.subject
        binding.imageView.load(item.icon)
        textViewTitle.text = item.title
        textViewAges.text = getString(R.string.competition_ages_template, item.ages)
        imageViewDifficulty.setDifficulty(item.difficulty)
        val hasDescription = !item.description.isNullOrEmpty()
        textViewDescription.isVisible = hasDescription
        if (hasDescription) {
            textViewDescription.text = item.description
        }
        val hasInfos = item.infos.isNotEmpty()
        textViewInfosTitle.isVisible = hasInfos
        recyclerViewInfos.isVisible = hasInfos
        if (hasInfos) {
            infosAdapter.submitList(item.infos)
        }
        textViewStartTestLabel.isVisible = item.tests.size > 1
        buttonsAdapter.submitList(item.tests)
    }
}
