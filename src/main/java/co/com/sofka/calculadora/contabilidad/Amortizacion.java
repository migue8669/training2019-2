package co.com.sofka.calculadora.contabilidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Amortizacion {
    private Double credito;
    private Double numeroCuotas;
    private Integer periodo;
    private Double interes;
    private Double amortizacion;
    private Double cuota;
    private Double pendientePago;
}
