package com.distribuida.service;

import com.distribuida.dao.FacturaRepository;
import com.distribuida.model.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service  // es un bean de logica de negocio
public class FacturaServiceImpl implements FacturaService{

    @Autowired // anotación para INYECCIÓN DE DEPENDENCIAS
    private FacturaRepository facturaRepository;


    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura findOne(int id) {

        Optional<Factura> factura = facturaRepository.findById(id);

        return factura.orElse(null);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura update(int id, Factura factura) {

        Factura facturaExistente = findOne(id);

        if(facturaExistente == null){
            return null;
        }

        facturaExistente.setNumFactura(factura.getNumFactura());
        facturaExistente.setFecha(factura.getFecha());
        facturaExistente.setTotalNeto(factura.getTotalNeto());
        facturaExistente.setIva(factura.getIva());
        facturaExistente.setTotal(factura.getTotal());
        facturaExistente.setCliente(factura.getCliente());

        return facturaRepository.save(facturaExistente);
    }

    @Override
    public void delete(int id) {
        if(facturaRepository.existsById(id)){
            facturaRepository.deleteById(id);
        }
    }
}
