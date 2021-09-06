package com.example.postcontrol.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

public class MethodsUtils {

    public static Double converterValor(String valorContrato) {
        Double valor = null;
        boolean formatoBrasileiro = valorContrato.charAt(valorContrato.length() - 3) != '.';

        try {
            String valorString;

            if (formatoBrasileiro) {
                valorString = valorContrato.replaceAll("[^\\d,]", "")
                        .replace(",", ".");
            } else {
                valorString = valorContrato.replaceAll("[^\\d.]", "")
                        .replace(",", ".");
            }

            valor = Double.parseDouble(valorString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return valor;
    }

    public static String exibirValor(Double valorContrato) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String valorTotal = String.valueOf(decimalFormat.format(valorContrato));

        String replaceable = String.format("[%s,.\\s]", Objects.requireNonNull(
                java.text.NumberFormat.getCurrencyInstance().getCurrency()).getSymbol());
        String cleanString = valorTotal.replaceAll(replaceable, "");

        double parsed;
        try {
            parsed = Double.parseDouble(cleanString);
        } catch (NumberFormatException e) {
            parsed = 0.00;
        }

        return NumberFormat.getCurrencyInstance().format((parsed / 100));
    }
}
