package com.jellyjelly.feed.di

import com.jellyjelly.feed.data.repository.MockFeedRepository
import com.jellyjelly.feed.domain.repository.FeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeedModule {

    @Provides
    @Singleton
    fun provideFeedRepository(
        mockRepository: MockFeedRepository
    ): FeedRepository {
        return mockRepository
    }
} 