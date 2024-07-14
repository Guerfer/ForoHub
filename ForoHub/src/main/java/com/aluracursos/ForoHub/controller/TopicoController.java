package com.aluracursos.ForoHub.controller;

import com.aluracursos.ForoHub.errores.ResourceNotFoundException;
import com.aluracursos.ForoHub.topico.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    ////// ---- Creacion de topicos ---- //////
    @PostMapping
    public ResponseEntity<String> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        if (topicoRepository.existsByTitulo(datosRegistroTopico.titulo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El Tópico ya está registrado con este título");
        }
        topicoRepository.save(new Topico(datosRegistroTopico));
        return ResponseEntity.status(HttpStatus.CREATED).body("Tópico creado exitosamente");
    }

    ////// ---- Consultar todos los topicos ---- //////
    @GetMapping
    public List<DatosListadoTopico> listaTopicos(){
        //// ordena en la pagina solo los 10 resultado por fecha de creacion ////
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("fechaDeCreacion").ascending());
        return topicoRepository.findAllByOrderByFechaDeCreacionAsc(pageRequest)///// ordena de forma ascendente /////
                .stream()
                .map(DatosListadoTopico::new)
                .toList();
    }

    ////// ---- Consultar un topicos por ID ---- //////
    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> obtenerTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Tópico con el ID " + id + " no se encuentra en la base de datos"));
        return ResponseEntity.ok(new DatosListadoTopico(topico));
    }


    ////// ---- Actualizar un topicos ---- //////
    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.id() == null) {
            throw new IllegalArgumentException("El campo de ID no puede estar vacio");
        }
        try {
            Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
            topico.actualizarDatos(datosActualizarTopico);
            return ResponseEntity.ok(new DatosRegistroTopico(topico.getTitulo(), topico.getMensaje(),
                    topico.getAutor(), topico.getCurso()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El Tópico no se encuentra registrado con este ID " + datosActualizarTopico.id());
        }

    }


    ////// ---- Eliminar un topicos ---- //////
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarTopico(@PathVariable Long id) {
        try {
            Optional<Topico> optionalTopico = topicoRepository.findById(id);
            if (optionalTopico.isPresent()) {
                topicoRepository.delete(optionalTopico.get());
                return ResponseEntity.status(HttpStatus.OK).body("Tópico eliminado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Tópico no se encuentra registrado con este ID");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el tópico");
        }
    }

}
