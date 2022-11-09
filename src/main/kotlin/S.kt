package rech.haeser.daniel.solid.s

import mu.KotlinLogging.logger
import rech.haeser.daniel.solid.model.*
import java.math.BigDecimal


class MultipleResponsibilityOrderService {

    private val logger = logger {}

    fun process(order: Order): Order {
        pay(order)
        preparePackage(order)
        dispatchPackage(order)
        return order
    }

    private fun pay(order: Order): Order {
        logger.info { "Paying: $order" }
        return order
    }

    private fun preparePackage(order: Order): Order {
        logger.info { "Prepare new package: $order" }
        return order
    }

    private fun dispatchPackage(order: Order): Order {
        logger.info { "Dispatch package: $order" }
        return order
    }
}

class PaymentService {

    private val logger = logger {}

    fun process(payment: Payment) {
        logger.info { "Paying: $payment" }
    }
}

class PackagingService {

    private val logger = logger {}

    fun process(address: Address, items: Collection<OrderItem>): OrderPackage {
        val orderPackage = OrderPackage(
            address = address,
            orderItems = items,
        )
        logger.info { "Prepare new package: $orderPackage" }
        return orderPackage
    }
}

class DispatchService {

    private val logger = logger {}

    fun process(orderPackage: OrderPackage): OrderPackage {
        logger.info { "Dispatch package: $orderPackage" }
        return orderPackage
    }
}

class SingleResponsibilityOrderService(
    private val dispatchService: DispatchService,
    private val packagingService: PackagingService,
    private val paymentService: PaymentService,
) {
    fun process(order: Order): Order {
        paymentService.process(order.payment)
        val orderPackage = packagingService.process(
            address = order.customer.address.copy(),
            items = order.orderItems
        )
        dispatchService.process(orderPackage)
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

    MultipleResponsibilityOrderService().process(order)

    SingleResponsibilityOrderService(
        DispatchService(),
        PackagingService(),
        PaymentService(),
    )
        .process(order)
}
