package file25;
//Appraisable.java
// 可鑑定元素介面
public interface Appraisable {
    void accept(Appraiser visitor);
    String getName();
}
