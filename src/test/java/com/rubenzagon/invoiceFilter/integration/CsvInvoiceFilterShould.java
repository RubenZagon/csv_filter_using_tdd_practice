package com.rubenzagon.invoiceFilter.integration;

import com.rubenzagon.invoiceFilter.CsvFilter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
Requirimientos de negocio:
 - Es válido que algunos campos estén vacíos (apareciendo dos comas seguidas o una coma final)
 - El número de factura no puede estar repetido. Si lo estuviese eliminaríamos todas las líneas con repetición.
 - Los impuestos IVA e IGIC son excluyentes, sólo puede aplicarse uno de los dos. Si alguna línea tiene contenido en ambos campos debe quedarse fuera.
 - Los campos CIF y NIF son excluyentes, sólo puede usar uno de ellos.
 - El neto es el resultado de aplicar al bruto el correspondiente impuesto. Si algún neto no está bien calculado se queda fuera.
 */
final class CsvInvoiceFilterShould {

    @Test
    void allow_for_correct_lines_only() {
        String headerLine = "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente";
        String invoiceLine = "1, 02/05/2019, 1000, 810, 19, , ACER Laptop, B76430130,";

        List<String> result = CsvFilter.filter(List.of(headerLine, invoiceLine));

        assertThat(result).isEqualTo(List.of(headerLine,invoiceLine));
    }

    /*
        - Un fichero con una sola factura donde IVA e IGIC están rellenos, deberían eliminar la línea
        - Un fichero con una sola factura donde el neto está mal calculado, debería ser eliminada
        - Un fichero con una sola factura donde CIF y NIF están rellenos, deberían eliminar la línea
        - Un fichero con una sola línea es incorrecto porque no tiene cabecera
        - Si el número de factura se repite en varias líneas, se eliminan todas ellas (Sin dejar ninguna).
        - Una lista vacía o nula producirá una lista vacía de salida
     */
}
