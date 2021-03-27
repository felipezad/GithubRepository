package com.example.lookatxing.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ActionResult
import com.example.domain.succeeded
import com.example.lookatxing.domain.github.GetGitHubListLocalUseCase
import com.example.lookatxing.domain.github.GetGitHubListUseCase
import com.example.lookatxing.domain.github.Github
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGithubListUseCase: GetGitHubListUseCase,
    private val getLocalGithubListUseCase: GetGitHubListLocalUseCase
) : ViewModel() {
    private val _gitHubRepository = MutableLiveData<List<Github>>()
    val gitHubRepository: LiveData<List<Github>>
        get() = _gitHubRepository

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun retrieveListGitHub(page: Int) {
        _isLoading.value = true
        Log.d("retrieveListGitHub", "Page $page")
        viewModelScope.launch {
            handleListGitHub(getGithubListUseCase.execute(page))
        }
    }

    fun retrieveListFromRoom() {
        _isLoading.value = true
        viewModelScope.launch {
            handleListGitHub(getLocalGithubListUseCase.execute())
        }
    }

    private fun handleListGitHub(result: ActionResult<List<Github>>) {
        when (result) {
            is ActionResult.Success -> {
                if (result.succeeded) {
                    val finalListOfRepos = mutableSetOf<Github>().apply {
                        _gitHubRepository.value?.let { addAll(it) }
                        addAll(result.data)
                    }
                    val shouldUpdate =
                        !(_gitHubRepository.value?.containsAll(finalListOfRepos) ?: false)
                    if (shouldUpdate) {
                        _gitHubRepository.value = finalListOfRepos.toList()
                    }

                }
            }
            is ActionResult.Error -> {
                retrieveListFromRoom()
                Log.e("handleListGitHub", result.exception.toString())
            }
            else -> {
                Log.d("handleListGitHub", ActionResult.Loading.toString())
            }
        }
        _isLoading.value = false
    }
}