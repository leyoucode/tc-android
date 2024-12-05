package com.tc.print.pos.activity.login

import com.tc.print.base.BaseActivity
import com.tc.print.pos.R
import com.tc.print.pos.BR
import com.tc.print.pos.databinding.ActivityLoginBinding

class LoginActivity: BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModelId(): Int {
        return BR.loginVm
    }

    override fun initViewData() {
        binding?.loginOrRegisterBtn?.setOnClickListener{
            viewModel?.login()
        }
    }


}