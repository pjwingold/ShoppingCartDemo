package au.com.pjwin.shoppingcartdemo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal

data class ProductResponse(val products: List<Product>)

data class Product(
    val id: Int,
    @SerializedName("product_name") val name: String,
    @SerializedName("description") val desc: String,
    val price: String,
    val discount: String
) : Serializable {

    var priceDecimal: BigDecimal = BigDecimal(0)
        private set

    var priceDisplay: String = ""
        private set

    fun updatePrice() {
        try {
            priceDecimal = BigDecimal(price)
            priceDisplay = String.format("$%S", priceDecimal)

        } catch (e: NumberFormatException) {
        }
    }
}