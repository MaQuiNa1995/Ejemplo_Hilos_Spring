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
package es.cic.christian.ejemplohilos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Clase De Configuración De La Aplicación
 *
 * @author cmunoz
 *
 * <p>
 * @Configuration
 * </p><p>
 * Indica a Spring que esta clase se usara de contexto para la
 * definición de beans
 * </p><p>
 * @ComponentScan(basePackages = "es.cic.christian")
 * </p><p>
 * Indica a Spring donde buscar las clases a las que hagas referencia en Beans
 * </p>
 */
@Configuration
@ComponentScan(basePackages = "es.cic.christian")
public class Configuracion {

    private static final int HILOS_MAXIMOS_USADOS = 8;
    private static final int HILOS_MAXIMOS_PERMITIDOS = 10;

    @Bean(name = "EjecutadorTask")
    public ThreadPoolTaskExecutor taskExecutor() {

        ThreadPoolTaskExecutor poolConexiones = new ThreadPoolTaskExecutor();

        poolConexiones.setCorePoolSize(HILOS_MAXIMOS_USADOS);
        poolConexiones.setMaxPoolSize(HILOS_MAXIMOS_PERMITIDOS);
        poolConexiones.setWaitForTasksToCompleteOnShutdown(true);

        return poolConexiones;
    }

}
