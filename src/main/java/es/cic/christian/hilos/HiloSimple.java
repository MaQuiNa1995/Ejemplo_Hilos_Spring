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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <p>Clase hilo que lo único que hace es esperar dos Segundos y luego se cierra</p>
 * <p>'@Component("Nombre Del Bean")'</p>
 * <p>Indica a Spring que es candidato a ser manejado por el y a continuación le ponemos nombre</p>
 * <p>'@Scope("Tipo De Instancia")'</p>
 * <p>Indica a spring las veces que podría ser instanciado</p>
 * @see <a href="http://www.tutorialspoint.com/spring/spring_bean_scopes.htm">Documentación Scope</a>
 * @author cmunoz
 */
@Component("HiloSimple")
@Scope("prototype")
public class HiloSimple extends Thread {

    private static final Logger LOG = Logger.getLogger(HiloSimple.class.getName());
    private static final long TIEMPO_ESPERA_HILO = 2000L;

    /**
     * Método que se ejecuta cuando se inicia el hilo
     */
    @Override
    public void run() {

        LOG.log(Level.INFO, "Lanzando {0}", getName());

        try {

            sleep(TIEMPO_ESPERA_HILO);

        } catch (InterruptedException ie) {
            LOG.info("Se Interrumpió El ".concat(ie.getMessage()));
        }

        LOG.log(Level.INFO, "Terminó La Ejecución De: {0}", getName());
    }

}
