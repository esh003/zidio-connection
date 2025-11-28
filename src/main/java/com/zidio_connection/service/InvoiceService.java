package com.zidio_connection.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.zidio_connection.dto.EmailRequestDTO;
import com.zidio_connection.entity.Payment;
import com.zidio_connection.entity.SubscriptionPlan;
import com.zidio_connection.exception.ResourceNotFoundException;
import com.zidio_connection.repository.PaymentRepository;
import com.zidio_connection.repository.SubPlanRepository;

@Service
public class InvoiceService {

    private final PaymentRepository paymentRepo;
    private final SubPlanRepository subPlanRepo;
    private final EmailService emailService;

    public InvoiceService(PaymentRepository paymentRepo,
                          SubPlanRepository subPlanRepo,
                          EmailService emailService) {
        this.paymentRepo = paymentRepo;
        this.subPlanRepo = subPlanRepo;
        this.emailService = emailService;
    }

    // Generate PDF file for given paymentId and return the File object
    public File generateInvoice(Long paymentId) {
        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + paymentId));

        SubscriptionPlan plan = subPlanRepo.findById(payment.getSubscriptionPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Subscription plan not found with id: " + payment.getSubscriptionPlanId()));

        // Ensure Invoices directory exists
        File dir = new File("Invoices");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = "Invoices/invoice-" + payment.getId() + ".pdf";
        File file = new File(fileName);

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Invoice", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ")); // empty line

            // Basic info
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            document.add(new Paragraph("Invoice ID: " + payment.getId()));
            document.add(new Paragraph("Transaction ID: " + payment.getTransactionId()));
            document.add(new Paragraph("Payment Time: " +
                    (payment.getPaymentTime() != null ? payment.getPaymentTime().format(fmt) : "N/A")));
            document.add(new Paragraph("User ID: " + payment.getUserId()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Plan: " + plan.getName()));
            document.add(new Paragraph("Duration: " + plan.getDuration()));
            document.add(new Paragraph("Plan Price: " + plan.getPrice()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Payment Status: " + payment.getStatus()));
            document.add(new Paragraph("Payment Type: " + payment.getPaymentType()));
            document.add(new Paragraph("Amount Paid: " + payment.getAmount()));
            document.add(new Paragraph(" "));

            // Table (optional, but looks nicer)
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            addCell(table, "Field");
            addCell(table, "Value");
            addCell(table, "Invoice ID");
            addCell(table, String.valueOf(payment.getId()));
            addCell(table, "Transaction ID");
            addCell(table, payment.getTransactionId());
            addCell(table, "User ID");
            addCell(table, String.valueOf(payment.getUserId()));
            addCell(table, "Subscription Plan");
            addCell(table, plan.getName());
            addCell(table, "Duration");
            addCell(table, plan.getDuration().name());
            addCell(table, "Status");
            addCell(table, payment.getStatus().name());
            addCell(table, "Amount");
            addCell(table, String.valueOf(payment.getAmount()));

            document.add(new Paragraph(" "));
            document.add(table);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Thank you for subscribing to our service."));
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Failed to generate invoice PDF", e);
        } finally {
            document.close();
        }

        return file;
    }

    private void addCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text));
        table.addCell(cell);
    }

    // Generate invoice and email it as attachment
    public void generateAndEmailInvoice(Long paymentId, String toEmail) {
        File invoiceFile = generateInvoice(paymentId);

        EmailRequestDTO dto = EmailRequestDTO.builder()
                .to(toEmail)
                .subject("Your Subscription Invoice - Payment #" + paymentId)
                .body("Please find attached the invoice for your recent payment.")
                .build();

        try {
            emailService.sendEmailWithAttachment(dto, invoiceFile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send invoice email", e);
        }
    }
}
