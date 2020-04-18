package net.kathir.koinmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.kathir.koinmvvm.model.repository.UserRepository
import net.kathir.koinmvvm.utils.LoadingState
import java.lang.Exception

class UserViewModel(private val userRepository: UserRepository) : ViewModel()
{
    private val _loadingState = MutableLiveData<LoadingState>()

    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    val data = userRepository.data

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {

                _loadingState.value = LoadingState.LOADING
                userRepository.refresh()
                _loadingState.value = LoadingState.LOADED

            }catch (e: Exception)
            {
                _loadingState.value = LoadingState.error(e.message)
            }
        }
    }
}