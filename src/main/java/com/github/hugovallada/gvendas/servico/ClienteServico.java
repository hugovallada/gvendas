package com.github.hugovallada.gvendas.servico;

import com.github.hugovallada.gvendas.entidades.Cliente;
import com.github.hugovallada.gvendas.excecao.RegraNegocioException;
import com.github.hugovallada.gvendas.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class ClienteServico {

    private ClienteRepositorio repositorio;

    @Autowired
    public ClienteServico(ClienteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Cliente> buscarTodos() {
        return repositorio.findAll();
    }

    public Optional<Cliente> buscarPorCodigo(Long codigo) {
        return repositorio.findById(codigo);
    }

    public Cliente salvar(Cliente cliente) {
        validarClienteDuplicado(cliente);
        return repositorio.save(cliente);
    }

    public Cliente atualizar(Long codigo, Cliente cliente) {
        Cliente clienteExiste = validarClienteExiste(codigo);
        validarClienteDuplicado(cliente);

        copyProperties(cliente, clienteExiste, "codigo");

        return repositorio.save(clienteExiste);
    }

    public void deletar(Long codigo) {
        repositorio.deleteById(codigo);
    }

    private void validarClienteDuplicado(Cliente cliente) {
        Optional<Cliente> clienteOptional = repositorio.findByNome(cliente.getNome());

        if (clienteOptional.isPresent() && cliente.getCodigo() != clienteOptional.get().getCodigo()) {
            throw new RegraNegocioException("Já existe um cliente cadastrado com esse nome");
        }
    }

    private Cliente validarClienteExiste(Long codigo) {
        Optional<Cliente> cliente = buscarPorCodigo(codigo);

        if (cliente.isEmpty()) throw new EmptyResultDataAccessException(1);

        return cliente.get();
    }
}
