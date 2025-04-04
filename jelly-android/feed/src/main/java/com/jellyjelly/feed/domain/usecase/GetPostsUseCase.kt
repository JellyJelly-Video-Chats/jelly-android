package com.jellyjelly.feed.domain.usecase

import com.jellyjelly.feed.domain.model.Post
import com.jellyjelly.feed.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val feedRepository: FeedRepository
) {
    operator fun invoke(): Flow<List<Post>> = feedRepository.getPosts()
} 