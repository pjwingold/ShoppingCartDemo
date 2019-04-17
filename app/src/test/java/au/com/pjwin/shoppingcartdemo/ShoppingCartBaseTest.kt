package au.com.pjwin.shoppingcartdemo

import au.com.pjwin.commonlib.util.BaseTest
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AppTestRunner::class)
@Config(application = ShoppingCartTestApp::class, sdk = [27])
abstract class ShoppingCartBaseTest : BaseTest()