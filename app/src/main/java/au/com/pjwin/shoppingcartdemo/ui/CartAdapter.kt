package au.com.pjwin.shoppingcartdemo.ui

import android.content.Context
import au.com.pjwin.commonlib.ui.adapter.RecyclerListAdapter
import au.com.pjwin.commonlib.ui.adapter.RecyclerViewHolder
import au.com.pjwin.shoppingcartdemo.R
import au.com.pjwin.shoppingcartdemo.databinding.CartItemBinding
import au.com.pjwin.shoppingcartdemo.model.CartItem

class CartAdapter(
    context: Context,
    cartItems: List<CartItem>,
    val updateAction: (CartItem) -> Unit
) : RecyclerListAdapter<CartItem, CartItemBinding, RecyclerViewHolder>(context, cartItems) {

    override fun layoutId() = R.layout.cart_item

    override fun bindData(binding: CartItemBinding, data: CartItem) {
        binding.apply {
            cartItem = data
            lineTotal = data.lineTotalDisplay
            lineTotalDesc = String.format("($%S X %S)", data.product.price, data.quantity)

            addIcon.setOnClickListener {
                updateAction(data)
            }
        }
    }
}