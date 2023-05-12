package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {

    @Mock
    private TableMetadata metadata;

    @Test
    public void testUpdateLastIndexed_ValidData() {
        when(metadata.getGeoPackageId()).thenReturn("geo1");
        when(metadata.getTableName()).thenReturn("table1");
        when(metadata.updateLastIndexed(anyString(), anyString(), anyLong())).thenReturn(true);

        boolean result = App.updateLastIndexed(metadata, 2000);

        verify(metadata, times(1)).setLastIndexed(2000);
        assertEquals(true, result);
    }

    @Test
    public void testUpdateLastIndexed_UpdateFailed() {
        when(metadata.getGeoPackageId()).thenReturn("geo1");
        when(metadata.getTableName()).thenReturn("table1");
        when(metadata.updateLastIndexed(anyString(), anyString(), anyLong())).thenReturn(false);

        boolean result = App.updateLastIndexed(metadata, 2000);

        verify(metadata, times(0)).setLastIndexed(2000);
        assertEquals(false, result);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateLastIndexed_NullMetadata() {
        App.updateLastIndexed(null, 2000);
    }
}
