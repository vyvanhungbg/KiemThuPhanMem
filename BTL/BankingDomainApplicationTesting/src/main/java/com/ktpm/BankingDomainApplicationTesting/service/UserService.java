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

    public String tranferMoney(String phoneNumberProvide, String money, String nganHang){
        User nguoiChuyen = myService.getUser();

        if(nguoiChuyen == null){
            return "Can dang nhap de thuc hien chuc nang nay";
        }
        if(nganHang.equals("BANK_CKICKEN") == false){
            return "Ngan hang khong ton tai so tai khoan nay";
        }
        User nguoiNhan = userRepository.findUserByNumberPhone(phoneNumberProvide);
        if(nguoiNhan == null){
            System.out.println("Nguoi Nhan Khong ton tai");
            return "So tai khoan khong ton tai";
        }
        Double soTienChuyen=0D;
        Double tienNguoiNhan=0D;
        Double tienNguoiChuyen=0D;
        try {
            soTienChuyen = Double.parseDouble(money);
            tienNguoiChuyen = Double.parseDouble(nguoiChuyen.getMoney());
            tienNguoiNhan = Double.parseDouble(nguoiNhan.getMoney());
        }catch (Exception e){
            System.out.println("Số tiền không hợp lệ");
            return "So tien khong hop le";
        }
        if( soTienChuyen<0){
            System.out.println("Số tiền không hợp lệ");
            return "So tien khong hop le";
        }
        /*if(soTienChuyen <1000){
            System.out.println("Số tiền không hợp lệ");
            return "So tien toi giao dich toi thieu la 1000đ";
        }*/
       /* if(soTienChuyen > 1000000){
            System.out.println("Số tiền không hợp lệ");
            return "Han muc giao dich toi da 100.000.000đ";
        }*/
        if(tienNguoiChuyen < soTienChuyen){
            System.out.println("Số tiền trong tài khoản cần lớn hơn số tiền cần chuyển");
            return "Giao dich that bai. So du khong du";
        }

        // bat dau chuyen tien
        tienNguoiChuyen -= soTienChuyen;
        tienNguoiNhan += soTienChuyen;
        nguoiChuyen.setMoney(tienNguoiChuyen.toString());
        nguoiNhan.setMoney(tienNguoiNhan.toString());

        User nguoiChuyenSave = userRepository.save(nguoiChuyen);
        if(nguoiChuyenSave == null || nguoiChuyenSave.getMoney().equals(tienNguoiChuyen)){
            System.out.println("Lỗi chuyển tiền ở người gửi ");
            return "Giao dich that bai";
        }

        User nguoiNhanSave = userRepository.save(nguoiNhan);
        if(nguoiNhanSave == null || nguoiNhanSave.getMoney().equals(tienNguoiNhan)){
            System.out.println("Lỗi chuyển tiền ở người nhận ");
            return "Giao dich that bai";
        }
        System.out.println("Chuyen tien thanh cong !");
        System.out.println("Người nhận " + nguoiNhanSave.getUserName() + " So tien : "+ nguoiNhanSave.getMoney());
        System.out.println("Người Chuyển  " + nguoiChuyenSave.getUserName() + " So tien : "+ nguoiChuyenSave.getMoney());
        return "Chuyen tien thanh cong !";
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
