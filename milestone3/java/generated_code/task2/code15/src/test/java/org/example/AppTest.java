package org.example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class AppTest {

    @Mock
    private FitRow fitRow;

    @Mock
    private Cell cell;

//    @Test
//    public void testDoRow_ValidData() throws Exception {
//        when(fitRow.cells()).thenReturn(Collections.singletonList(cell));
//        when(FitUtils.extractCellParameter(cell)).thenReturn(new Object());
//
//        App main = new App();
//        main.doRow(fitRow);
//
//        verify(fitRow, times(1)).cells();
//        verifyNoMoreInteractions(fitRow);
//    }

    @Test(expected = NullPointerException.class)
    public void testDoRow_NullRow() throws Exception {
        App main = new App();
        main.doRow(null);
    }

    @Test(expected = Exception.class)
    public void testDoRow_ThrowsException() throws Exception {
        when(fitRow.cells()).thenReturn(Collections.singletonList(cell));
        when(FitUtils.extractCellParameter(cell)).thenThrow(new Exception("Exception occurred"));

        App main = new App();
        main.doRow(fitRow);
    }
}
