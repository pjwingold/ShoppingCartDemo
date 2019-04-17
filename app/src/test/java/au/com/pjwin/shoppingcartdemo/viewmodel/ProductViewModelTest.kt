package au.com.pjwin.shoppingcartdemo.viewmodel

import au.com.pjwin.shoppingcartdemo.ShoppingCartBaseTest
import au.com.pjwin.shoppingcartdemo.model.Product
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class ProductViewModelTest : ShoppingCartBaseTest() {

    //Spyk has to be read/writable, cannot be val
    //@SpyK
    private var viewModel = spyk<ProductViewModel>(recordPrivateCalls = true)

    @Before
    override fun init() {
        every { viewModel.onData(any()) } just runs
        every { viewModel.onError(any()) } just runs
    }

    @Test
    fun testGetProducts() {
        viewModel.getProducts()

        verify(exactly = 1) { viewModel.onData(any()) }
    }

    @Test
    fun testUpdateCart() {
        val product1 = Product(1, "pro 1", "desc 1", "100", "")
        val product2 = Product(2, "pro 1", "desc 1", "200", "")

        viewModel.updateCart(product1, 1)
        viewModel.updateCart(product2, 2)
        assertEquals(viewModel.getTotalQuantity(), 3)
        assertEquals(viewModel.getTotalCost(), BigDecimal(500))

        viewModel.updateCart(product2, 0)
        assertEquals(viewModel.getTotalQuantity(), 1)
        assertEquals(viewModel.getTotalCost(), BigDecimal(100))

        viewModel.updateCart(product2, 10)
        viewModel.updateCart(product2, 3)
        assertEquals(viewModel.getTotalQuantity(), 4)
        assertEquals(viewModel.getTotalCost(), BigDecimal(700))

        viewModel.updateCart(product1, 0)
        viewModel.updateCart(product2, 0)
        assertEquals(viewModel.getTotalQuantity(), 0)
        assertEquals(viewModel.getTotalCost(), BigDecimal(0))
    }

    @Test
    fun testFindProductById() {
        val list = mutableListOf<Product>()
        list.add(Product(1, "pro 1", "desc 1", "100", ""))
        list.add(Product(2, "pro 2", "desc 2", "100", ""))
        list.add(Product(3, "pro 3", "desc 3", "100", ""))
        list.add(Product(4, "pro 4", "desc 4", "100", ""))
        list.add(Product(5, "pro 5", "desc 5", "100", ""))

        every { viewModel.productList } answers {
            fieldValue = list
            list
        }
        //viewModel.productList = list

        //viewModel.productList
        viewModel.findProductById(3)
        assertNotNull(viewModel.findProductById(1))
        assertNotNull(viewModel.findProductById(5))
        assertNull(viewModel.findProductById(6))
        /*verify {
            viewModel.findProductById(any())
        }*/

    }
}