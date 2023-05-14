private NotificationCategory getNotificationCategory(PortletRequest request) {
        PortletPreferences preferences = request.getPreferences();
        String title = preferences.getValue(NOTIFICATION_CATEGORY_PREF, DEFAULT_CATEGORY);

        NotificationCategory category = new NotificationCategory();
        category.setTitle(title);

        return category;
    }