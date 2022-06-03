import static spark.Spark.*;



public class Aplicacao {
	
	private static UserService userService = new UserService();
	
    public static void main(String[] args) {
        port(6789);

        post("/user", (request, response) -> userService.add(request, response));

        get("/user/:email", (request, response) -> userService.get(request, response));

        get("/user/update/:email", (request, response) -> userService.update(request, response));

        get("/user/delete/:email", (request, response) -> userService.remove(request, response));

        get("/user", (request, response) -> userService.getAll(request, response));
               
    }
}