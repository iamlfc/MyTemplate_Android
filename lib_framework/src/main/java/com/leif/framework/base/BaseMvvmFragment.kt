package com.leif.framework.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import java.lang.reflect.ParameterizedType

/**
 * @author mingyan.su
 * @date   2023/2/27 12:31
 * @desc   DataBinding和ViewModel基类
 */
abstract class BaseMvvmFragment<DB : ViewDataBinding> : BaseDataBindFragment<DB>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    open fun initViewModel() {
        val argument = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
    }
}