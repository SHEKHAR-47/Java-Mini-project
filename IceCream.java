import java.util.jar.Attributes.Name;

public class IceCream {
    String name;
    float price;
    
    Topping topping = new Topping();

    public static IceCream copy( IceCream other ) {
        IceCream newObj = new IceCream();
        newObj.name = other.name;
        newObj.price = other.price;
        return newObj;
    }
}
