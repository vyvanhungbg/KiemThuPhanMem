package com.ktpm.BankingDomainApplicationTesting.service;

import com.ktpm.BankingDomainApplicationTesting.entity.CustomUserDetails;
import com.ktpm.BankingDomainApplicationTesting.entity.Role;
import com.ktpm.BankingDomainApplicationTesting.entity.User;
import com.ktpm.BankingDomainApplicationTesting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final MyService myService;



    public UserService(UserRepository userRepository, MyService myService) {
        this.userRepository = userRepository;
        this.myService = myService;
    }

    public User findUserByUserName(String userName){
        User user = userRepository.findUserByUserName(userName);
        return user;
    }
    public User addNewUser(User newUser)  {

        if(findUserByUserName(newUser.getUserName())!=null){
            return null;
        }
        newUser.setMoney("0");
        newUser.setEnabled(true);
        newUser.addRole(new Role("USER",new ArrayList<>()));
        User user = userRepository.save(newUser);

        return user;

    }

    public String tranferMoney(String phoneNumberProvide, String money ){
        User nguoiChuyen = myService.getUser();
        if(nguoiChuyen == null){
            return "Cần đăng nhập để thực hiện chức năng chuyển tiền này";
        }
        User nguoiNhan = userRepository.findUserByNumberPhone(phoneNumberProvide);
        if(nguoiNhan == null){
            System.out.println("Người nhân tiền không tồn tại");
            return "Người nhân tiền không tồn tại";
        }
        Double soTienChuyen=0D;
        Double tienNguoiNhan=0D;
        Double tienNguoiChuyen=0D;
        try {
            soTienChuyen = Double.parseDouble(money);
            tienNguoiChuyen = Double.parseDouble(nguoiChuyen.getMoney());
            tienNguoiNhan = Double.parseDouble(nguoiNhan.getMoney());
        }catch (Exception e){
            System.out.println("Lỗi chuyển tieenfv thử lại");
            return "Lỗi chuyển tiền thử lại";
        }
        if( soTienChuyen<0){
            System.out.println("Số tiền chuyển không được âm");
            return "Số tiền chuyển không được âm ";
        }
        if(tienNguoiChuyen < soTienChuyen){
            System.out.println("Số tiền trong tài khoản cần lớn hơn số tiền cần chuyển");
            return "Số tiền trong tài khoản cần lớn hơn số tiền cần chuyển , số tiền bạn là "+ tienNguoiChuyen + "Nhưng bạn chuyển : "+soTienChuyen;
        }

        // bat dau chuyen tien
        tienNguoiChuyen -= soTienChuyen;
        tienNguoiNhan += soTienChuyen;
        nguoiChuyen.setMoney(tienNguoiChuyen.toString());
        nguoiNhan.setMoney(tienNguoiNhan.toString());

        User nguoiChuyenSave = userRepository.save(nguoiChuyen);
        if(nguoiChuyenSave == null || nguoiChuyenSave.getMoney().equals(tienNguoiChuyen)){
            System.out.println("Lỗi chuyển tiền ở người gửi ");
            return "Lỗi chuyển tiền ở người gửi ";
        }

        User nguoiNhanSave = userRepository.save(nguoiNhan);
        if(nguoiNhanSave == null || nguoiNhanSave.getMoney().equals(tienNguoiNhan)){
            System.out.println("Lỗi chuyển tiền ở người nhận ");
            return "Lỗi chuyển tiền ở người nhận ";
        }
        System.out.println("Chuyen tien thanh cong !");
        System.out.println("Người nhận " + nguoiNhanSave.getUserName() + " So tien : "+ nguoiNhanSave.getMoney());
        System.out.println("Người Chuyển  " + nguoiChuyenSave.getUserName() + " So tien : "+ nguoiChuyenSave.getMoney());
        return "Chuyển tiền thành công !";
    }



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = findUserByUserName(userName);
        if (user == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }else {
            System.out.println("User  " + userName);
            System.out.println("User  " + user.getUserName());
            System.out.println("User  " + user.getEncrytedPassword());
        }
        return new CustomUserDetails(user);
    }
}
