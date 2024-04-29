package com.example.mspedido.feign;

import com.example.mspedido.dto.ProductoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalogo-service",path = "/categoria")
public interface CatalogoFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "productoListarPorIdCB", fallbackMethod = "fallBackProducto")
    public ResponseEntity<ProductoDto> listById(@PathVariable(required = true) Integer id);
    default ResponseEntity<ProductoDto> fallbackProducto(Integer id, Exception e){
        return ResponseEntity.ok(new ProductoDto());
    }
}
