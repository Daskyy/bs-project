package com.thws.eventmanager.domain.services.other;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

public class TicketPdfGenerator {

    public static String generateTicket(Ticket ticket, int ticketCount, Payment payment) throws IOException {
        String filePath = "tickets/ticket_" + ticket.getId() + ".pdf";
        File file = new File("tickets");
        if (!file.exists()) file.mkdirs(); // Ensure directory exists

        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc, PageSize.A6); // Compact size
        document.setMargins(15, 15, 15, 15);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        DecimalFormat df = new DecimalFormat("#,##0.00");
        String priceFormatted = df.format(ticket.getEvent().getTicketPrice() / 100.0) + " €";
        String totalPrice = df.format((ticket.getEvent().getTicketPrice() * ticketCount) / 100.0) + " €";

        document.add(new Paragraph(ticket.getEvent().getName())
                .setBold().setFontSize(18).setFont(font)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(5));

        document.add(new Paragraph("Date: " + ticket.getEvent().getStartDate().toLocalDate())
                .setFontSize(10).setFont(font)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10));

        document.add(new Paragraph(ticket.getEvent().getLocation().getName())
                .setFontSize(12).setFont(font)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10));

        float[] columnWidths = {80, 150};
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).setWidth(UnitValue.createPercentValue(80));

        table.addCell(createHeaderCell("Buyer"));
        table.addCell(createValueCell(ticket.getUser().getName()));

        table.addCell(createHeaderCell("Email"));
        table.addCell(createValueCell(ticket.getUser().getEmail()));

        table.addCell(createHeaderCell("Tickets"));
        table.addCell(createValueCell(String.valueOf(ticketCount)));

        table.addCell(createHeaderCell("Price"));
        table.addCell(createValueCell(priceFormatted));

        table.addCell(createHeaderCell("Total"));
        table.addCell(createValueCell(totalPrice));

        document.add(table);

        Table paymentTable = new Table(UnitValue.createPercentArray(columnWidths)).setWidth(UnitValue.createPercentValue(80));

        paymentTable.addCell(createHeaderCell("Payment"));
        paymentTable.addCell(createValueCell(payment.getPaymentMethodId()));

        paymentTable.addCell(createHeaderCell("Txn ID"));
        paymentTable.addCell(createValueCell(payment.getPaymentIntentId()));

        document.add(paymentTable);

        // ✅ Footer
        document.add(new Paragraph("Valid for event entry")
                .setItalic()
                .setFontSize(8).setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(10));

        document.close();
        return filePath;
    }

    // 🎨 Header Cell (Gray Background)
    private static Cell createHeaderCell(String text) {
        return new Cell()
                .add(new Paragraph(text).setBold().setFontSize(10))
                .setBackgroundColor(DeviceGray.GRAY)
                .setPadding(4);
    }

    // 🎨 Value Cell (Regular)
    private static Cell createValueCell(String text) {
        return new Cell()
                .add(new Paragraph(text).setFontSize(10))
                .setPadding(4);
    }
}
