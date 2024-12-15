package cl.praxis.ExamenFinal.controller;

import cl.praxis.ExamenFinal.model.entities.Comanda;
import cl.praxis.ExamenFinal.model.services.ComandaService;
import cl.praxis.ExamenFinal.model.services.GarzonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/inicio")
public class ListadoController {

    private final ComandaService comandaService;
    private final GarzonService garzonService;

    public ListadoController(ComandaService comandaService, GarzonService garzonService) {
        this.comandaService = comandaService;
        this.garzonService = garzonService;
    }

    @GetMapping
    public String findAll(@RequestParam(value = "garzonRut", required = false) String garzonRut,
                          @RequestParam(value = "comandaId", required = false) String comandaId,
                          Model model) {
        List<Comanda> comandas = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        if ((garzonRut == null || garzonRut.trim().isEmpty()) &&
                (comandaId == null || comandaId.trim().isEmpty())) {
            comandas = comandaService.findAll();
        } else {
            try {
                if (garzonRut != null && !garzonRut.trim().isEmpty()) {
                    if (!isValidRut(garzonRut)) {
                        errors.add("Ingrese un RUT válido, con formato 11111111-1.");
                    } else {
                        List<Comanda> filteredComandas = comandaService.findAllByGarzonRut(garzonRut);
                        if (filteredComandas.isEmpty()) {
                            errors.add("No se encontraron comandas para el rut proporcionado.");
                        } else {
                            comandas.addAll(comandaService.findAllByGarzonRut(garzonRut));
                        }
                    }
                }
                if (comandaId != null && !comandaId.trim().isEmpty()) {
                    try {
                        int id = Integer.parseInt(comandaId);
                        Optional<Comanda> comanda = comandaService.findById(id);
                        comanda.ifPresent(comandas::add);
                    } catch (NumberFormatException e) {
                        errors.add("El identificador de comanda debe ser un número del 1 al 20.");
                    }
                }
                if (garzonRut == null && comandaId == null) {
                    comandas = comandaService.findAll();
                }
            } catch (Exception e) {
                errors.add("Ocurrió un error al procesar la búsqueda: " + e.getMessage());
            }
        }

        model.addAttribute("comandas", comandas);
        model.addAttribute("garzones", garzonService.findAll());
        model.addAttribute("errors", errors);

        return "listadoList";
    }

    private boolean isValidRut(String rut) {
        // regex para formato rut
        String rutPattern = "\\d{8}-[kK0-9]";
        return Pattern.matches(rutPattern, rut);
    }

}
