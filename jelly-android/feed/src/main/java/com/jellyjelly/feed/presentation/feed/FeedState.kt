package com.jellyjelly.feed.presentation.feed

import com.jellyjelly.feed.domain.model.Post

data class FeedState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class FeedEvent {
    data class ShowError(val message: String) : FeedEvent()
    data class NavigateToPost(val postId: String) : FeedEvent()
    object NavigateToCreatePost : FeedEvent()
} 