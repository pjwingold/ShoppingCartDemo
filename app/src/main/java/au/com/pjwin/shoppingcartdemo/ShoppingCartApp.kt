package au.com.pjwin.shoppingcartdemo

import au.com.pjwin.commonlib.BaseApplication
import au.com.pjwin.commonlib.Common
import au.com.pjwin.shoppingcartdemo.util.AppConfig


open class ShoppingCartApp : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initCommon()
    }

    protected open fun initCommon() {
        Common.init(applicationContext, AppConfig())
    }
}