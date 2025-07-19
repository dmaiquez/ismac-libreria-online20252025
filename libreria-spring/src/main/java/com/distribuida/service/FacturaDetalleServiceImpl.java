package com.distribuida.service;

import com.distribuida.dao.FacturaDetalleRepository;
import com.distribuida.model.FacturaDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service  // es un bean de logica de negocio
public class FacturaDetalleServiceImpl implements FacturaDetalleService{

    @Autowired // anotación para INYECCIÓN DE DEPENDENCIAS
    private FacturaDetalleRepository facturaDetalleRepository;


    @Override
    public List<FacturaDetalle> findAll() {
        return facturaDetalleRepository.findAll();
    }

    @Override
    public FacturaDetalle findOne(int id) {

        Optional<FacturaDetalle> facturaDetalle = facturaDetalleRepository.findById(id);

        return facturaDetalle.orElse(null);
    }

    @Override
    public FacturaDetalle save(FacturaDetalle facturaDetalle) {
        return facturaDetalleRepository.save(facturaDetalle);
    }

    @Override
    public FacturaDetalle update(int id, FacturaDetalle facturaDetalle) {

        FacturaDetalle facturaDetalleExistente = findOne(id);

        if(facturaDetalleExistente == null){
            return null;
        }

        facturaDetalleExistente.setCantidad(facturaDetalle.getCantidad());
        facturaDetalleExistente.setSubtotal(facturaDetalle.getSubtotal());
        facturaDetalleExistente.setFactura(facturaDetalle.getFactura());
        facturaDetalleExistente.setLibro(facturaDetalle.getLibro());


        return facturaDetalleRepository.save(facturaDetalleExistente);
    }

    @Override
    public void delete(int id) {
        if(facturaDetalleRepository.existsById(id)){
            facturaDetalleRepository.deleteById(id);
        }
    }
}
