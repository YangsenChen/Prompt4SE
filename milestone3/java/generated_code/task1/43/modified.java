private NotificationCategory getNotificationCategory(PortletPreferences preferences) {
    String title = preferences.getValue(NOTIFICATION_CATEGORY_PREF, DEFAULT_CATEGORY);
    
    NotificationCategory category = createNotificationCategory(title);
    
    return category;
}

private NotificationCategory createNotificationCategory(String title) {
    NotificationCategory category = new NotificationCategory();
    category.setTitle(title);
    return category;
}