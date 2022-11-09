package rech.haeser.daniel.solid.d

import mu.KotlinLogging


class SomeFeatureService(val kafkaMessageSender: KafkaMessageSender) {
    fun process() {
        // ...
        kafkaMessageSender.sendMessage("Some payload")
    }
}

class KafkaMessageSender : MessageSender {

    private val logger = KotlinLogging.logger {}

    override fun sendMessage(payload: String) {
        // do send the message
        logger.info { "Message sent" }
    }
}

/*
 * And below we apply the pattern
 */

interface MessageSender {
    fun sendMessage(payload: String)
}

class SomeDependencyInvertedFeatureService(val messageSender: MessageSender) {
    fun doSomething() {
        messageSender.sendMessage("Some payload")
    }
}

fun main() {
    SomeFeatureService(KafkaMessageSender()).process()

    SomeDependencyInvertedFeatureService(KafkaMessageSender()).doSomething()
}
