package com.example;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.sql.*;

import javax.validation.Valid;


@EnableAutoConfiguration
@SpringBootApplication
@Controller
public class Example {

    @RequestMapping("/test")
    String home() {
        return "lol";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }
   
    @RequestMapping(value="/form_info",method = RequestMethod.GET)
    public String form_info(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        Etudiant etudiant=new Etudiant();
    	model.addAttribute("etudiant", etudiant);
    	return "form_info";
    }
    
    @RequestMapping("/form_souhait")
    public String form_souhait(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "form_souhait";
    }
    
    
    @RequestMapping(value = "/result", method = RequestMethod.POST)
	public String addEtudiant(@Valid Etudiant etudiant, BindingResult bindingResult, Model model) {
		model.addAttribute("nom", etudiant.getNom());
		model.addAttribute("prenom", etudiant.getPrenom());
		return "result";
	}
    
    
    
    public String connect(){
        try{
         String url="jdbc:mysql://172.28.2.10:3306/siteweb";
         Connection conn = DriverManager.getConnection(url,"root","MYSECRET");
             Statement stmt = conn.createStatement();
            ResultSet rs;
 
            rs = stmt.executeQuery("SELECT * from test");
            while ( rs.next() ) {
                String lastName = rs.getString("colonne1");
                return lastName;
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return e.getMessage();
        }
        return "erreur..";
    }

}
