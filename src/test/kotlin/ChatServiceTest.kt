import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun createChat() {
        ChatService.createChat(1, Message("Hello"))
        assertEquals(1, ChatService.getLastMessages().size)
    }

    @Test
    fun addMessage() {
        ChatService.createChat(1, Message("Hello"))
        ChatService.addMessage(1, Message("How are you?"))
        assertEquals(1, ChatService.getLastMessages().size)
    }

    @Test
    fun deleteMessage() {
        ChatService.createChat(1, Message("Hello"))
        ChatService.addMessage(1, Message("How are you?"))
        ChatService.deleteMessage(1, 1)
        assertEquals(1, ChatService.getLastMessages().size)
    }

    @Test
    fun deleteChat() {
        ChatService.createChat(1, Message("Hello"))
        ChatService.deleteChat(1)
        assertEquals("Hi", ChatService.getLastMessages()[0])
    }

    @Test
    fun getUnreadChatsCount() {
        ChatService.createChat(1, Message("Hello"))
        ChatService.createChat(2, Message("Hi"))
        ChatService.addMessage(1, Message("How are you?"))
        assertEquals(2, ChatService.getUnreadChatsCount())
    }

    @Test
    fun getLastMessages() {
        ChatService.createChat(1, Message("Hello"))
        ChatService.addMessage(1, Message("How are you?"))
        assertEquals("How are you?", ChatService.getLastMessages()[0])
    }

    @Test
    fun getMessages() {
        ChatService.createChat(1, Message("Hello"))
        ChatService.addMessage(1, Message("How are you?"))
        assertEquals(1, ChatService.getMessages(1, 1).size)
    }
}