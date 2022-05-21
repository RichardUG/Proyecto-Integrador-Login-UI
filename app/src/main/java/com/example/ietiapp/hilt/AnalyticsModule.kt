package com.example.ietiapp.hilt

import com.google.android.gms.analytics.AnalyticsService
import com.google.android.gms.analytics.GoogleAnalytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
    class AnalyticsModule {
    @Provides
    fun provideAnalyticsService(): AnalyticsService? {
        //return GoogleAnalyticsService()
        return  null
    }
}