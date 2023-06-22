package nusiss.project.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MaintenanceRedirect {

  @RequestMapping({ "/success", "/cancelled" })
  public String index() {
    return "forward:/index.html";
  }
}
