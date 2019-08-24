package co.com.sofka.calculadora.contabilidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class TablaMultiplicar {
    private Integer multiplicando;
    private Integer muliplicador;
    private Integer resultado;
}
