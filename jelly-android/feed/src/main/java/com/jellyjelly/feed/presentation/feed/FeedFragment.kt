package com.jellyjelly.feed.presentation.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jellyjelly.feed.databinding.FragmentFeedBinding
import com.jellyjelly.common.base.BaseFragment
import com.jellyjelly.common.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FeedFragment : BaseFragment<FragmentFeedBinding>() {

    private val viewModel: FeedViewModel by viewModels()
    private val postsAdapter = PostsAdapter { postId -> viewModel.onPostClick(postId) }

    override fun getViewBinding() = FragmentFeedBinding.inflate(layoutInflater)

    override fun setupViews() {
        with(binding) {
            recyclerView.adapter = postsAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            swipeRefreshLayout.setOnRefreshListener { viewModel.onRefresh() }
            createPostButton.setOnClickListener { viewModel.onCreatePostClick() }
        }
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState.collectLatest { state ->
                updateUI(state)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.event.collectLatest { event ->
                handleEvent(event)
            }
        }
    }

    private fun updateUI(state: FeedState) {
        with(binding) {
            swipeRefreshLayout.isRefreshing = state.isLoading
            postsAdapter.submitList(state.posts)
            errorText.text = state.error
            errorText.visibility = if (state.error != null) View.VISIBLE else View.GONE
        }
    }

    private fun handleEvent(event: FeedEvent) {
        when (event) {
            is FeedEvent.ShowError -> showToast(event.message)
            is FeedEvent.NavigateToPost -> {
                // TODO: Navigate to post details
            }
            FeedEvent.NavigateToCreatePost -> {
                // TODO: Navigate to create post
            }
        }
    }
} 