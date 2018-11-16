/*
 * Copyright 2017 cmunoz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.cic.christian.hilos;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Autor cmunoz
 * @Fecha 26-jun-2017
 */
import es.cic.christian.configuracion.Configuracion;

/**
 * Clase Main que inicia el ejemplo de Hilos
 * 
 * @author Christian Muñoz Ason
 * @version 0.1.0
 * @since 1.8
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    private static final Long TIEMPO_ESPERA_HILOS = 1000L;
    private static final int NUMERO_HILOS = 31;

    /**
     * Método main que inicia la aplicación
     * 
     * @param args
     *            Array de parámetros que se le pueden pasar al método
     */
    public static void main(String[] args) {

	ThreadPoolTaskExecutor ejecutadorTareas;

	try (AnnotationConfigApplicationContext contexto = new AnnotationConfigApplicationContext(
		Configuracion.class)) {

	    ejecutadorTareas = (ThreadPoolTaskExecutor) contexto.getBean("EjecutadorHilos");

	    LOG.log(Level.INFO, "Creando {0} Hilos", NUMERO_HILOS);

	    for (int i = 1; i < NUMERO_HILOS; i++) {

		HiloSimple hiloSimple = (HiloSimple) contexto.getBean("HiloSimple");
		hiloSimple.setName("Hilo ".concat(String.valueOf(i)));
		ejecutadorTareas.execute(hiloSimple);

	    }

	    do {

		int hilosActivos = ejecutadorTareas.getActiveCount();

		LOG.info(" ------------------- Hilos Activos: ".concat(String.valueOf(hilosActivos))
			.concat(" -------------------"));

		try {
		    Thread.sleep(TIEMPO_ESPERA_HILOS);
		} catch (InterruptedException e) {
		    LOG.warning("Error: ".concat(e.getMessage()));
		}

	    } while (ejecutadorTareas.getActiveCount() != 0);
	}
	LOG.info("Cerramos el Ejecutador De Hilos Porque No Quedan Hilos Activos");
	ejecutadorTareas.shutdown();

	System.exit(0);

    }
}
