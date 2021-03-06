package com.jugarjuntos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jugarjuntos.Entities.UsuarioDetalles.CustomUserDetails;
import com.jugarjuntos.Exceptions.BusinessException;
import com.jugarjuntos.ServiciosAplicacion.SAAnuncio;
import com.jugarjuntos.ServiciosAplicacion.SAParticipacion;
import com.jugarjuntos.Transfers.TParticipacion;

@Controller
public class JugarJuntosController {

	@Autowired
	SAAnuncio saAnuncio;
	
	@Autowired 
	SAParticipacion saParticipacion;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("anuncios", saAnuncio.getAllAnuncios());
		
		return "index";
	}
	
	@GetMapping("/verSolicitudesDeAcceso")
	public String verSolicitudes(Model model, @RequestParam long id){
		model.addAttribute("solicitudes", saParticipacion.solicitudesPendientes(id));
		return "solicitudes";
	}
	
	@PostMapping("/enviarSolicitud")
	public String enviarSolicitud(Model model, RedirectAttributes redirAttrs, @RequestParam long id_anuncio, @RequestParam String text) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long idUsuario = ((CustomUserDetails) principal).getId();
		TParticipacion participacion = new TParticipacion(idUsuario, id_anuncio, null, text);
		
		int res = saParticipacion.enviarSolicitud(participacion);
		System.out.println(res);
		if (res == 0)
			redirAttrs.addFlashAttribute("success", "Se ha enviado la solicitud correctamente.");
		else if (res == -1)
			redirAttrs.addFlashAttribute("error", "Error. El usuario o el anuncio no son válidos");
		else if (res == -2)
			redirAttrs.addFlashAttribute("error", "Error. El anuncio ha sido finalizado o está en juego");
		else if (res == -3)
			redirAttrs.addFlashAttribute("error", "Error. Se ha alcanzado el número máximo de jugadores.");
		else
			redirAttrs.addFlashAttribute("error", "Error al unirse al anuncio");

		redirAttrs.addAttribute("id", id_anuncio);
		return "redirect:/detalles";
	}
	

	@PostMapping("/aceptarSolicitud")
	public String aceptarSolicitud(Model model,RedirectAttributes redirAttrs, @RequestParam long idAnuncio, @RequestParam long idUsuario) {
		TParticipacion participacion = new TParticipacion();
		participacion.setEstado("pendiente");
		participacion.setId_anuncio(idAnuncio);
		participacion.setId_usuario(idUsuario);
		try {
			saParticipacion.aceptarSolicitud(participacion);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		redirAttrs.addAttribute("id", idAnuncio);
		return "redirect:/detalles";
	}
	
	@PostMapping("/rechazarSolicitud")
	public String rechazarSolicitud(Model model, RedirectAttributes redirAttrs, @RequestParam String idAnuncio, @RequestParam String idUsuario) {
		TParticipacion participacion = new TParticipacion();
		participacion.setId_anuncio(Long.parseLong(idAnuncio));
		participacion.setId_usuario(Long.parseLong(idUsuario));
		try {
			saParticipacion.rechazarSolicitud(participacion);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		redirAttrs.addAttribute("id", idAnuncio);
		return "redirect:/detalles";
	}
	
}
