package br.com.gadoboi.api.infrastructure.adapter.out.persistence.mapper;

import br.com.gadoboi.api.domain.model.Cliente;
import br.com.gadoboi.api.domain.model.Endereco;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.ClienteEntity;
import br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity.EnderecoEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public Cliente toDomain(ClienteEntity e) {
        Endereco endereco = null;
        if (e.getEndereco() != null) {
            EnderecoEntity en = e.getEndereco();
            endereco = new Endereco(en.getId(), en.getBairro(), en.getNumero(),
                    en.getComplemento(), en.getCidade(), en.getEstado());
        }
        return new Cliente(e.getId(), e.getNome(), e.getSobrenome(), e.getEmail(),
                e.getCpf(), e.getRg(), e.getTelefone(), e.getCelular(),
                e.getSexo(), e.getDataNasc(), endereco);
    }

    public ClienteEntity toEntity(Cliente d, String senhaHash) {
        EnderecoEntity enderecoEntity = null;
        if (d.endereco() != null) {
            Endereco en = d.endereco();
            enderecoEntity = EnderecoEntity.builder()
                    .id(en.id())
                    .bairro(en.bairro())
                    .numero(en.numero())
                    .complemento(en.complemento())
                    .cidade(en.cidade())
                    .estado(en.estado())
                    .build();
        }
        return ClienteEntity.builder()
                .id(d.id())
                .nome(d.nome())
                .sobrenome(d.sobrenome())
                .email(d.email())
                .cpf(d.cpf())
                .rg(d.rg())
                .telefone(d.telefone())
                .celular(d.celular())
                .sexo(d.sexo())
                .dataNasc(d.dataNasc())
                .senhaHash(senhaHash)
                .endereco(enderecoEntity)
                .build();
    }
}
