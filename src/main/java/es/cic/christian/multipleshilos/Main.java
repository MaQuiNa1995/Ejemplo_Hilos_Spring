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
package es.cic.christian.multipleshilos;

/**
 * @Autor cmunoz
 * @Fecha 26-jun-2017
 */
import es.cic.christian.configuracion.Configuracion;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Clase Main que inicia el ejemplo de Hilos
 *
 * @author Christian Muñoz Ason
 * @version 0.1.0
 * @since 1.8
 */
public class Main {

    /**
     * Log genérico de la clase
     */
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    /**
     * Tiempo que se espera para verificar cuantos hilos estan activos
     */ 
    private static final Long TIEMPO_ESPERA_HILOS = 1000L;

    /**
     * Método main que inicia la aplicación
     *
     * @param args Array de parámetros que se le pueden pasar al método
     */
    public static void main(String[] args) {

        ApplicationContext contexto;
        ThreadPoolTaskExecutor ejecutadorTareas;

        contexto = new AnnotationConfigApplicationContext(Configuracion.class);
        ejecutadorTareas = (ThreadPoolTaskExecutor) contexto.getBean("EjecutadorHilos");

        LOG.log(Level.INFO, "Creando Hilos");

        HiloDiezSegundos hiloDiez = (HiloDiezSegundos) contexto.getBean("HiloDiez");
        hiloDiez.setName("Hilo Diez");
        ejecutadorTareas.execute(hiloDiez);

        HiloCincoSegundos hiloCinco = (HiloCincoSegundos) contexto.getBean("HiloCinco");
        hiloCinco.setName("Hilo CincoF");
        ejecutadorTareas.execute(hiloCinco);

        do {

            int hilosActivos = ejecutadorTareas.getActiveCount();

            LOG.info(" ------------------- Hilos Activos: ".concat(String.valueOf(hilosActivos)).concat(" -------------------"));

            try {
                Thread.sleep(TIEMPO_ESPERA_HILOS);
            } catch (InterruptedException e) {
                LOG.warning("Error: ".concat(e.getMessage()));
            }

        } while (ejecutadorTareas.getActiveCount() != 0);

        LOG.info("Cerramos el Ejecutador De Hilos Porque No Quedan Hilos Activos");
        ejecutadorTareas.shutdown();

        System.exit(0);

    }
}
