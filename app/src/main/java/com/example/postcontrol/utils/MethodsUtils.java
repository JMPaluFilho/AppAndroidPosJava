package com.example.postcontrol.utils;

import java.time.LocalDate;

public class MethodsUtils {

    public static Double converterValor(String valorContrato) {
        Double valor = null;

        try {
            String valorString = valorContrato.replaceAll("[^\\d,]", "")
                    .replace(",",".");
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

}
