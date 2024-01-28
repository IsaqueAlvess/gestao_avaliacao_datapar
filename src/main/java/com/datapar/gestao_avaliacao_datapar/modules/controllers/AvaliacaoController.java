package com.datapar.gestao_avaliacao_datapar.modules.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.datapar.gestao_avaliacao_datapar.modules.models.Avaliacao;
import com.datapar.gestao_avaliacao_datapar.modules.models.Usuario;
import com.datapar.gestao_avaliacao_datapar.modules.services.AvaliacaoService;
import com.datapar.gestao_avaliacao_datapar.modules.services.UsuarioService;


@Controller
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;
    
    @Autowired
    private UsuarioService usuarioService;
   

    @GetMapping("/")
    public String viewHomePage(Model model){   
        model.addAttribute("mensagem", "");     
        return "index";
    }

    @GetMapping("/login")
    public String logar() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastrar() {
        return "cadastrar";
    }    

    @PostMapping("/submit")
    public String saveAvaliacao(@ModelAttribute("avaliacao") Avaliacao avaliacao, Model model){

        var result = avaliacaoService.saveAvaliacao(avaliacao); 

        model.addAttribute("mensagem", result);

        return "index";
    }

    @PostMapping("/saveUsuario")
    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario, Model model){
        try {
            usuarioService.saveUsuario(usuario);          
            return "redirect:/";
        } catch (IllegalStateException e) {
            model.addAttribute("mensagemErro", "Este e-mail já está em uso. Por favor, escolha outro.");
            return "cadastrar";
        }
    }

    @GetMapping("/resultados")
    public ModelAndView listarAvaliacoes() {
        ModelAndView modelAndView = new ModelAndView();
        List<Avaliacao> listaDeAvaliacoes = avaliacaoService.getAllAvaliacoes();
        modelAndView.addObject("listaDeAvaliacoes", listaDeAvaliacoes);
        modelAndView.setViewName("resultados");
        return modelAndView;
    }

    @GetMapping("/getAvaliacoes")
    public String mostrarResultados(Model model) {
        List<Avaliacao> avaliacoes = avaliacaoService.getAllAvaliacoes();
        model.addAttribute("listAvaliacoes", avaliacoes);
        return "resultados";
    }
        

    @PostMapping("/efetuarLogin")
    public String efetuarLogin(@RequestParam("email") String email, @RequestParam("senha") String senha, Model model) {
        Usuario usuario = usuarioService.getUsuarioByEmail(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            return "redirect:/resultados";
        } else {
            model.addAttribute("error_message", "Credenciais inválidas!");
            return "login";    
        }
    }
    
}
