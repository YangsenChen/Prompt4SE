package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AppTest {


    private App main;

//    @Test
//    public void testCreateUser_ValidUserParamAndServiceTicket() {
//        User userParam = new User("{\"serviceTicket\":\"testTicket\"}");
//        String serviceTicket = "testTicket";
//
//        // mock putJson method
//        when(main.putJson(any(User.class), anyString())).thenReturn("{\"serviceTicket\":\"testTicket\"}");
//
//        // mock WS.Path.User.Version1.userCreate() method
//        when(WS.Path.User.Version1.userCreate()).thenReturn("http://example.com/api/v1/userCreate");
//
//        // set serviceTicket
//        userParam.setServiceTicket(serviceTicket);
//
//        User newUser = main.createUser(userParam);
//
//        verify(main, times(1)).putJson(any(User.class), anyString());
//        assertEquals(serviceTicket, newUser.getServiceTicket());
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testCreateUser_NullUserParam() {
//        String serviceTicket = "testTicket";
//
//
//        User newUser = main.createUser(null);
//    }

    @Test(expected = NullPointerException.class)
    public void testCreateUser_NullServiceTicket() {
        User userParam = new User("{\"serviceTicket\":\"testTicket\"}");

        // mock putJson method
        when(main.putJson(any(User.class), anyString())).thenReturn("{\"serviceTicket\":\"testTicket\"}");

    }
}

