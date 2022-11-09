package rech.haeser.daniel.solid.i

import mu.KotlinLogging


interface IDoEverything {
    fun sendMessage(data: String)

    fun storeInFilesystem(data: String)

    fun writeToDatabase(data: String)
}

class MessageService : IDoEverything {

    private val logger = KotlinLogging.logger {}

    override fun sendMessage(data: String) {
        // send the message
        logger.info { "Message sent" }
    }

    override fun storeInFilesystem(data: String) {
        // Do nothing
    }

    override fun writeToDatabase(data: String) {
        // Do nothing
    }
}

/*
 * And below we apply the pattern
 */

interface MessageSender {
    fun send(data: String)
}

class NewMessageService : MessageSender {

    private val logger = KotlinLogging.logger {}

    override fun send(data: String) {
        // send the message
        logger.info { "Message sent" }
    }

}

fun main() {
    MessageService().sendMessage("Some data")

    NewMessageService().send("Some more data")
}
