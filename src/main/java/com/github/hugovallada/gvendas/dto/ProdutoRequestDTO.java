package com.github.hugovallada.gvendas.dto;

import com.github.hugovallada.gvendas.entidades.Categoria;
import com.github.hugovallada.gvendas.entidades.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Produto Request DTO")
public class ProdutoRequestDTO {

    @ApiModelProperty(value = "Descricao")
    @NotBlank(message = "A descrição não deve estar em branco")
    @Length(min = 3, max = 50, message = "A descrição deve ter entre 3 e 50 caracteres")
    private String descricao;


    @ApiModelProperty(value = "Quantidade")
    @NotNull(message = "A quantidade não pode ser nula")
    private Integer quantidade;

    @ApiModelProperty(value = "Preço Curto")
    @NotNull(message = "Indique o preço curto")
    private BigDecimal precoCurto;

    @ApiModelProperty(value = "Preço Venda")
    @NotNull(message = "Indique o valor de venda")
    private BigDecimal precoVenda;

    @ApiModelProperty(value = "Observação")
    @NotBlank(message = "A observação não pode ser nula")
    @Length(min = 3, max = 500, message = "A observação deve ter entre 3 e 500 caracteres")
    private String observacao;

    public Produto converterParaEntidade(Long codigoCategoria) {
        Produto produto = new Produto();
        produto.setDescricao(descricao);
        produto.setObservacao(observacao);
        produto.setPrecoCurto(precoCurto);
        produto.setPrecoVenda(precoVenda);
        produto.setQuantidade(quantidade);

        Categoria categoria = new Categoria();
        categoria.setCodigo(codigoCategoria);

        produto.setCategoria(categoria);

        return produto;
    }

    public Produto converterParaEntidade(Long codigoCategoria, Long codigo) {
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        produto.setDescricao(descricao);
        produto.setObservacao(observacao);
        produto.setPrecoCurto(precoCurto);
        produto.setPrecoVenda(precoVenda);
        produto.setQuantidade(quantidade);

        Categoria categoria = new Categoria();
        categoria.setCodigo(codigoCategoria);

        produto.setCategoria(categoria);

        return produto;
    }


}
