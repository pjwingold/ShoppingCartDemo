package au.com.pjwin.shoppingcartdemo

import au.com.pjwin.commonlib.Common

class ShoppingCartTestApp : ShoppingCartApp() {

    override fun initCommon() {
        Common.init(applicationContext, TestConfig(), true)
    }
}