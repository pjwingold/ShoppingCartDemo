package au.com.pjwin.shoppingcartdemo

import android.app.Application
import au.com.pjwin.commonlib.Common
import au.com.pjwin.shoppingcartdemo.util.AppConfig

open class ShoppingCartApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initCommon()
    }

    protected open fun initCommon() {
        Common.init(applicationContext, AppConfig())
    }
}