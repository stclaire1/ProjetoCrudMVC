package mvc_crud.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import mvc_crud.crud.dto.CidadaoDTO;

import java.util.ArrayList;
import java.util.List;

@Controller
public class crud {
    List<CidadaoDTO> cidadaos = new ArrayList<>();

    @GetMapping("cadastrar")
    public String home() {
        return "cadastro";
    }

    @PostMapping("cadastrar")
    public String cadastro(Long id, CidadaoDTO dto, Model model) {
        id = cidadaos.size() + 1L;
        dto.setId(id);
        cidadaos.add(dto);
        model.addAttribute("cidadaos", cidadaos);        

        return "redirect:/home";
    }

    @GetMapping("home")
    public String listagem(Model model){
        model.addAttribute("cidadaos", cidadaos);
        return "home";
    }

    @GetMapping("editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable("id") Long id, Model model) {
        CidadaoDTO cidadaoFind = cidadaos.stream().filter(cidadao -> id.equals(cidadao.getId())).findFirst().get();
        model.addAttribute("cidadaoFind", cidadaoFind);
        return "editar";
  }

    @PostMapping("editar/{id}")
        public String atualizarCidadao(@PathVariable("id") Long id, CidadaoDTO dto, Model model){
            for(CidadaoDTO cidadao : cidadaos){
                if(cidadao.getId().equals(id)){
                    cidadao.setNome(dto.getNome());
                    cidadao.setEmail(dto.getEmail());
                    cidadao.setEndereco(dto.getEndereco());
                    cidadao.setCelular(dto.getCelular());

                    model.addAttribute("cidadaos", cidadaos);
                    return "redirect:/home";
                }
            }
        return "redirect:/home";
        }

    @GetMapping("deletar/{id}")
        public String deletarUsuario(@PathVariable("id") Long id, Model model){
        CidadaoDTO cidadaoFind = cidadaos.stream().filter(cidadao -> id.equals(cidadao.getId())).findFirst().get();
        if (cidadaoFind != null) {
            cidadaos.remove(cidadaoFind);
        }
        return "redirect:/home";
    }
}
