package com.example.lookatxing.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.example.lookatxing.R
import com.example.lookatxing.databinding.ActivityMainBinding
import com.example.lookatxing.domain.github.Github
import com.example.lookatxing.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        mViewModel.retrieveListGitHub()
    }

    override fun setupView() {
        mViewModel.gitHubRepository.observe(this, { result: List<Github> ->
            mViewBinding.textViewAnswer.text = "Success ${result.size}"
        })
    }
}