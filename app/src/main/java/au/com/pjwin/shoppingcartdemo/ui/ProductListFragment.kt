package au.com.pjwin.shoppingcartdemo.ui

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.navigation.findNavController
import au.com.pjwin.commonlib.ui.BaseFragment
import au.com.pjwin.commonlib.ui.adapter.ListClickListener
import au.com.pjwin.shoppingcartdemo.R
import au.com.pjwin.shoppingcartdemo.databinding.FragmentProductListBinding
import au.com.pjwin.shoppingcartdemo.model.Product
import au.com.pjwin.shoppingcartdemo.model.ProductResponse
import au.com.pjwin.shoppingcartdemo.util.Util
import au.com.pjwin.shoppingcartdemo.util.updateCartBadge
import au.com.pjwin.shoppingcartdemo.viewmodel.ProductViewModel
import com.google.android.gms.common.internal.service.Common

class ProductListFragment :
    BaseFragment<ProductResponse, ProductViewModel, FragmentProductListBinding>() {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var productList: List<Product>

    override fun layoutId() = R.layout.fragment_product_list

    override fun isFragmentObserver() = false

    override fun pageTitle() = R.string.app_name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productList = listOf()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProducts()

        binding.productList.apply {
            setHasFixedSize(true)
            addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    context,
                    androidx.recyclerview.widget.RecyclerView.VERTICAL
                )
            )
            productAdapter =
                ProductAdapter(context, productList, object : ListClickListener<Product> {
                    override fun onClick(data: Product) {
                        findNavController().navigate(
                            R.id.productDetail,
                            ProductListFragmentArgs.Builder(data).build().toBundle()
                        )
                    }
                }) { product ->
                    Util.openCartUpdateDialog(activity!!, product) { qty ->
                        viewModel.updateCart(product, qty)
                        getBottomNavView()?.updateCartBadge(viewModel.getTotalQuantity())
                    }
                }

            binding.adapter = productAdapter
        }
    }

    override fun onData(data: ProductResponse?) {
        data?.let {
            productList = it.products
            productAdapter.list = it.products
            binding.adapter = productAdapter
        }
    }
}