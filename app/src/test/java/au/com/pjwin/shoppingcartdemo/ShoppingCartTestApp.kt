package au.com.pjwin.shoppingcartdemo

import au.com.pjwin.commonlib.Common
import au.com.pjwin.shoppingcartdemo.util.TestConfig

class ShoppingCartTestApp : ShoppingCartApp() {

    override fun initCommon() {
        Common.init(applicationContext, TestConfig(), true)
    }
}