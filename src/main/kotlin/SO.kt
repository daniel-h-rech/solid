package rech.haeser.daniel.solid.o

import mu.KotlinLogging.logger
import rech.haeser.daniel.solid.model.*
import java.math.BigDecimal


open class OpenForModificationOrderService {

    private val logger = logger {}

    fun process(order: Order): Order {
        pay(order)
        preparePackage(order)
        return dispatchPackage(order)
    }

    protected fun pay(order: Order): Order {
        logger.info { "Paying order" }
        return order
    }

    protected fun preparePackage(order: Order): Order {
        logger.info { "Prepare new package" }
        return order
    }

    open fun dispatchPackage(order: Order): Order {
        logger.info { "Dispatch package" }
        return order
    }
}

class NewBrokenOrderService : OpenForModificationOrderService() {

    private val logger = logger {}

    override fun dispatchPackage(order: Order): Order {
        preparePackage(order)
        super.dispatchPackage(order)
        logger.info { "Processing extra dispatch step" }
        return order
    }
}

abstract class ClosedForModificationOrderService {

    private val logger = logger {}

    fun process(order: Order): Order {
        pay(order)
        preparePackage(order)
        return dispatchPackage(order)
    }

    private fun pay(order: Order): Order {
        logger.info { "Paying order" }
        return order
    }

    private fun preparePackage(order: Order): Order {
        logger.info { "Prepare new package" }
        return order
    }

    private fun dispatchPackage(order: Order): Order {
        logger.info { "Dispatch package" }
        processExtraDispatchStep(order)
        return order
    }

    abstract fun processExtraDispatchStep(order: Order): Order
}

class NewOrderService : ClosedForModificationOrderService() {

    private val logger = logger {}

    override fun processExtraDispatchStep(order: Order): Order {
        logger.info { "Processing extra dispatch step" }
        return order
    }
}

fun main() {
    val orderItem = OrderItem(
        product = Product(
            name = "A TV Model From Some Brand",
            sku = "123123",
        ),
        quantity = 1,
    )
    val order = Order(
        customer = Customer(
            username = "daniel",
            address = Address(12345)
        ),
        orderItems = listOf(orderItem),
        payment = Payment(
            PaymentType.CREDIT_CARD,
            BigDecimal("200.00"),
        ),
    )

    NewBrokenOrderService().process(order)

    NewOrderService().process(order)
}
