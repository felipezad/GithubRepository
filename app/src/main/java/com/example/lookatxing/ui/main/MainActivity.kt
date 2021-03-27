package com.example.lookatxing.ui.main

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lookatxing.databinding.ActivityMainBinding
import com.example.lookatxing.domain.github.Github
import com.example.lookatxing.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val mViewModel: MainViewModel by viewModels()

    private val githubListAdapter: GithubItemAdapter by lazy {
        GithubItemAdapter(requestManager = requestManagerGlide)
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        mViewModel.retrieveListGitHub()
    }

    override fun setupObservers() {
        mViewModel.gitHubRepository.observe(this, { result: List<Github> ->
            githubListAdapter.submitList(result)
        })

        mViewBinding.homeGithubRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = githubListAdapter
        }
    }
}