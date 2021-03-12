package com.github.hugovallada.gvendas.servico;

import com.github.hugovallada.gvendas.dto.ClienteVendaResponseDTO;
import com.github.hugovallada.gvendas.dto.ItemVendaRequestDTO;
import com.github.hugovallada.gvendas.dto.VendaRequestDTO;
import com.github.hugovallada.gvendas.dto.VendaResponseDTO;
import com.github.hugovallada.gvendas.entidades.Cliente;
import com.github.hugovallada.gvendas.entidades.ItemVenda;
import com.github.hugovallada.gvendas.entidades.Produto;
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
public class VendaServico extends AbstractVendaServico {

    private ClienteServico clienteServico;
    private ItemVendaRepositorio itemVendaRepositorio;
    private VendaRepositorio vendaRepositorio;
    private ProdutoServico produtoServico;

    @Autowired
    public VendaServico(ClienteServico clienteServico, VendaRepositorio vendaRepositorio, ItemVendaRepositorio itemVendaRepositorio, ProdutoServico produtoServico) {
        this.clienteServico = clienteServico;
        this.vendaRepositorio = vendaRepositorio;
        this.itemVendaRepositorio = itemVendaRepositorio;
        this.produtoServico = produtoServico;
    }

    public ClienteVendaResponseDTO listarVendaPorCliente(Long codigoCliente) {
        Cliente cliente = validarClienteVendaExiste(codigoCliente);

        List<VendaResponseDTO> vendas = vendaRepositorio.findByClienteCodigo(codigoCliente)
                .stream()
                .map(venda -> converterParaVendaDto(venda, itemVendaRepositorio.findByVendaCodigo(venda.getCodigo())))
                .collect(Collectors.toList());

        return new ClienteVendaResponseDTO(cliente.getNome(), vendas);

    }

    public ClienteVendaResponseDTO listarVendaPorCodigo(Long codigoVenda) {
        Venda venda = validarVendaExiste(codigoVenda);
        return new ClienteVendaResponseDTO(venda.getCliente().getNome(), Arrays.asList(converterParaVendaDto(venda, itemVendaRepositorio.findByVendaCodigo(venda.getCodigo()))));
    }

    public ClienteVendaResponseDTO salvar(Long codigoCliente, VendaRequestDTO venda) {
        Cliente cliente = validarClienteVendaExiste(codigoCliente);
        validarProdutoExiste(venda.getItensVendaDto());

        Venda vendaSalva = salvarVenda(cliente, venda);

        return new ClienteVendaResponseDTO(
                vendaSalva.getCliente().getNome(),
                Arrays.asList(converterParaVendaDto(vendaSalva, itemVendaRepositorio.findByVendaCodigo(vendaSalva.getCodigo())))
        );
    }

    private Venda salvarVenda(Cliente cliente, VendaRequestDTO dto) {
        Venda vendaSalva = vendaRepositorio.save(new Venda(dto.getData(), cliente));
        dto.getItensVendaDto()
                .stream()
                .map(itemVendaRequestDTO -> converterParaItemVenda(itemVendaRequestDTO, vendaSalva))
                .forEach(itemVendaRepositorio::save);

        return vendaSalva;

    }

    private ItemVenda converterParaItemVenda(ItemVendaRequestDTO dto, Venda venda) {
        ItemVenda itemVenda = new ItemVenda();
        Produto produto = new Produto();

        produto.setCodigo(dto.getCodigoProduto());

        itemVenda.setVenda(venda);
        itemVenda.setQuantidade(dto.getQuantidade());
        itemVenda.setPrecoVendido(dto.getPrecoVendido());
        itemVenda.setProduto(produto);

        return itemVenda;
    }

    private void validarProdutoExiste(List<ItemVendaRequestDTO> itensVendaDto) {
        itensVendaDto.forEach(item -> produtoServico.validarProdutoExiste(item.getCodigoProduto()));
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
}
