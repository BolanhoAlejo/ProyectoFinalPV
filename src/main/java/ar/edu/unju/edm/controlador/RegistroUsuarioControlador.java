package ar.edu.unju.edm.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.edm.controlador.dto.UsuarioRegistroDTO;
import ar.edu.unju.edm.modelo.Usuario;
import ar.edu.unju.edm.servicio.UsuarioServicio;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

	private UsuarioServicio usuarioServicio;

	public RegistroUsuarioControlador(UsuarioServicio usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}
	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
	    Usuario usuarioExistente = usuarioServicio.findByEmail(registroDTO.getEmail());
	    if (usuarioExistente != null) {
	        return "redirect:/registro?error";
	    }

	    usuarioServicio.guardar(registroDTO);
	    return "redirect:/registro?exito";
	}

}
