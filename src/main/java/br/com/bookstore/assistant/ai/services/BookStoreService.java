package br.com.bookstore.assistant.ai.services;


import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;


@Service
public class BookStoreService {

    private final ChatClient chatClient;

    public BookStoreService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public Mono<String> getBookstoreChat(String message) {
        return chatClient.callAsync(message);
    }

    public Mono<String> getBookstoreReview(String book) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                  Por favor, me forneça
                  um breve resumo do livro {book}
                  e também a biografia de seu autor.
                """);
        promptTemplate.add("book", book);
        return chatClient.callAsync(promptTemplate.create());
    }

    public Flux<String> getBookstoreChatStream(String message) {
        return chatClient.stream(message);
    }
}
