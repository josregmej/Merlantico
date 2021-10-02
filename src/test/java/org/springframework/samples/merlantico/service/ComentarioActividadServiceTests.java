package org.springframework.samples.merlantico.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.merlantico.model.Actividad;
import org.springframework.samples.merlantico.model.ComentarioActividad;
import org.springframework.samples.merlantico.service.ActividadService;
import org.springframework.samples.merlantico.service.ComentarioActividadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class ComentarioActividadServiceTests {                
        @Autowired
	protected ComentarioActividadService comentarioActividadService;
        @Autowired
    protected ActividadService actividadService;
    
    //Prueba H9+E1 - Alta de un comentario
	@Test
	@Transactional
	public void shouldInsertComentarioActividad() {
		
		Actividad actividad = new Actividad();
		actividad.setNombre("Escalada");
		actividad.setDescripcion("Buena ruta de escala con amigos");
		actividad.setValoracion(4);
		actividad.setDireccion("Sierra de Grazalema");
		actividad.setProvincia("Sevilla");
		actividad.setPrecio(2);
		this.actividadService.saveActividad(actividad);
		assertThat(actividad.getId()).isNotEqualTo(0);
		
		//A continuacion creamos el comentario de la actividad"
		ComentarioActividad comentario=new ComentarioActividad();
		comentario.setActividad(actividad);
		comentario.setMensaje("Bueno");
		comentario.setPuntuacion(6);
		this.comentarioActividadService.saveComentario(comentario);
		assertThat(comentario.getId()).isNotEqualTo(0);
    }
	
	//Prueba H9-E1 - Alta de un comentario sin mensaje y sin puntuacion
	@Test
	@Transactional
	public void shouldInsertComentarioActividadVacio() {
		
		ComentarioActividad comentario=new ComentarioActividad();
		comentario.setMensaje("Mejor hotel calidad precio");
		comentario.setPuntuacion(null);
        assertThat(comentario.getPuntuacion()).isNull();
    }
}
