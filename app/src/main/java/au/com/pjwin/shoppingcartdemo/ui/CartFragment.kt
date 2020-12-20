package au.com.pjwin.shoppingcartdemo.ui

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import au.com.pjwin.commonlib.ui.BaseFragment
import au.com.pjwin.shoppingcartdemo.R
import au.com.pjwin.shoppingcartdemo.databinding.FragmentCartBinding
import au.com.pjwin.shoppingcartdemo.model.CartItem
import au.com.pjwin.shoppingcartdemo.model.ProductResponse
import au.com.pjwin.shoppingcartdemo.util.Util
import au.com.pjwin.shoppingcartdemo.util.updateCartBadge
import au.com.pjwin.shoppingcartdemo.viewmodel.ProductViewModel

class CartFragment :
    BaseFragment<ProductResponse, ProductViewModel, FragmentCartBinding>() {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartList: List<CartItem>

    override fun layoutId() = R.layout.fragment_cart

    override fun pageTitle() = R.string.cart_title

    override fun isFragmentObserver() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartList = listOf()
        cartAdapter = CartAdapter(requireContext(), cartList) {
            val product = it.product
            Util.openCartUpdateDialog(requireActivity(), product) { qty ->
                viewModel.updateCart(product, qty)
                updateDisplay()
                getBottomNavView()?.updateCartBadge(viewModel.getTotalQuantity())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateDisplay()
        binding.apply {
            cartItems.addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    context,
                    androidx.recyclerview.widget.RecyclerView.VERTICAL
                )
            )
            adapter = cartAdapter
        }
    }

    private fun updateDisplay() {
        cartList = viewModel.getCartItemList()
        binding.apply {
            totalQuantity = viewModel.getTotalQuantity()
            totalCost = String.format("$%S", viewModel.getTotalCost().toString())
            cartAdapter.list = cartList
        }
    }
}