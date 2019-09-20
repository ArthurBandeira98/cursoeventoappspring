package com.cursoeventoappspring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cursoeventoappspring.model.Convidado;
import com.cursoeventoappspring.model.Evento;
import com.cursoeventoappspring.repository.ConvidadoRepository;
import com.cursoeventoappspring.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository er;

	@Autowired
	private ConvidadoRepository cr;

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarEvento";
		}

		er.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento cadastrado!");
		return "redirect:/cadastrarEvento";

	}

	// Retorna a lista de eventos
	@RequestMapping("/evento")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;

	}

	// Retorna os detalhes do evento
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);

		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);
		System.out.println("evento: " + evento);
		return mv;
	}

	// Salva o convidado no banco
	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado,
			BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		} else {

			Evento evento = er.findByCodigo(codigo);
			convidado.setEvento(evento);
			cr.save(convidado);
			attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
			return "redirect:/{codigo}";
		}
	}

	// deleta o evento
	@RequestMapping(value = "/deletarEvento")
	public String deletarEvento(long codigo) {
		Evento evento = er.findByCodigo(codigo);
		er.delete(evento);
		return "redirect:/evento";
	}

	// deleta convidado
	@RequestMapping(value = "/deletarConvidado")
	public String deletarConvidado(long rg) {
		Convidado convidado = cr.findByRg(rg);
		cr.delete(convidado);
		Evento evento = convidado.getEvento();
		long codigoLong = evento.getCodigo();
		return "redirect:/" + codigoLong;

	}

}
