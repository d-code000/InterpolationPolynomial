package test.java.converter;

import main.java.converter.Border;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BorderTest {
    
    @Test
    public void constructor(){
        var border = new Border(-1.0, 1.0, -1.0, 1.0);
        
        Assertions.assertEquals(-1.0, border.getMinX());
        Assertions.assertEquals(1.0, border.getMaxX());
        Assertions.assertEquals(-1.0, border.getMinY());
        Assertions.assertEquals(1.0, border.getMaxY());
    }
    
    @Test
    public void borderMinMoreBorderMax(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Border(1.0, -1.0, 1.0, 1.0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Border(-1.0, 1.0, 1.0, -1.0));
    }
    
    @Test
    public void setMinX(){
        var border = new Border(-1.0, 1.0, -1.0, 1.0);
        Assertions.assertEquals(-1.0, border.getMinX());
        
        border.setMinX(-2.0);
        Assertions.assertEquals(-2.0, border.getMinX());
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> border.setMinX(2.0));
    }
    
    @Test
    public void setMaxX(){
        var border = new Border(-1.0, 1.0, -1.0, 1.0);
        Assertions.assertEquals(1.0, border.getMaxX());
        
        border.setMaxX(2.0);
        Assertions.assertEquals(2.0, border.getMaxX());
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> border.setMaxX(-2.0));
    }
    
    @Test
    public void setMinY(){
        var border = new Border(-1.0, 1.0, -1.0, 1.0);
        Assertions.assertEquals(-1.0, border.getMinY());
        
        border.setMinY(-2.0);
        Assertions.assertEquals(-2.0, border.getMinY());
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> border.setMinY(2.0));
    }
    
    @Test
    public void setMaxY(){
        var border = new Border(-1.0, 1.0, -1.0, 1.0);
        Assertions.assertEquals(1.0, border.getMaxY());
        
        border.setMaxY(2.0);
        Assertions.assertEquals(2.0, border.getMaxY());
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> border.setMaxY(-2.0));
    }
}
