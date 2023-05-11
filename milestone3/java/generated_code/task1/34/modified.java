private ReceivedEmail addAttachment(final Part part, final byte[] content) throws MessagingException {
    final EmailAttachmentBuilder builder = new EmailAttachmentBuilder();
    builder.setAttachmentInfo(part);
    builder.setContent(content, part.getContentType());
  
    final EmailAttachment<ByteArrayDataSource> attachment = builder.buildByteArrayDataSource();
    attachment.setSize(content.length);
  
    final AttachmentStorage storage = new AttachmentStorage();
    storage.store(attachment);
    
    final ReceivedEmail receivedEmail = new ReceivedEmail();
    receivedEmail.setAttachment(attachment);
  
    return receivedEmail;
}