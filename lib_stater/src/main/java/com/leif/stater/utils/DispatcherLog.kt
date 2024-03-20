package com.leif.stater.utils

import com.leif.framework.helper.SumAppHelper
import com.leif.framework.log.LogUtil

object DispatcherLog {
    var isDebug = SumAppHelper.isDebug()

    @JvmStatic
    fun i(msg: String?) {
        if (msg == null) {
            return
        }
        LogUtil.i(msg, tag = "StartTask")
    }
}