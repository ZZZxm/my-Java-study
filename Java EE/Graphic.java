package Lecture;

// 抽象类，不一定要有抽象方法
public abstract class Graphic {

    // 抽象方法
    public abstract int square();
}

class Rectangle extends Graphic {


    @Override
    public int square() {
        return 0;
    }
}
