package cl.praxis.ExamenFinal.restController;

import cl.praxis.ExamenFinal.model.entities.Comanda;
import cl.praxis.ExamenFinal.model.services.ComandaService;
import cl.praxis.ExamenFinal.model.services.GarzonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/inicio")
public class ListadoRestController {

    private final ComandaService comandaService;
    private final GarzonService garzonService;

    public ListadoRestController(ComandaService comandaService, GarzonService garzonService) {
        this.comandaService = comandaService;
        this.garzonService = garzonService;
    }

    private HttpStatus status = HttpStatus.OK;

    @GetMapping
    private ResponseEntity<Object> filter(
            @RequestParam(value = "garzonRut", required = false) String garzonRut,
            @RequestParam(value = "comandaId", required = false) String comandaId
    ) {
        try {
            if ((garzonRut == null || garzonRut.trim().isEmpty()) &&
                    (comandaId == null || comandaId.trim().isEmpty())) {

                List<Comanda> comandas = comandaService.findAll();
                return ResponseEntity.ok(comandas);
            } else {
                if (garzonRut != null && !garzonRut.trim().isEmpty()) {
                    if (!isValidRut(garzonRut)) {
                        return ResponseEntity.badRequest().body("Ingrese un RUT válido en el formato 11111111-1.");
                    }

                    List<Comanda> filteredComandasByRut = comandaService.findAllByGarzonRut(garzonRut);
                    if (filteredComandasByRut.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron comandas para el RUT proporcionado.");
                    }

                    if (comandaId != null && !comandaId.trim().isEmpty()) {
                        try {
                            int id = Integer.parseInt(comandaId);
                            Optional<Comanda> comanda = filteredComandasByRut.stream()
                                    .filter(c -> c.getId() == id)
                                    .findFirst();
                            if (comanda.isPresent()) {
                                return ResponseEntity.ok(comanda.get());
                            } else {
                                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                        .body("No se encontró la comanda con el ID proporcionado.");
                            }
                        } catch (NumberFormatException e) {
                            return ResponseEntity.badRequest().body("El identificador de comanda debe ser un número entero.");
                        }
                    } else {
                        return ResponseEntity.ok(filteredComandasByRut);
                    }
                } else if (comandaId != null && !comandaId.trim().isEmpty()) {
                    try {
                        int id = Integer.parseInt(comandaId);
                        Optional<Comanda> comanda = comandaService.findById(id);
                        if (comanda.isPresent()) {
                            return ResponseEntity.ok(comanda.get());
                        } else {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body("No se encontró la comanda con el ID proporcionado.");
                        }
                    } catch (NumberFormatException e) {
                        return ResponseEntity.badRequest().body("El identificador de comanda debe ser un número entero.");
                    }
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al procesar la búsqueda: " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error desconocido.");
    }

    private boolean isValidRut(String rut) {
        // Expresión regular para validar el formato del RUT
        String rutPattern = "\\d{8}-[kK0-9]";
        return Pattern.matches(rutPattern, rut);
    }
}
