package mvc_crud.crud.dto;

import lombok.Data;

@Data
public class CidadaoDTO {
    private Long id;
    private String nome;
    private String email;
    private String endereco;
    private String celular;
}