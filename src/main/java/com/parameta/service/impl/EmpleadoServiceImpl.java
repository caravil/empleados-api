package com.parameta.service.impl;

import com.parameta.dto.request.EmpleadoRequest;
import com.parameta.dto.response.EmpleadoResponse;
import com.parameta.entity.Empleado;
import com.parameta.exception.BusinessException;
import com.parameta.mapper.EmpleadoMapper;
import com.parameta.repository.EmpleadoRepository;
import com.parameta.service.EmpleadoService;
import com.parameta.util.EmpleadoCalculator;
import com.parameta.validator.EmpleadoValidator;

import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

        private final EmpleadoRepository empleadoRepository;

        public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
                this.empleadoRepository = empleadoRepository;
        }

        @Override
        public EmpleadoResponse crearEmpleado(EmpleadoRequest request) {

                EmpleadoValidator.validar(request);

                if (empleadoRepository
                                .findByNumeroDocumento(
                                                request.getNumeroDocumento())
                                .isPresent()) {

                        throw new BusinessException(
                                        "Ya existe un empleado con ese número de documento.");
                }

                Empleado empleado = EmpleadoMapper.toEntity(request);

                Empleado empleadoGuardado = empleadoRepository.save(empleado);

                String edad = EmpleadoCalculator.calcularEdad(
                                empleadoGuardado.getFechaNacimiento());

                String tiempoVinculacion = EmpleadoCalculator.calcularTiempoVinculacion(
                                empleadoGuardado.getFechaVinculacion());

                return EmpleadoMapper.toResponse(
                                empleadoGuardado,
                                edad,
                                tiempoVinculacion);
        }
}
