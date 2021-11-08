import java.util.ArrayList;

public class RunMain {
    public static void main(String[] args) {
        Wrapper wrapper = new Wrapper();
        Wrapper.arrayList.add(wrapper.new Employee("ADAMS",35,5.0f));
        Wrapper.arrayList.add(wrapper.new Employee("BANKER",40,5.0f));
        Wrapper.arrayList.add(wrapper.new Employee("CAIRNS",44,5.0f));
        Wrapper.arrayList.add(wrapper.new Employee("DONALD",20,6.0f));

        Wrapper.calculatePay();
        Wrapper.Show();
    }
}
