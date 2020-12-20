package au.com.pjwin.shoppingcartdemo.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import au.com.pjwin.commonlib.ui.BaseFragment
import au.com.pjwin.shoppingcartdemo.R
import au.com.pjwin.shoppingcartdemo.databinding.FragmentProductDetailBinding
import au.com.pjwin.shoppingcartdemo.model.ProductResponse
import au.com.pjwin.shoppingcartdemo.util.Util
import au.com.pjwin.shoppingcartdemo.util.updateCartBadge
import au.com.pjwin.shoppingcartdemo.viewmodel.ProductViewModel

class ProductDetailFragment :
    BaseFragment<ProductResponse, ProductViewModel, FragmentProductDetailBinding>() {

    //todo add navarg
    override fun layoutId() = R.layout.fragment_product_detail

    override fun pageTitle() = R.string.product_detail_title

    override fun isFragmentObserver() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs = arguments?.let { ProductListFragmentArgs.fromBundle(it) }
        val product = safeArgs?.toProductDetail
        Log.d("fragment", "product $product")

        product?.let {
            binding.product = it

            binding.productInclude.addIcon.setOnClickListener {
                Util.openCartUpdateDialog(requireActivity(), product) { qty ->
                    viewModel.updateCart(product, qty)
                    getBottomNavView()?.updateCartBadge(viewModel.getTotalQuantity())
                }
            }
        }
    }
}