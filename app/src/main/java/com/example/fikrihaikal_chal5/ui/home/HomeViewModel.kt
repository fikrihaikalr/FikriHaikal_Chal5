package com.example.fikrihaikal_chal5.ui.home

import MovieResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _movie = MutableLiveData<MovieResponse>()
    val movie: LiveData<MovieResponse> = _movie

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    private val _user = MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?> = _user


    fun getAllMoviesNowPlaying() = viewModelScope.launch(Dispatchers.IO) {
        _loadingState.postValue(true)
        delay(1000L)
        val response = ApiConfig.getApiService().getAllMoviesNowPlaying()
        viewModelScope.launch(Dispatchers.Main) {
            _loadingState.postValue(false)
            _movie.postValue(response)
        }
    }

    fun session() {
        if (Firebase.auth.currentUser != null) {
            _user.postValue(Firebase.auth.currentUser)
        } else {
            _user.postValue(null)
        }
    }
}