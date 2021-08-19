package com.example.postcontrol.utils;

import java.time.LocalDate;

public class MethodsUtils {

    public static Double converterValor(String valorContrato) {
        Double valor = null;

        try {
            String valorString = valorContrato.replaceAll("[^\\d,]", "")
                    .replace(",", ".");
            valor = Double.parseDouble(valorString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return valor;
    }

    public static LocalDate converterData(String dtInicio) {
        String[] dateString = dtInicio.split("/");
        return LocalDate.of(Integer.parseInt(dateString[2]),
                Integer.parseInt(dateString[1]), Integer.parseInt(dateString[0]));
    }

    public static String exibirData(LocalDate dtInicioContrato) {
        String data = dtInicioContrato.toString();
        String[] dataSeparada = data.split("-");
        return dataSeparada[2] + "/" + dataSeparada[1] + "/" + dataSeparada[0];
    }

    public static String exibirValor(Double valorContrato) {
        String valorTotal = String.valueOf(valorContrato);
        String valorInteiro = valorTotal.substring(0, valorTotal.indexOf("."));
        String valorDecimal = valorTotal.substring(valorTotal.indexOf(".") + 1);
        String valorInteiroReverso = new StringBuilder(valorInteiro).reverse().toString();
        String valorExibido;

        if (valorInteiroReverso.length()/3 > 1) {
            String valorInteiroDividido = new StringBuilder()
                    .append(valorInteiroReverso.substring(0, 3))
                    .append(".")
                    .append(valorInteiroReverso.substring(3))
                    .reverse()
                    .toString();

            valorExibido = "R$ " + valorInteiroDividido + "," + valorDecimal;
        } else {
            valorExibido = "R$ " + valorInteiro + "," + valorDecimal;
        }
        return valorExibido;
    }
}
