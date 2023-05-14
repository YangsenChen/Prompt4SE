private NotificationCategory getNotificationCategory(PortletRequest request, DataProvider dataProvider) throws Exception {
    NotificationCategory category;
    PortletPreferences preferences = request.getPreferences();
    String title = preferences.getValue(NOTIFICATION_CATEGORY_PREF, DEFAULT_CATEGORY);

    String categoryStr = dataProvider.getCategory(title);
    if (categoryStr != null) {
        category = mapper.readValue(categoryStr, NotificationCategory.class);
    } else {
        category = createDefaultCategory();
    }

    return category;
}

private NotificationCategory createDefaultCategory() {
    NotificationCategory category = new NotificationCategory();
    category.setTitle(DEFAULT_CATEGORY);
    category.setDescription("Default notification category");
    category.setPriority(Priority.LOW);
    return category;
}