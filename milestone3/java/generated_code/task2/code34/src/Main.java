import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

public class Main {

    public static void main(String[] args) {
        testAddAttachmentWithPlainText();
        testAddAttachmentWithImage();
        testAddAttachmentWithPDF();
    }

    private static ReceivedEmail addAttachment(final Part part, final byte[] content) throws MessagingException {
        final EmailAttachmentBuilder builder = addAttachmentInfo(part);
        builder.content(content, part.getContentType());
        final EmailAttachment<ByteArrayDataSource> attachment = builder.buildByteArrayDataSource();
        attachment.setSize(content.length);
        return storeAttachment(attachment);
    }

    private static EmailAttachmentBuilder addAttachmentInfo(Part part) {
        // Implementation specific to your application's requirements
        return new EmailAttachmentBuilder();
    }

    private static ReceivedEmail storeAttachment(EmailAttachment<ByteArrayDataSource> attachment) {
        // Implementation specific to your application's requirements
        return new ReceivedEmail();
    }

    // Test method for adding a plain text attachment
    @Test
    public static void testAddAttachmentWithPlainText() {
        String text = "Hello, this is a plain text attachment.";
        byte[] attachmentContent = text.getBytes();
        MimeBodyPart attachmentPart = new MimeBodyPart();
        try {
            attachmentPart.setContent(text, "text/plain");
            ReceivedEmail receivedEmail = addAttachment(attachmentPart, attachmentContent);
            assert receivedEmail != null : "ReceivedEmail should not be null";
            assert receivedEmail.toString().contains("attachment") : "ReceivedEmail should have an attachment";
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Test method for adding an image attachment
    @Test
    public static void testAddAttachmentWithImage() {
        byte[] attachmentContent = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04};
        MimeBodyPart attachmentPart = new MimeBodyPart();
        try {
            attachmentPart.setContent(attachmentContent, "image/jpeg");
            ReceivedEmail receivedEmail = addAttachment(attachmentPart, attachmentContent);
            assert receivedEmail != null : "ReceivedEmail should not be null";
            assert receivedEmail.toString().contains("attachment") : "ReceivedEmail should have an attachment";
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Test method for adding a PDF attachment
    @Test
    public static void testAddAttachmentWithPDF() {
        byte[] attachmentContent = new byte[]{0x12, 0x34, 0x56, 0x78, 0x9A, 0xBC};
        MimeBodyPart attachmentPart = new MimeBodyPart();
        try {
            attachmentPart.setContent(attachmentContent, "application/pdf");
            ReceivedEmail receivedEmail = addAttachment(attachmentPart, attachmentContent);
            assert receivedEmail != null : "ReceivedEmail should not be null";
            assert receivedEmail.toString().contains("attachment") : "ReceivedEmail should have an attachment";
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    static class EmailAttachmentBuilder {
        public void content(byte[] content, String contentType) {
        }

        public EmailAttachment<ByteArrayDataSource> buildByteArrayDataSource() {
            return new EmailAttachment<ByteArrayDataSource>();
        }
    }

    static class ByteArrayDataSource {
    }

    static class ReceivedEmail {
        @Override
        public String toString() {
            return "ReceivedEmail object with attachment";
        }
    }
}