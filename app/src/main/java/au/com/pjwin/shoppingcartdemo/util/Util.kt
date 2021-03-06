package au.com.pjwin.shoppingcartdemo.util

import android.app.Activity
import android.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.LayoutInflater
import android.widget.TextView
import au.com.pjwin.shoppingcartdemo.R
import au.com.pjwin.shoppingcartdemo.databinding.BadgeLayoutBinding
import au.com.pjwin.shoppingcartdemo.databinding.DialogCartUpdateBinding
import au.com.pjwin.shoppingcartdemo.model.Product

object Util {

    fun openCartUpdateDialog(activity: Activity, product: Product, action: (Int) -> Unit) {
        if (activity.isDestroyed || activity.isFinishing) {
            return
        }
        val builder = AlertDialog.Builder(activity)
        val binding = DataBindingUtil.inflate<DialogCartUpdateBinding>(
            LayoutInflater.from(activity),
            R.layout.dialog_cart_update,
            null,
            true
        )
        binding.product = product
        binding.apply {
            qtyPicker.maxValue = 20
            qtyPicker.value = 1
        }

        builder.setView(binding.root)
        builder.setNegativeButton(R.string.button_cancel) { dialog, _ ->
            dialog.dismiss()
        }
        builder.setPositiveButton(R.string.button_ok) { dialog, _ ->
            action(binding.qtyPicker.value)
            dialog.dismiss()
        }
        builder.show()
    }
}

//todo separate file
fun BottomNavigationView.updateCartBadge(quantity: Int) {
    val menuView: BottomNavigationMenuView = getChildAt(0) as BottomNavigationMenuView
    val itemView: BottomNavigationItemView = menuView.getChildAt(1) as BottomNavigationItemView
    val badgeView = itemView.findViewById<TextView>(R.id.badge)

    if (quantity == 0) {
        if (badgeView != null) {
            itemView.removeView(badgeView)
        }

    } else {
        if (badgeView == null) {
            val binding =
                DataBindingUtil.inflate<BadgeLayoutBinding>(
                    LayoutInflater.from(context),
                    R.layout.badge_layout,
                    itemView,
                    false
                )
            binding.quantity = quantity.toString()
            itemView.addView(binding.root)

        } else {
            badgeView.text = quantity.toString()
        }
    }
}