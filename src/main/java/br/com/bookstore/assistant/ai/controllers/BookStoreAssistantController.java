package br.com.bookstore.assistant.ai.controllers;

import br.com.bookstore.assistant.ai.services.BookStoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/bookstore")
public class BookStoreAssistantController {
    private final BookStoreService bookstoreService;

    public BookStoreAssistantController(BookStoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping("/informations")
    public ResponseEntity<String> bookstoreChat(@RequestParam(value = "message",
            defaultValue = "Quais são os livros de TI que estão em alta ultimamente?") String message) {

        // Retorna a mensagem de "aguardando resposta"
        String waitingMessage = "Aguardando resposta...";

        // Retorna a mensagem imediatamente
        ResponseEntity<String> responseEntity = new ResponseEntity<>(waitingMessage, HttpStatus.OK);

        // Realiza a consulta de forma assíncrona
        bookstoreService.getBookstoreChat(message)
                .subscribe(this::handleSuccess, this::handleError);

        return responseEntity;
    }

    @GetMapping("/reviews")
    public ResponseEntity<String> bookstoreReview(@RequestParam(value = "book", defaultValue = "Código Limpo") String book) {

        // Retorna a mensagem de "aguardando resposta"
        String waitingMessage = "Aguardando resposta...";

        // Retorna a mensagem imediatamente
        ResponseEntity<String> responseEntity = new ResponseEntity<>(waitingMessage, HttpStatus.OK);

        // Realiza a consulta de forma assíncrona
        bookstoreService.getBookstoreReview(book)
                .subscribe(this::handleSuccess, this::handleError);

        return responseEntity;
    }

    @GetMapping("/stream/informations")
    public Flux<String> bookstoreChatStream(@RequestParam(value = "message",
            defaultValue = "Quais são os livros best sellers dos ultimos anos?") String message) {
        // Realiza a consulta de forma assíncrona e retorna o fluxo diretamente
        return bookstoreService.getBookstoreChatStream(message)
                .doOnSubscribe(subscription -> System.out.println("Consulta iniciada..."))
                .doOnError(error -> System.err.println("Erro durante a consulta: " + error.getMessage()))
                .doOnComplete(() -> System.out.println("Consulta concluída."));
    }

    private void handleSuccess(String result) {
        // Manipular o resultado da consulta bem-sucedida aqui
        System.out.println("Consulta bem-sucedida: " + result);
    }

    private void handleError(Throwable error) {
        // Manipular erros ocorridos durante a consulta aqui
        System.err.println("Erro durante a consulta: " + error.getMessage());
    }

    //    @GetMapping("/informations")
//    public ChatResponse bookstoreChatEx2(@RequestParam(value = "message",
//            defaultValue = "Quais são os livros best sellers dos ultimos anos?") String message){
//        return chatClient.call(new Prompt(message));
//    }

}

