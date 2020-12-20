package au.com.pjwin.shoppingcartdemo

import androidx.annotation.VisibleForTesting
import au.com.pjwin.commonlib.Common
import au.com.pjwin.shoppingcartdemo.BuildConfig

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
open class TestConfig : Common.Config {

    override fun host() = "0.0.0.0"

    override fun port(): Int = 8081

    override fun readTimeout(): Long = 5

    override fun schema(): String = "http"

    override fun connectionTimeout(): Long = 5

    override fun debug(): Boolean = BuildConfig.DEBUG
}