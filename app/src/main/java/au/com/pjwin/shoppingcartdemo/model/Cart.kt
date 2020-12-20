package au.com.pjwin.shoppingcartdemo.model

import android.util.SparseArray
import androidx.core.util.forEach
import java.math.BigDecimal

data class Cart(private var cartItems: SparseArray<CartItem>) {

    val items: SparseArray<CartItem> by lazy {
        cartItems
    }

    //todo add synch op
    @Throws(IllegalArgumentException::class)
    fun updateCart(product: Product, quantity: Int) {
        val cartItem = cartItems[product.id]
        when {
            quantity < 0 -> throw IllegalArgumentException("Quantity must be greater than zero.")

            quantity == 0 -> {
                if (cartItem != null) {
                    cartItems.remove(product.id)
                }
            }
            else -> {
                if (cartItem == null) {
                    cartItems.put(product.id, CartItem(product, quantity))

                } else {
                    cartItem.quantity = quantity
                }
            }
        }
    }

    fun getTotalQuantity(): Int {
        var total = 0
        cartItems.forEach { _, item ->
            println("item ${item.product} qty ${item.quantity}")
            total += item.quantity
        }
        return total
    }

    fun getTotalCost(): BigDecimal {
        var total = BigDecimal(0)
        cartItems.forEach { _, item ->
            total = total.add(item.product.priceDecimal.multiply(item.quantity.toBigDecimal()))
        }

        return total
    }
}

class CartItem(
    val product: Product,
    quantity: Int
) {
    var quantity: Int = quantity
        set(value) {
            field = value
            updateLineTotal()
        }

    var lineTotal = BigDecimal(0)
        private set

    var lineTotalDisplay = ""

    init {
        updateLineTotal()
    }

    private fun updateLineTotal() {
        lineTotal = product.priceDecimal.multiply(BigDecimal(quantity))
        lineTotalDisplay = String.format("$%S", lineTotal)
        //todo add discount support
    }
}