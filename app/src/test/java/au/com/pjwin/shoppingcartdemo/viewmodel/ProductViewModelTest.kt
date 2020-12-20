package au.com.pjwin.shoppingcartdemo.viewmodel

import au.com.pjwin.shoppingcartdemo.ShoppingCartBaseTest
import au.com.pjwin.shoppingcartdemo.model.Product
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class ProductViewModelTest : ShoppingCartBaseTest() {

    //Spyk has to be read/writable, cannot be val
    private var viewModel = spyk<ProductViewModel>(recordPrivateCalls = true)

    @Before
    override fun init() {
        every { viewModel.onData(any()) } just runs
        every { viewModel.onError(any()) } just runs
    }

    @Test
    fun `test Get Products`() {
        viewModel.getProducts()
        verify(exactly = 1) { viewModel.onData(any()) }

        var product: Product? = viewModel.findProductById(1)
        assertEquals(product!!.id, 1)
        product = viewModel.findProductById(5)
        assertEquals(product!!.name, "Leica M10")
        assertNull(viewModel.findProductById(13))
    }

    @Test
    fun `test Update Cart`() {
        val product1 = Product(1, "pro 1", "desc 1", "100", "")
        val product2 = Product(2, "pro 2", "desc 2", "200", "")
        val product3 = Product(3, "pro 3", "desc 3", "300", "")

        viewModel.updateCart(product1, 1)
        viewModel.updateCart(product2, 2)
        assertEquals(3, viewModel.getTotalQuantity())
        assertEquals(BigDecimal(500), viewModel.getTotalCost())//100+400

        viewModel.updateCart(product3, 4)//3+4
        assertEquals(7, viewModel.getTotalQuantity())
        assertEquals(viewModel.getTotalCost(), BigDecimal(1700))//1200+500

        viewModel.updateCart(product2, 0)
        assertEquals(viewModel.getTotalQuantity(), 5)//7-2
        assertEquals(viewModel.getTotalCost(), BigDecimal(1300))//1700-400

        viewModel.updateCart(product2, 10)
        viewModel.updateCart(product2, 3)
        assertEquals(viewModel.getTotalQuantity(), 8)//5+3
        assertEquals(viewModel.getTotalCost(), BigDecimal(1900))//1300+600

        viewModel.updateCart(product1, 0)
        viewModel.updateCart(product2, 0)
        viewModel.updateCart(product3, 0)
        assertEquals(viewModel.getTotalQuantity(), 0)
        assertEquals(viewModel.getTotalCost(), BigDecimal(0))
    }
}