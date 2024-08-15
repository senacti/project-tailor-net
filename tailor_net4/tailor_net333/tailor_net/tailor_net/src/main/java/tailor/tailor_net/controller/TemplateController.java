package tailor.tailor_net.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {


    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // Nombre de la vista en la carpeta templates sin la extensión .html
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }


}

