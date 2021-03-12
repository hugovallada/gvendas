package com.github.hugovallada.gvendas.servico;

import com.github.hugovallada.gvendas.dto.ClienteVendaResponseDTO;
import com.github.hugovallada.gvendas.dto.ItemVendaResponseDTO;
import com.github.hugovallada.gvendas.dto.VendaResponseDTO;
import com.github.hugovallada.gvendas.entidades.Cliente;
import com.github.hugovallada.gvendas.entidades.ItemVenda;
import com.github.hugovallada.gvendas.entidades.Venda;
import com.github.hugovallada.gvendas.excecao.RegraNegocioException;
import com.github.hugovallada.gvendas.repositorio.ItemVendaRepositorio;
import com.github.hugovallada.gvendas.repositorio.VendaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaServico {

    private ClienteServico clienteServico;
    private ItemVendaRepositorio itemVendaRepositorio;
    private VendaRepositorio vendaRepositorio;

    @Autowired
    public VendaServico(ClienteServico clienteServico, VendaRepositorio vendaRepositorio, ItemVendaRepositorio itemVendaRepositorio) {
        this.clienteServico = clienteServico;
        this.vendaRepositorio = vendaRepositorio;
        this.itemVendaRepositorio = itemVendaRepositorio;
    }

    public ClienteVendaResponseDTO listarVendaPorCliente(Long codigoCliente) {
        Cliente cliente = validarClienteVendaExiste(codigoCliente);

        List<VendaResponseDTO> vendas = vendaRepositorio.findByClienteCodigo(codigoCliente)
                .stream()
                .map(this::converterParaVendaDto)
                .collect(Collectors.toList());

        return new ClienteVendaResponseDTO(cliente.getNome(), vendas);

    }

    public ClienteVendaResponseDTO listarVendaPorCodigo(Long codigoVenda) {
        Venda venda = validarVendaExiste(codigoVenda);
        return new ClienteVendaResponseDTO(venda.getCliente().getNome(), Arrays.asList(converterParaVendaDto(venda)));
    }

    private Venda validarVendaExiste(Long codigoVenda) {
        Optional<Venda> venda = vendaRepositorio.findById(codigoVenda);

        if (venda.isEmpty()) {
            throw new RegraNegocioException(String.format("Venda de código %s não encontrada", codigoVenda));
        }

        return venda.get();

    }

    private Cliente validarClienteVendaExiste(Long codigoCliente) {
        Optional<Cliente> clienteOpt = clienteServico.buscarPorCodigo(codigoCliente);
        if (clienteOpt.isEmpty()) {
            throw new RegraNegocioException(String.format("O cliente de código %s não existe no cadastro", codigoCliente));
        }

        return clienteOpt.get();

    }

    private VendaResponseDTO converterParaVendaDto(Venda venda) {
        List<ItemVendaResponseDTO> itensVenda = itemVendaRepositorio.findByVendaCodigo(venda.getCodigo())
                .stream()
                .map(this::converterParaItemVendaDto)
                .collect(Collectors.toList());

        return new VendaResponseDTO(venda.getCodigo(), venda.getData(), itensVenda);

    }

    private ItemVendaResponseDTO converterParaItemVendaDto(ItemVenda itemVenda) {
        return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(), itemVenda.getProduto().getCodigo(), itemVenda.getProduto().getDescricao());
    }
}
