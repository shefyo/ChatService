data class Message(val text: String, var read: Boolean = false)

data class Chat(
    val messages: MutableList<Message> = mutableListOf()
)

class NoChatException : Exception()

object ChatService {
    private val chats = mutableMapOf<Int, Chat>()
    fun addMessage(userId: Int, message: Message) {
        chats.getOrPut(userId) { Chat() }.messages += message
    }

    fun createChat(userId: Int, message: Message) {
        chats[userId] = Chat(mutableListOf(message))
    }

    fun deleteChat(userId: Int) {
        chats.remove(userId)
    }

    fun deleteMessage(userId: Int, messageIndex: Int) {
        chats[userId]?.messages?.removeAt(messageIndex)
    }

    fun getUnreadChatsCount() = chats.asSequence()
        .filter { it.value.messages.any { !it.read } }.count()

    fun getLastMessages() = chats.asSequence()
        .map { it.value.messages.lastOrNull()?.text ?: "No messages" }.toList()

    fun getMessages(userId: Int, count: Int) = chats[userId]?.let { chat ->
        chat.messages.takeLast(count).onEach { it.read = true }
    } ?: throw NoChatException()

    fun printChats() = println(chats)
}

fun main() {
    ChatService.createChat(1, Message("Hi"))
    ChatService.addMessage(1, Message("How are you?"))
    ChatService.addMessage(3, Message("Bye"))
    ChatService.addMessage(4, Message("Hello"))
    ChatService.printChats()
    ChatService.deleteMessage(1, 0)
    ChatService.deleteChat(3)
    ChatService.printChats()
    println(ChatService.getUnreadChatsCount())
    println(ChatService.getLastMessages())
    println(ChatService.getMessages(1, 1))
}