package Controller;

import Service.AccountService;
import Service.MessageService;
import Model.Account;
import Model.Message;
import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postAccountHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{id}", this::getMessageByIDHandler);
        app.delete("/messages/{id}", this::deleteMessageByIDHandler);
        app.patch("/messages/{id}", this::updateMessageByIDHandler);
        app.get("/accounts/{id}/messages", this::getMessagesByAccountIDHandler);
        return app;
    }

    private void postAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount != null){
            ctx.json(mapper.writeValueAsString(addedAccount));
        }else{
            ctx.status(400);
        }
    }

    private void postLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account foundAccount = accountService.getAccountByUsername(account.getUsername());
        if(foundAccount != null && foundAccount.getPassword().equals(account.getPassword())){
            ctx.json(mapper.writeValueAsString(foundAccount));
        }else{
            ctx.status(401);
        }
    }

    private void postMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if(addedMessage != null){
            ctx.json(mapper.writeValueAsString(addedMessage));
        }else{
            ctx.status(400);
        }
    }

    private void getAllMessagesHandler(Context ctx) {
        ctx.json(messageService.getAllMessages());
    }

    private void getMessageByIDHandler(Context ctx) {
        Message message = messageService.getMessageByID(Integer.parseInt(ctx.pathParam("id")));
        if (message != null)
           ctx.json(message);
    }

    private void deleteMessageByIDHandler(Context ctx) {
        int result = messageService.deleteMessageByID(Integer.parseInt(ctx.pathParam("id")));
        if (result > 0)
            ctx.json(result);
    }

    private void updateMessageByIDHandler(Context ctx) {
        String message = "";
        if (ctx.body().length() > 18)
            message = ctx.body().substring(17, ctx.body().length()-1);
        int result = messageService.updateMessageByID(Integer.parseInt(ctx.pathParam("id")), message);
        if(result > 0){
            ctx.json(result);
        }else{
            ctx.status(400);
        }
    }

    private void getMessagesByAccountIDHandler(Context ctx) {
        ctx.json(messageService.getMessagesByAccountID(Integer.parseInt(ctx.pathParam("id"))));
    }

}