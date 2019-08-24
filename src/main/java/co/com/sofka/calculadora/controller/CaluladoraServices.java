package co.com.sofka.calculadora.controller;

import co.com.sofka.calculadora.contabilidad.Amortizacion;
import co.com.sofka.calculadora.contabilidad.RespuestaSalario;
import co.com.sofka.calculadora.contabilidad.TablaMultiplicar;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


import java.util.Arrays;
import java.util.List;

import static co.com.sofka.calculadora.utils.FuncionesCalculadora.*;

@RestController
@RequestMapping(value = "/calculadora")
@RequiredArgsConstructor
public class CaluladoraServices {

    @GetMapping(value = "/suma")
    public Mono<Integer> sumaDosNumeros (Integer a, Integer b) {
        return Mono.just(Arrays.asList(a,b)).flatMap(sumar);
    }

    @GetMapping(value = "/resta")
    public Mono<Integer> restaDosNumeros (Integer a, Integer b) {
        return Mono.just(Arrays.asList(a,b)).flatMap(restar);
    }

    @GetMapping(value = "/tabla_del")
    public Mono<List<TablaMultiplicar>> tablaMultiplicar (Integer factor) {
        return Mono.just(factor).flatMap(getTablaMultiplicar);
    }

    @GetMapping(value = "/division")
    public Mono<Integer> dividirdosnumeros (Integer a, Integer b) {
        return Mono.just(Arrays.asList(a,b)).flatMap(dividir);
    }


    @GetMapping(value = "/amortizacion")
    public Mono <List<Amortizacion>>tablaAmortizacion (Integer numberOfFees, Integer creditAmount) {
        return Mono.just(Arrays.asList(numberOfFees, creditAmount)).flatMap(tablaAmortizacion);
    }

   @GetMapping(value = "/reciboDePago")
   public Mono <List<RespuestaSalario>>tablaReciboDePago (Double salario, Double pagoNetoEmpleado) {
       return Mono.just(Arrays.asList(salario,pagoNetoEmpleado)).flatMap(ReciboDePago);
   }
   
}
