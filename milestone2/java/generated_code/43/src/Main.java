import static org.junit.Assert.*;

import org.junit.Test;

import javax.portlet.PortletRequest;
import javax.portlet.PortletPreferences;

public class Main {

    @Test
    public void testGetNotificationCategory_DefaultCategory() {
        PortletRequest request = new MockPortletRequest();
        NotificationCategory category = getNotificationCategory(request);
        assertEquals(DEFAULT_CATEGORY, category.getTitle());
    }

    @Test
    public void testGetNotificationCategory_CustomCategory() {
        MockPortletPreferences mockPrefs = new MockPortletPreferences();
        mockPrefs.setMockValue(NOTIFICATION_CATEGORY_PREF, "alerts");
        PortletRequest request = new MockPortletRequest(mockPrefs);
        NotificationCategory category = getNotificationCategory(request);
        assertEquals("alerts", category.getTitle());
    }

    @Test
    public void testGetNotificationCategory_EmptyCategory() {
        MockPortletPreferences mockPrefs = new MockPortletPreferences();
        mockPrefs.setMockValue(NOTIFICATION_CATEGORY_PREF, "");
        PortletRequest request = new MockPortletRequest(mockPrefs);
        NotificationCategory category = getNotificationCategory(request);
        assertEquals("", category.getTitle());
    }

    private static final String NOTIFICATION_CATEGORY_PREF = "notification_category";
    private static final String DEFAULT_CATEGORY = "news";

    private static NotificationCategory getNotificationCategory(PortletRequest request) {
        PortletPreferences preferences = request.getPreferences();
        String title = preferences.getValue(NOTIFICATION_CATEGORY_PREF, DEFAULT_CATEGORY);

        NotificationCategory category = new NotificationCategory();
        category.setTitle(title);

        return category;
    }
}

class NotificationCategory {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

class MockPortletRequest extends javax.portlet.PortletRequest {
    private PortletPreferences mockPrefs;

    public MockPortletRequest() {
        this.mockPrefs = new MockPortletPreferences();
    }

    public MockPortletRequest(PortletPreferences mockPrefs) {
        this.mockPrefs = mockPrefs;
    }

    @Override
    public PortletPreferences getPreferences() {
        return mockPrefs;
    }

    // Implementations of the abstract methods of PortletRequest
    // can be added here for use in testing.
}

class MockPortletPreferences extends javax.portlet.PortletPreferences {
    private String mockValue;

    public void setMockValue(String key, String value) {
        this.mockValue = value;
    }

    @Override
    public String getValue(String key, String def) {
        return mockValue != null ? mockValue : def;
    }

    // Implementations of the abstract methods of PortletPreferences
    // can be added here for use in testing.
}