import java.util.ArrayList;

public class Wrapper {
    static ArrayList<Employee> arrayList = new ArrayList<Employee>(){};

    public class Employee {
        String name;
        int hoursWorked;
        float hourlyRate;
        float tax;
        float grossPay;
        float netPay;

        public Employee(String name, int hoursWorked, float hourlyRate){
            this.name = name;
            this.hoursWorked = hoursWorked;
            this.hourlyRate= hourlyRate;
        }

        public String getName(){
            return name;
        }

        public void calculatePay(){
            int payableHours;

            if( hoursWorked <= 40){
                payableHours = hoursWorked;
            }else{
                payableHours = 40+(hoursWorked - 40)* 3/2;
            }

            grossPay = payableHours * hourlyRate;   /// sửa biến hoursWorked bằng payableHours

            if(grossPay > 200){                 // sai không có “=”
                tax = (grossPay-200) * 20/100;
            }

            netPay = grossPay - tax;
        }// end calculatePay()
        public static void Label(){
            System.out.printf("%-10s %-10s %-12s %-10s %-10s %-10s","Name","Hours","Rate","Gross","Tax","Net");
        }
        public void Show(){
            System.out.printf("%n%-10s %-10d %-10.2f   %-10.2f %-10.2f %-10.2f",name,hoursWorked,hourlyRate,grossPay,tax,netPay);
        }
    }

    public static void calculatePay(){
        Employee.Label();
        for(Employee employee : arrayList){
            employee.calculatePay();
        }
    }

    public static void Show(){
        for(Employee employee : arrayList){
            employee.Show();
        }
    }
}
