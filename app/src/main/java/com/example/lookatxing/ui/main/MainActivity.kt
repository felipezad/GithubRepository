package com.example.lookatxing.ui.main

import androidx.activity.viewModels
import com.example.lookatxing.databinding.ActivityMainBinding
import com.example.lookatxing.domain.github.Github
import com.example.lookatxing.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val mViewModel: MainViewModel by viewModels()

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        mViewModel.retrieveListGitHub()
    }

    override fun setupObservers() {
        mViewModel.gitHubRepository.observe(this, { result: List<Github> ->
            mViewBinding.textViewAnswer.text = "Success ${result.size}"
        })
    }
}