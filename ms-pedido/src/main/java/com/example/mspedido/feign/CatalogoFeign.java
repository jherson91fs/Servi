package com.example.mspedido.feign;

import com.example.mspedido.dto.ProductoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalogo-service",path = "/categoria")
public interface CatalogoFeign {
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> listById(@PathVariable(required = true) Integer id);
}
