package Lecture;

public class Oct9 {
    // Inner class

    public static void main(String[] args) {
        int x = 100;

        // x = 200 这样就不是逻辑上的final

        // local class 本地类 (在方法中的类)
        class A {
            public void speak() {
                // 可以获取本地的变量值，但必须是final的（定义下的final或逻辑上的final）
                System.out.println(x);
            }
        }

        A a = new A();
        a.speak();

        // 使用枚举变量
        Day day = Day.MON;
        System.out.println(day.showFeeling());

        // values()返回枚举列表
        for (Day b : Day.values()) {
            System.out.print(b.name() + "  ");// name()返回枚举名称
            System.out.println(b.showFeeling());
        }

        // Lecture 5
        // 继承
        Cat cat = new Cat();
        cat.speak();

        class Base {
            Base() {
                System.out.println("Base construct");
                initialize();
            }

            // 调用于构造函数，声明为final防止重载
            final public void initialize() {
                System.out.println("base init");
            }
        }

        class Derived extends Base {
            Derived() {
                System.out.println("Derived construct");
                initialize();
            }

            // 重载了Base的方法initialize()，而这个函数在构造函数中被调用，可能会引发错误
            // 会在子类未构造前就调用了它的initialize()函数
            //
//            public void initialize() {
//                System.out.println("derived init");
//            }
        }

        Base base = new Derived();
    }


}

// enum枚举类
enum Day {
    // 对于枚举类的每个值，都要符合构造函数的参数列表
    MON("good"),
    TUS,
    WED,
    THU("bad"),
    FRI,
    SAT,
    SUN("nice");

    private String feeling;

    // 可以使用构造函数
    Day(String feeling) {
        this.feeling = feeling;
    }

    Day() {
        this(" ");
    }

    // can use operation
    public String showFeeling() {
        return feeling;
    }
}

class Animal {
    protected int weight;

    public Animal() {
        System.out.println("Animal construction");
    }

    void speak() {
        System.out.println("Animal");
    }
}

class Cat extends Animal {

    public Cat() {
        super();
        System.out.println("Cat construction");
    }

    // rules of Modifiers
    // Animal中的speak()是package access，所以这里只能是package或public
    public void speak() {
        super.speak();// Keyword: super
        System.out.println("Cat");
    }
}

class Dog extends Animal {

}



