package com.parameta.service;

import com.parameta.dto.request.EmpleadoRequest;
import com.parameta.dto.response.EmpleadoResponse;

public interface EmpleadoService {

    EmpleadoResponse crearEmpleado(EmpleadoRequest request);

}