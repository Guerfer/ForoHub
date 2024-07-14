package com.aluracursos.ForoHub.topico;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

///// este record mapea la informacion que se envia (POST) desde insomnia //////
public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {

}
