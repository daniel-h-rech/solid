package rech.haeser.daniel.solid.model

import java.math.BigDecimal

enum class PaymentType {
    BANK_TRANSFER,
    CREDIT_CARD,
}

data class Address(val zip: Int)

data class Customer(val username: String, val address: Address)

data class Payment(val paymentType: PaymentType, val amount: BigDecimal)

data class Product(val name: String, val sku: String)

data class Order(val customer: Customer, val orderItems: Collection<OrderItem>, val payment: Payment)

data class OrderItem(val product: Product, val quantity: Int)

data class OrderPackage(val orderItems: Collection<OrderItem>, val address: Address)
