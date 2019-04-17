package au.com.pjwin.shoppingcartdemo.ui

import android.content.Context
import au.com.pjwin.commonlib.ui.adapter.ListClickListener
import au.com.pjwin.commonlib.ui.adapter.RecyclerListAdapter
import au.com.pjwin.commonlib.ui.adapter.RecyclerViewHolder
import au.com.pjwin.shoppingcartdemo.R
import au.com.pjwin.shoppingcartdemo.databinding.ProductItemBinding
import au.com.pjwin.shoppingcartdemo.model.Product

class ProductAdapter(
    context: Context,
    productList: List<Product>,
    onClickListener: ListClickListener<Product>,
    val updateAction: (Product) -> Unit
) :
    RecyclerListAdapter<Product, ProductItemBinding, RecyclerViewHolder>(context, productList, onClickListener) {

    override fun layoutId() = R.layout.product_item

    override fun bindData(
        binding: ProductItemBinding,
        data: Product
    ) {
        binding.apply {
            product = data
            price = String.format("$%S", data.price)
            addIcon.setOnClickListener {
                updateAction(data)
            }
        }
    }
}