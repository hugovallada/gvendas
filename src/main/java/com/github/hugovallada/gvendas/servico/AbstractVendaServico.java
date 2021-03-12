package com.github.hugovallada.gvendas.servico;

import com.github.hugovallada.gvendas.dto.ItemVendaResponseDTO;
import com.github.hugovallada.gvendas.dto.VendaResponseDTO;
import com.github.hugovallada.gvendas.entidades.ItemVenda;
import com.github.hugovallada.gvendas.entidades.Venda;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractVendaServico {

    protected VendaResponseDTO converterParaVendaDto(Venda venda, List<ItemVenda> itemVendaList) {
        List<ItemVendaResponseDTO> itensVenda = itemVendaList
                .stream()
                .map(this::converterParaItemVendaDto)
                .collect(Collectors.toList());

        return new VendaResponseDTO(venda.getCodigo(), venda.getData(), itensVenda);

    }

    protected ItemVendaResponseDTO converterParaItemVendaDto(ItemVenda itemVenda) {
        return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(), itemVenda.getProduto().getCodigo(), itemVenda.getProduto().getDescricao());
    }
}
