package au.com.pjwin.shoppingcartdemo.viewmodel

import android.util.SparseArray
import au.com.pjwin.commonlib.util.JsonUtils
import au.com.pjwin.commonlib.util.toList
import au.com.pjwin.commonlib.viewmodel.DataViewModel
import au.com.pjwin.shoppingcartdemo.model.Cart
import au.com.pjwin.shoppingcartdemo.model.Product
import au.com.pjwin.shoppingcartdemo.model.ProductResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.isActive


class ProductViewModel : DataViewModel<ProductResponse>() {

    var productList: List<Product>? = null
        private set

    private val cart: Cart by lazy {
        Cart(SparseArray())
    }

    fun getProducts() {
        launchJob {
            val response = executeAwait({ loadProducts() },
                { onError(it) })

            if (isActive) {
                onData(response)
                productList = response?.products
                productList?.forEach {
                    it.updatePrice()
                }
            }
        }
    }

    fun updateCart(product: Product, qty: Int) {
        cart.updateCart(product, qty)
    }

    fun getTotalQuantity() = cart.getTotalQuantity()

    fun getTotalCost() = cart.getTotalCost()

    fun getCartItemList() = cart.items.toList()

    fun findProductById(id: Int): Product? {
        return productList?.firstOrNull {
            it.id == id
        }
    }

    private suspend fun loadProducts() = async {
        JsonUtils.loadJsonFromAsset("data.json", ProductResponse::class.java)
    }.await()
}