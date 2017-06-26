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
package es.cic.christian.configuracion;

import java.util.concurrent.Executor;
import java.util.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * <p>
 * Clase De Configuración De La Aplicación
 * </p>
 *
 * @author cmunoz
 * <p>
 * '@EnableAsync,'@EnableScheduling'
 * </p><p>
 * Indica a Spring que la programación de tareas va a estar activa
 * </p><p>
 * '@Configuration'
 * </p><p>
 * Indica a Spring que esta clase se usara de contexto para la definición de
 * beans
 * </p><p>
 * '@ComponentScan(basePackages = "es.cic.christian")'
 * </p><p>
 * Indica a Spring donde buscar las clases a las que hagas referencia en Beans
 * </p><p>
 * @see
 * <a href="https://stackoverflow.com/questions/1878806/what-is-the-difference-between-corepoolsize-and-maxpoolsize-in-the-spring-thread">Preguntas
 * Relacionadas SO</a>
 * </p>
 */
@EnableAsync
@EnableScheduling
@Configuration
@ComponentScan(basePackages = "es.cic.christian")
public class Configuracion {

    private static final Logger LOG
            = Logger.getLogger(Configuracion.class.getName());

    // Está por defecto a Integer.MAX_VALUE no haría falta hacerlo
    private static final int HILOS_MAXIMOS_COLA = Integer.MAX_VALUE;
    private static final int HILOS_MAXIMOS_USADOS = 6;
    private static final int HILOS_MAXIMOS_PERMITIDOS = 16;

    // ------------------- Manejador De Hilos -------------------
    @Bean(name = "EjecutadorHilos")
    public ThreadPoolTaskExecutor taskExecutor() {

        LOG.info(
                "Creando ThreadPoolTaskExecutor con:"
                .concat("\n - CorePoolSize: ").concat(String.valueOf(HILOS_MAXIMOS_USADOS))
                .concat("\n - MaxPoolSize: ").concat(String.valueOf(HILOS_MAXIMOS_PERMITIDOS))
                .concat("\n - QueueCapacity: ").concat(String.valueOf(HILOS_MAXIMOS_COLA))
        );

        ThreadPoolTaskExecutor poolConexiones = new ThreadPoolTaskExecutor();

        poolConexiones.setThreadNamePrefix("Maqui-");
        
        poolConexiones.setCorePoolSize(HILOS_MAXIMOS_USADOS);
        poolConexiones.setMaxPoolSize(HILOS_MAXIMOS_PERMITIDOS);
        poolConexiones.setQueueCapacity(HILOS_MAXIMOS_COLA);
        poolConexiones.setWaitForTasksToCompleteOnShutdown(true);

        return poolConexiones;
    }

    // ------------------- Manejador De Tareas -------------------
    @Bean(name = "EjecutadorTareas")
    public Executor taskScheduler() {
        LOG.info("Creando Manejador De Tareas");
        return new ThreadPoolTaskScheduler();
    }

}
