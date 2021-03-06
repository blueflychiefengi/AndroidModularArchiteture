package com.effective.android.service.account

import android.app.Application
import com.effective.android.service.account.data.db.AccountDataBase
import com.plugin.component.IComponent
import com.plugin.component.anno.AutoInjectComponent


/**
 * Email yummyl.lau@gmail.com
 * Created by yummylau on 2018/01/25.
 */
@AutoInjectComponent(impl = [AccountServiceImpl::class])
class AccountComponent : IComponent {

    companion object {
        lateinit var sApplication: Application
    }

    override fun attachComponent(application: Application) {
        sApplication = application
        AccountDataBase.initDataBase(application)
        Sdks.init(application)
    }

    override fun detachComponent() {

    }
}
