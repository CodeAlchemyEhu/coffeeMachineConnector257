package lt.esdc.designpatterns.machine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class V75ToV69AdapterTest {

    private CoffeeMachineV75 v75Machine;
    private V75ToV69Adapter adapter;

    @BeforeEach
    void setUp() {
        v75Machine = mock(CoffeeMachineV75.class);
        adapter = new V75ToV69Adapter(v75Machine);
    }

    @Test
    void shouldCallV75MethodsWhenSend() {
        when(v75Machine.getToken()).thenReturn("token123");
        when(v75Machine.openSession("token123")).thenReturn("session456");
        
        adapter.send("200ml 15g 100ml");
        
        verify(v75Machine).getToken();
        verify(v75Machine).openSession("token123");
        verify(v75Machine).makeCoffee("token123", "session456", "200ml 15g 100ml");
        verify(v75Machine).closeSession("token123", "session456");
    }

    @Test
    void shouldWorkWithToppings() {
        when(v75Machine.getToken()).thenReturn("token789");
        when(v75Machine.openSession("token789")).thenReturn("session999");
        
        adapter.send("300ml 12g 250ml cream caramel");
        
        verify(v75Machine).makeCoffee("token789", "session999", "300ml 12g 250ml cream caramel");
    }
}

