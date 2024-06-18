package com.app.Backend.service;

import com.app.Backend.persistence.entities.Pedido;
import com.app.Backend.persistence.repository.PedidoRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ReportService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public byte[] generateSalesReport(String date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatter.parse(date);
            List<Pedido> pedidos = pedidoRepository.findByFechaPedido(fecha);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph header = new Paragraph("Reporte de ventas del día " + date, headerFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            document.add(new Paragraph("\n"));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            PdfPCell headerCell;
            Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            String[] headers = {"ID Pedido", "Dirección de Entrega", "Fecha del Pedido", "Número de Serie", "Total del Pedido"};

            for (String headerTitle : headers) {
                headerCell = new PdfPCell(new Phrase(headerTitle, tableHeaderFont));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                headerCell.setMinimumHeight(30);
                table.addCell(headerCell);
            }

            double totalGeneral = 0.0;

            Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
            PdfPCell cell;

            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "PE"));

            for (Pedido pedido : pedidos) {
                cell = new PdfPCell(new Phrase(String.valueOf(pedido.getIdPedido()), cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(pedido.getDireccionEntrega(), cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(pedido.getFechaPedido().toString(), cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(pedido.getNumeroSerie(), cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(currencyFormat.format(pedido.getTotalPedido()), cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                totalGeneral += pedido.getTotalPedido();
            }

            PdfPCell totalCell = new PdfPCell(new Paragraph("Total General"));
            totalCell.setColspan(4);
            totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            totalCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            totalCell.setMinimumHeight(30);
            table.addCell(totalCell);

            cell = new PdfPCell(new Phrase(currencyFormat.format(totalGeneral), cellFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            document.add(table);
            document.close();

            return outputStream.toByteArray();
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}