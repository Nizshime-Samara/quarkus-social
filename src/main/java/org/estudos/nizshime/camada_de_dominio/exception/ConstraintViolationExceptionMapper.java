package org.estudos.nizshime.camada_de_dominio.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.validation.ConstraintViolationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
        private static final Logger LOGGER = LoggerFactory.getLogger(ConstraintViolationExceptionMapper.class);

        @Override
        public Response toResponse(ConstraintViolationException exception) {
                LOGGER.error("Violação de constraint na validação: {}",
                                exception.getConstraintViolations());

                List<ErrorMessage> errors = exception.getConstraintViolations().stream()
                                .map(violation -> {
                                        String field = violation.getPropertyPath().toString();
                                        String fieldName = field.substring(field.lastIndexOf('.') + 1);
                                        return new ErrorMessage(fieldName, violation.getMessage());
                                })
                                .collect(Collectors.toList());

                return Response.status(Response.Status.BAD_REQUEST)
                                .entity(errors)
                                .build();
        }
}
