package com.example.lookatxing.ui.main

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lookatxing.databinding.ActivityMainBinding
import com.example.lookatxing.domain.github.Github
import com.example.lookatxing.ui.BaseActivity
import com.example.lookatxing.ui.InfiniteRecyclerViewScrollListener
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
        mViewModel.retrieveListGitHub(0)
    }

    override fun setupObservers() {
        mViewModel.gitHubRepository.observe(this, { result: List<Github> ->
            if (result.isNotEmpty()) {
                githubListAdapter.submitList(result)
            }
        })

        mViewModel.isLoading.observe(this, { isLoading ->
            if (isLoading) {
                mViewBinding.contentLoadingProgressBar.show()
            } else {
                mViewBinding.contentLoadingProgressBar.hide()
            }
        })

        mViewBinding.homeGithubRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = githubListAdapter
            addOnScrollListener(object :
                InfiniteRecyclerViewScrollListener(this.layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    mViewModel.retrieveListGitHub(page)
                }
            })
        }
    }
}