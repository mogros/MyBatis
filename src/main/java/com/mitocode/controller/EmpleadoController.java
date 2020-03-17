package com.mitocode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mitocode.model.Empleado;
import com.mitocode.service.EmpleadoService;
import com.mitocode.service.TipoEmpleadoService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private TipoEmpleadoService tipoEmpleadoService;

	@GetMapping
	public String listarEmpleados(Model model) {
		model.addAttribute("listaEmpleados", empleadoService.obtenerEmpleados());
		return "empleados/empleados";
	}

	@RequestMapping("/nuevo")
	public String empleadoView(Model model) {

		model.addAttribute("empleado", new Empleado());
		model.addAttribute("listaTipos", tipoEmpleadoService.obtenerTipos());

		return "empleados/nuevo";
	}

}
