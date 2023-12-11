package ar.edu.unju.edm.controlador;

import ar.edu.unju.edm.modelo.Usuario;
import ar.edu.unju.edm.servicio.UsuarioServicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PerfilController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/perfil/{id}")
    public String mostrarPerfil(@PathVariable("id") Long id, Model model) {
        Optional<Usuario> optionalUsuario = usuarioServicio.buscarPorId(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            model.addAttribute("usuario", usuario);
        } else {
            // Manejar el caso cuando el usuario no se encuentra
        }
        return "perfil";
    }



}
