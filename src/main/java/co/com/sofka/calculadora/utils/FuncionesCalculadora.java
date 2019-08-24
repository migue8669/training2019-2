package co.com.sofka.calculadora.utils;

import co.com.sofka.calculadora.contabilidad.Amortizacion;
import co.com.sofka.calculadora.contabilidad.RespuestaSalario;
import co.com.sofka.calculadora.contabilidad.TablaMultiplicar;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class FuncionesCalculadora {

    public static final Function<List<Integer>,Mono<Integer> > sumar = lista ->
            Mono.just(
            lista.stream().
            reduce(0,
                    (a, b) -> a + b)
            );

    public static final Function<List<Integer>,Mono<Integer> > restar =  lista ->
            Mono.just(
            lista.stream().
            reduce(0,
                    (a, b) -> b - a)
            ).flatMap(x -> Mono.just(x * (-1)));


     public static final Function<Integer, Mono<List<TablaMultiplicar>>> getTablaMultiplicar = factor -> {
         List<TablaMultiplicar> tabla= new ArrayList<>(90);
          IntStream.rangeClosed(0, 10).forEach(digito ->
            tabla.add(TablaMultiplicar.builder().muliplicador(digito).multiplicando(factor).resultado(digito*factor).build())
      );
    return Mono.just(tabla);
    };


    public static final Function<List<Integer>,Mono<Integer> > dividir = lista ->
            Mono.just(
                    lista.stream().
                            reduce(0,
                                    (a,b)-> a / b)
            );

    public static final Function<List<Integer>, Mono<List<Amortizacion>>> tablaAmortizacion = list -> {
        final Double interes = 0.01;
        final Integer tarifa = list.get(0);
        final Integer montoCredito = 5;
        final Double liquidacion = (double) montoCredito / tarifa;

        List<Amortizacion> tabla= new ArrayList<>(90);
        IntStream.rangeClosed(1,tarifa).forEach(digito ->
            tabla.add(Amortizacion.builder().credito((double)montoCredito)
                   .numeroCuotas((double) tarifa)
                    .periodo(digito)
                    .interes((double) (tarifa - (montoCredito - liquidacion) * (digito - 1) * interes))
                    .amortizacion((liquidacion))
                    .cuota(((double) (montoCredito - tarifa * (digito - 1) * interes + liquidacion)))
                    .pendientePago( (montoCredito - liquidacion * digito))
                    .build())
            );

        return Mono.just(tabla);
    };


    public static final Function<List<Double>, Mono<List<RespuestaSalario>>> ReciboDePago = list -> {
        final Double aporteSaludEmpleado = 0.04;
        final Double aporteSaludEmpleador = 0.12;
        final Double aportePensionEmpleado = 0.04;
        final Double aportePensionEmpleador = 0.85;
        final Double aporteRiesgosLaborales = 0.05;
        final Double aporteCaja = 0.04;
        final Double aporteFSP = 0.01;
        final Integer salario = 1000000;
        final Integer registro = 2;

        List<RespuestaSalario> tabla= new ArrayList<>(90);
        IntStream.rangeClosed(1,registro).forEach(digito ->
                tabla.add(co.com.sofka.calculadora.contabilidad.RespuestaSalario.builder().salarioBase(salario)
                        .costoEmpleador(salario)
                        .aporteSaludEmpleador((double) salario*aporteSaludEmpleador)
                        .aportePensionEmpleador((double)salario*aportePensionEmpleador)
                        .aporteRiesgosLaborales((double)salario*aporteRiesgosLaborales)
                        .aporteCajaDeCompensacion((double)(salario*aporteCaja))
                        .aporteFSP((double)salario*aporteFSP)
                        .aportePensionEmpleado((double)salario*aportePensionEmpleado)
                        .aporteSaludEmpleado((double)salario*aporteSaludEmpleado)
                        .pagoNetoEmpleado((double)(salario*aporteSaludEmpleador)+(salario*aportePensionEmpleador)+(salario*aporteRiesgosLaborales)+(salario*aporteCaja)+(salario*aporteFSP)+(salario*aportePensionEmpleado)+(salario*aporteSaludEmpleado))
                        .build())
        );

        return Mono.just(tabla);
    };
}
