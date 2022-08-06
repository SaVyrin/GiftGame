package ru.savyrin.gift.di

import android.animation.ValueAnimator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AnimationModule {

    @Provides
    @Named("infinite")
    fun provideInfiniteFadeAnimation(): AlphaAnimation {
        val textAnimation = AlphaAnimation(0.2f, 1f)
        textAnimation.duration = 1800
        textAnimation.repeatCount = Animation.INFINITE
        textAnimation.repeatMode = Animation.REVERSE

        return textAnimation
    }

    @Provides
    @Named("single")
    fun provideFadeAnimation(): AlphaAnimation {
        val textAnimation = AlphaAnimation(1.0f, 0.0f)
        textAnimation.duration = 500
        textAnimation.repeatCount = 1
        textAnimation.repeatMode = Animation.REVERSE

        return textAnimation
    }

    @Provides
    fun provideResizeAnimation(): ValueAnimator {
        val valueAnimator = ValueAnimator.ofFloat(1f, 0.9f, 1f)
        valueAnimator.duration = 100

        return valueAnimator
    }
}