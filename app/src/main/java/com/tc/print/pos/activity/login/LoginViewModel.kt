package com.tc.print.pos.activity.login

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.tc.print.base.BaseViewModel
import com.tc.print.base.SingleLiveEvent
import com.tc.print.http.LoginRequest
import com.tc.print.http.RetrofitService
import com.tc.print.http.token.TokenManager
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): BaseViewModel(application) {

    val username = ObservableField<String>()
    val password = ObservableField<String>()
    val passwordTwice = ObservableField<String>()

    private val tokenManager by lazy { TokenManager(application) }
    private val apiService by lazy {
        RetrofitService.getInstance(tokenManager).createApiService()
    }

    //登录或者注册是否成功
    val actionState = SingleLiveEvent<Boolean>()

    init {
        username.set("lylsy")
        password.set("lylsy")
    }

    /**
     * 注册
     */
    fun login() {
        if (checkNull(username.get()) || checkNull(password.get())) {
            ToastUtils.showShort("输入不能为空")
            return
        }
        ToastUtils.showShort("登录")
        viewModelScope.launch {
            val loginResponse = apiService.login(LoginRequest(username.get() ?: "", password.get() ?: ""))
            LogUtils.d("======================> ${loginResponse}")
            if (loginResponse.code == 0) {
                //登录成功
                actionState.postValue(true)
            } else {
                actionState.postValue(false)
            }
        }
    }

    /**
     * 注册
     */
    fun register() {
        if (checkNull(username.get()) || checkNull(password.get()) || checkNull(passwordTwice.get())) {
            ToastUtils.showShort("输入不能为空")
            return
        }
        ToastUtils.showShort("注册")
//        viewModelScope.launch {
//            val data: UserData? = Repository.register(
//                username.get() ?: "",
//                password.get() ?: "",
//                passwordTwice.get() ?: ""
//            )
//            if (data != null) {
//                //注册成功
//                actionState.postValue(true)
//            } else {
//                actionState.postValue(false)
//            }
//        }
    }

    private fun checkNull(value: String?): Boolean {
        return value.isNullOrEmpty()
    }
}