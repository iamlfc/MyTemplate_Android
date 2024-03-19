package com.sum.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.sum.common.model.User
import com.sum.framework.toast.TipsToast
import com.sum.main.repository.HomeRepository
import com.sum.network.viewmodel.BaseViewModel

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