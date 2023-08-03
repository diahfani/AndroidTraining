package com.example.projecttraining2

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val context: Context
    ) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> fragment_onboarding.newInstance(
                context.resources.getString(R.string.title_onboarding_1),
                context.resources.getString(R.string.description_onboarding_1),
                R.raw.lottie_headphonehuman
            )
            1 -> fragment_onboarding.newInstance(
                context.resources.getString(R.string.title_onboarding_2),
                context.resources.getString(R.string.description_onboarding_2),
                R.raw.animation_lks05gsz
            )
            else -> fragment_onboarding.newInstance(
                context.resources.getString(R.string.title_onboarding_3),
                context.resources.getString(R.string.description_onboarding_3),
                R.raw.lottie_enjoyhuman
            )
        }
    }

}