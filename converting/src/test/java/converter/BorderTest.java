package test.java.converter;

import main.java.converter.Border;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BorderTest {
    
    @Test
    public void constructor(){
        var border = new Border(-1.0, 1.0, -1.0, 1.0);
        
        Assertions.assertEquals(-1.0, border.xMin);
        Assertions.assertEquals(1.0, border.xMax);
        Assertions.assertEquals(-1.0, border.yMin);
        Assertions.assertEquals(1.0, border.yMax);
    }
    
    @Test
    public void borderMinMoreBorderMax(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Border(1.0, -1.0, 1.0, 1.0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Border(-1.0, 1.0, 1.0, -1.0));
    }
}
