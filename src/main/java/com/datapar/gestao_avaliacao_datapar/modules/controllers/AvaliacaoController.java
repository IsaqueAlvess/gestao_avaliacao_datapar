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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Página inicial")
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("mensagem", "");
        return "index";
    }

    @Operation(summary = "Página de login")
    @GetMapping("/login")
    public String logar() {
        return "login";
    }

    @Operation(summary = "Página de cadastro")
    @GetMapping("/cadastro")
    public String cadastrar() {
        return "cadastrar";
    }

    @Operation(summary = "Salvar avaliação")
    @PostMapping("/submit")
    public String saveAvaliacao(@ModelAttribute("avaliacao") Avaliacao avaliacao, Model model) {
        var result = avaliacaoService.saveAvaliacao(avaliacao);
        model.addAttribute("mensagem", result);
        return "index";
    }

    @Operation(summary = "Salvar usuário")
    @PostMapping("/saveUsuario")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "E-mail já está em uso")
    })
    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        try {
            usuarioService.saveUsuario(usuario);
            return "redirect:/";
        } catch (IllegalStateException e) {
            model.addAttribute("mensagemErro", "Este e-mail já está em uso. Por favor, escolha outro.");
            return "cadastrar";
        }
    }

    @Operation(summary = "Listar avaliações")
    @GetMapping("/resultados")
    public ModelAndView listarAvaliacoes() {
        ModelAndView modelAndView = new ModelAndView("resultados");
        List<Avaliacao> listaDeAvaliacoes = avaliacaoService.getAllAvaliacoes();
        modelAndView.addObject("listaDeAvaliacoes", listaDeAvaliacoes);

        // Calcula a média das pontuações
        double[] averages = new double[6]; // Índice 0 não é usado
        int[] counts = new int[6]; // Conta o número de avaliações para cada pontuação
        for (Avaliacao avaliacao : listaDeAvaliacoes) {
            int pontuacao = avaliacao.getPontuacao();
            averages[pontuacao] += pontuacao;
            counts[pontuacao]++;
        }
        for (int i = 1; i <= 5; i++) {
            if (counts[i] != 0) {
                averages[i] /= counts[i];
            }
        }
        modelAndView.addObject("averages", averages);

        return modelAndView;
    }

    @Operation(summary = "Listar avaliações")
    @GetMapping("/getAvaliacoes")
    public String mostrarResultados(Model model) {
        List<Avaliacao> avaliacoes = avaliacaoService.getAllAvaliacoes();
        model.addAttribute("listAvaliacoes", avaliacoes);
        return "resultados";
    }

    @Operation(summary = "Efetuar login")
    @PostMapping("/efetuarLogin")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "302", description = "Login bem-sucedido"),
        @ApiResponse(responseCode = "400", description = "Credenciais inválidas")
    })
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
