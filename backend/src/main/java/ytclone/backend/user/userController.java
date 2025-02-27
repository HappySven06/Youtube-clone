package ytclone.backend.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @GetMapping("/getUser")
    public String getUser(){
        return "This is the user controller";
    }
}
