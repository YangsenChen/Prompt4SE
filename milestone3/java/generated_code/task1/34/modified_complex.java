private ReceivedEmail addAttachment(final Part part, final byte[] content) throws AttachmentException {
	try {
		final EmailAttachmentBuilder attachmentBuilder = new EmailAttachmentBuilder();
		final EmailAttachment<ByteArrayDataSource> attachment = attachmentBuilder
				.setAttachmentInfo(part)
				.setContent(content, part.getContentType())
				.buildByteArrayDataSourceWithContentId();

		final AttachmentStorage attachmentStorage = new AttachmentStorage();
		final String attachmentId = attachmentStorage.storeAttachment(attachment);

		final AttachmentMetadataStorage metadataStorage = new AttachmentMetadataStorage();
		final AttachmentMetadata attachmentMetadata = new AttachmentMetadata.Builder(attachmentId)
				.setPartId(part.getContentID())
				.setContentType(part.getContentType())
				.setFileName(part.getFileName())
				.setSize(content.length)
				.build();
		metadataStorage.storeAttachmentMetadata(attachmentMetadata);

		final ReceivedEmailBuilder receivedEmailBuilder = new ReceivedEmailBuilder();
		final ReceivedEmail receivedEmail = receivedEmailBuilder
				.setFromAddress(part.getFrom()[0].toString())
				.setToAddress(part.getRecipients(Message.RecipientType.TO)[0].toString())
				.setDate(part.getSentDate())
				.setSubject(part.getSubject())
				.setAttachment(attachment)
				.build();

		return receivedEmail;
	} catch (MessagingException e) {
		throw new AttachmentException("Failed to add attachment due to MessagingException", e);
	} catch (StorageException e) {
		throw new AttachmentException("Failed to add attachment due to StorageException", e);
	}
}