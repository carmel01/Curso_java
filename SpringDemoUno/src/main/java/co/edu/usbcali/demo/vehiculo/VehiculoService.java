package co.edu.usbcali.demo.vehiculo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VehiculoService {

	private final static Logger log = LoggerFactory.getLogger(VehiculoService.class);
	private static VehiculoService vehiculoService = null;

	private VehiculoService() {

	}

	public static VehiculoService createInstance() {
		if (vehiculoService == null) {
			vehiculoService = new VehiculoService();
		}
		log.info("Creo el VehiculoService");
		return vehiculoService;
	}

}
