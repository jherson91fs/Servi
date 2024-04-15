package com.example.mspedido.service.impl;

import com.example.mspedido.dto.ClienteDto;
import com.example.mspedido.entity.Pedido;
import com.example.mspedido.entity.PedidoDetalle;
import com.example.mspedido.feign.CatalogoFeign;
import com.example.mspedido.feign.ClienteFeign;
import com.example.mspedido.repository.PedidoRepository;
import com.example.mspedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceimpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteFeign clienteFeign;
    @Autowired
    private CatalogoFeign catalogoFeign;
    @Override
    public List<Pedido> listar(){
        return pedidoRepository.findAll();
    }
    @Override
    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    @Override
    public Pedido actualizar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    @Override
    public Optional<Pedido> listarPorId(Integer id){
        Optional<Pedido> pedido =pedidoRepository.findById(id);
        ClienteDto clienteDto = clienteFeign.listById(pedido.get().getClienteId()).getBody();
        for (PedidoDetalle pedidoDetalle : pedido.get().getDetalle()){
            pedidoDetalle.setProductoDto(catalogoFeign.listById(pedidoDetalle.getProductoId()).getBody());
        }
//        List<PedidoDetalle> pedidoDetalles = pedido.get().getDetalle().stream().map(pedidoDetalle -> {
//           pedidoDetalle.setProductoDto(catalogoFeign.listById(pedidoDetalle.getProductoId()).getBody());
//           return pedidoDetalle;
//        }).toList();
        pedido.get().setClienteDto(clienteDto);
//        pedido.get().setDetalle(pedidoDetalles);
        return pedidoRepository.findById(id);
    }
    @Override
    public void eliminarPorId(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
