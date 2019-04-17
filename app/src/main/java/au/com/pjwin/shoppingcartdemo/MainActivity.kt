package au.com.pjwin.shoppingcartdemo

import android.os.Bundle
import au.com.pjwin.commonlib.ui.ViewActivity

class MainActivity : ViewActivity() {

    init {
        navigationGraphIds = listOf(R.navigation.nav_graph_product_list, R.navigation.nav_graph_cart)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            setupBottomNavigation(R.menu.bottom_bar)
        }
    }
}
