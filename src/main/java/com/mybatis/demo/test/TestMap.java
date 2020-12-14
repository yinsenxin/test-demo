package com.mybatis.demo.test;



public class TestMap {
    public static void main(String[] args) {

        TestGeneraicity<String>  test = new TestGeneraicity();

        test.setG("this is String ");
        System.out.println(test.getG());

        ExtendsTest extendsTest = new ExtendsTest();
        extendsTest.setG(1);
        System.out.println(extendsTest.getG());

    }
}

class TestGeneraicity<G> {

    private G g;

    public G getG(){
        return this.g;
    }
    public void setG(G g){
        this.g = g;
    }
}

class ExtendsTest extends TestGeneraicity<Integer>{

}




