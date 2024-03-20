package com.leif.main.viewModel

import androidx.lifecycle.MutableLiveData
import com.leif.common.model.User
import com.leif.main.repository.HomeRepository
import com.leif.network.viewmodel.BaseViewModel

/**
 * @author mingyan.su
 * @date   2023/3/25 16:38
 * @desc   MainviewModel
 */
class MainViewModel : BaseViewModel() {
    val loginLiveData = MutableLiveData<User?>()
    val registerLiveData = MutableLiveData<User?>()
    val loginRepository by lazy { HomeRepository() }


}