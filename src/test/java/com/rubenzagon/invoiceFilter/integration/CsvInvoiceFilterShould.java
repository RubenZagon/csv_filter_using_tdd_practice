package com.rubenzagon.invoiceFilter.integration;

import com.rubenzagon.invoiceFilter.CsvFilter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

final class CsvInvoiceFilterShould {

    @Test
    void allow_for_correct_lines_only() {
        String headerLine = "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente";
        String invoiceLine = "1, 02/05/2019, 1000, 810, 19, , ACER Laptop, B76430130,";

        List<String> result = CsvFilter.filter(List.of(headerLine, invoiceLine));

        assertThat(result).isEqualTo(List.of(headerLine,invoiceLine));
    }
}
