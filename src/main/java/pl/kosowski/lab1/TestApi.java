package pl.kosowski.lab1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RestController
public class TestApi {
    private int countUser = 0;
    private int countAdmin = 0;

    @GetMapping("/forLogout")
    public String forLogout(){
        return "Papa";
    }

    @GetMapping("/forNobody")
    public String forNobody(Principal principal){
        if (principal != null) {
            return "Hello: " + principal.getName();
        }
        else return "Cześć nieznajomy";
    }

    @GetMapping("/forAdmin")
    public String forAdmin(Principal principal){
        return "Cześć admin: " + principal.getName()+ "    Ilość zalogowań: " + countAdmin;
    }

    @GetMapping("/forUser")
    public String forUser(Principal principal){
        return "Cześć user: " + principal.getName()+ "     Ilość zalogowań: " + countUser;
    }

    @GetMapping("/count")
    public void Counter(Principal principal, HttpServletResponse response) throws IOException {
        if (principal.getName().equals("mcnuel")) {
            countAdmin++;
            response.sendRedirect("/forAdmin");
        } else if (principal.getName().equals("diuki")) {
            countUser++;
            response.sendRedirect("/forUser");
        }
    }
}