package com.jellyjelly.feed.presentation.feed

import androidx.lifecycle.viewModelScope
import com.jellyjelly.feed.domain.usecase.GetPostsUseCase
import com.jellyjelly.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel<FeedState, FeedEvent>() {

    override fun createInitialState(): FeedState = FeedState()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            setState { copy(isLoading = true, error = null) }
            try {
                getPostsUseCase().collect { posts ->
                    setState { copy(posts = posts, isLoading = false) }
                }
            } catch (e: Exception) {
                setState { copy(isLoading = false, error = e.message) }
                setEvent(FeedEvent.ShowError(e.message ?: "Unknown error occurred"))
            }
        }
    }

    fun onPostClick(postId: String) {
        setEvent(FeedEvent.NavigateToPost(postId))
    }

    fun onCreatePostClick() {
        setEvent(FeedEvent.NavigateToCreatePost)
    }

    fun onRefresh() {
        loadPosts()
    }
} 