public class Testing implements NewInterface {

    public static void main(String[] args)
    {
        Testing interfaceDemo = new Testing();

        // Calling the static method of interface
        NewInterface.hello();

        // Calling the abstract method of interface
        interfaceDemo.overrideMethod("Hello, Override Method here");
    }

    // Implementing interface method

    @Override
    public void overrideMethod(String str)
    {
        System.out.println(str);
    }
}


interface NewInterface {

    // static method
    static void hello()
    {
        System.out.println("Hello, New Static Method Here");
    }

    // Public and abstract method of Interface
    void overrideMethod(String str);
}

abstract class abs{
    abs(){
        
    }
}

