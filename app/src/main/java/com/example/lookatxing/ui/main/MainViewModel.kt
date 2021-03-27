package com.example.lookatxing.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ActionResult
import com.example.domain.succeeded
import com.example.lookatxing.domain.github.GetGitHubListUseCase
import com.example.lookatxing.domain.github.Github
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGithubListUseCase: GetGitHubListUseCase
) : ViewModel() {
    private val _gitHubRepository = MutableLiveData<List<Github>>()

    val gitHubRepository: LiveData<List<Github>>
        get() = _gitHubRepository

    private var page = 0

    fun retrieveListGitHub() {
        viewModelScope.launch {
            handleListGitHub(getGithubListUseCase.execute(++page))
        }
    }

    private fun handleListGitHub(result: ActionResult<List<Github>>) {
        when (result) {
            is ActionResult.Success -> {
                if (result.succeeded) {
                    _gitHubRepository.value = result.data
                }
            }
            is ActionResult.Error -> {
                _gitHubRepository.value = emptyList()
                Log.e("handleListGitHub", result.exception.toString())
            }
            is ActionResult.Loading -> {
                Log.d("handleListGitHub", ActionResult.Loading.toString())
            }
        }
    }
}