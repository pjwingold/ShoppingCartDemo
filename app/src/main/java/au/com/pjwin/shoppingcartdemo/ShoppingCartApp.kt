package au.com.pjwin.shoppingcartdemo

import au.com.pjwin.commonlib.BaseApplication
import au.com.pjwin.commonlib.Common
import au.com.pjwin.commonlib.viewmodel.VoidViewModel
import au.com.pjwin.shoppingcartdemo.util.AppConfig
import au.com.pjwin.shoppingcartdemo.viewmodel.ProductViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

open class ShoppingCartApp : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initCommon()
    }

    protected open fun initCommon() {
        Common.init(applicationContext, AppConfig())
    }

    /*override fun setupModules() =
        listOf(
            module { viewModel { ProductViewModel() } },
            module { viewModel { VoidViewModel() } })*/
}