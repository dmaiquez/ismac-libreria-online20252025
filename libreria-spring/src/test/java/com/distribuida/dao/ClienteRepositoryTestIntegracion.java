package com.distribuida.dao;

import com.distribuida.model.Cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class ClienteRepositoryTestIntegracion {


    @Autowired
    private ClienteRepository clienteRepository;


    @Test
    public void findAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);
        for(Cliente item: clientes){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Cliente> cliente = clienteRepository.findById(1);
        assertTrue(cliente.isPresent());
        System.out.println(cliente.toString());
    }


    @Test
    public void save(){
        Cliente cliente = new Cliente(0,"1701234567","Juan","Taipe","Av. por ahi.", "0987654321","jtaipe@correo.com");
        Cliente clienteGuardado = clienteRepository.save(cliente);
        assertNotNull(clienteGuardado);
        assertEquals("Juan",clienteGuardado.getNombre());
        assertEquals("Taipe",clienteGuardado.getApellido());

    }

    @Test
    public void update(){
        Optional<Cliente> cliente = clienteRepository.findById(41);
        assertTrue(cliente.isPresent(),"El cliente no id = 41 deberia existir");
        cliente.orElse(null).setCedula("1701234522");
        cliente.orElse(null).setNombre("Juan22");
        cliente.orElse(null).setApellido("Taipe22");
        cliente.orElse(null).setDireccion("Av 2222");
        cliente.orElse(null).setTelefono("09876542222");
        cliente.orElse(null).setCorreo("jtaipe22@correo.com");
        Cliente clienteActualizado = clienteRepository.save(cliente.orElse(null));
        assertNotNull(clienteActualizado);
        assertEquals("Juan22",clienteActualizado.getNombre());
        assertEquals("Taipe22",clienteActualizado.getApellido());
    }

    @Test
    public void delete(){
        if(clienteRepository.existsById(41)){
            clienteRepository.deleteById(41);
        }
        assertFalse(clienteRepository.existsById(41),"El id=41 deberia haberse eliminado.");
    }

}
