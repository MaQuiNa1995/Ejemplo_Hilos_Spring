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
package es.cic.christian.tareas;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Clase que muestra un mensaje cada dos segundos
 *
 * @author Christian Muñoz Ason
 * @version 0.1.0
 * @since 1.8
 * @see
 * <a href="http://howtodoinjava.com/spring/spring-core/4-ways-to-schedule-tasks-in-spring-3-scheduled-example/">Documentación
 * Schedule</a>
 */
@Scope("singleton")
@Component("EjecutadorTarea")
public class Tarea {

    private static final int SEGUNDOS_TAREA=2000;
    private static final Logger LOG = Logger.getLogger(Tarea.class.getName());

    //@Scheduled(fixedRate = 2000)
    @Scheduled(fixedDelay = SEGUNDOS_TAREA)
    public void tareaProgramada() {
        LOG.info("Me Ejecuto Cada "
                .concat(String.valueOf(SEGUNDOS_TAREA/1000))
                .concat(" Segundos Mostrándote: ")
                .concat(new Date().toString()));
    }

    public void ejecutar() {

    }
}
