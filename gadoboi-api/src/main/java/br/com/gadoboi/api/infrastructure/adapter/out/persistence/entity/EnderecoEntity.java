package br.com.gadoboi.api.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "endereco")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EnderecoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bairro;
    private String numero;
    private String complemento;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false, length = 2)
    private String estado;
}
