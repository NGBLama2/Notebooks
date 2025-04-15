package com.ngbarsenal.test2.StuManager;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Manager{
   public static void main(String[] args){
        boolean status = false;
        Scanner sc = new Scanner(System.in);
        ArrayList<User> userList = new ArrayList<>();

       while(true){
           if(status) break;
           System.out.print("请选择您的操作：1.登录 2.注册 3.忘记密码：");
           int choice = sc.nextInt();
           if(choice==1){
               status = userLogin(userList);
           }else if(choice==2){
               userRegister(userList);
           }else if(choice==3){
               userForgetPassword(userList);
           }else if(choice==4){
               queryUser(userList);
           }else{
               System.out.println("输入非法，请重新输入！");
           }
       }

       ArrayList<Student> stuList = new ArrayList<>();
       // menu界面
       while(true){
           System.out.println("-------------NGB学生管理系统-------------");
           System.out.println("1.添加学生");
           System.out.println("2.删除学生");
           System.out.println("3.修改学生");
           System.out.println("4.查询学生");
           System.out.println("5.退出");
           System.out.print("请输入您的选择：");

           int choice = sc.nextInt();
           if(choice==5){
               System.out.println("祝您生活愉快，再见~");
               break;
           }else if(choice==1){
               addStu(stuList);
           }else if(choice==2){
               delStu(stuList);
           }else if(choice==3){
               setStu(stuList);
           }else if(choice==4){
               queryStu(stuList);
           }else{
               System.out.println("输入非法，请重新输入！");
           }
       }
   }

   public static void addStu(ArrayList<Student> stuList){
        Scanner sc = new Scanner(System.in);
        String id;

        while(true){
            System.out.print("请输入学生ID：");
            id = sc.next();
            if(isContains(stuList,id)){
                System.out.println("该ID已经存在，请重新输入");
            }else break;
        }
        System.out.print("请输入学生姓名：");
        String name = sc.next();
        System.out.print("请输入学生年龄：");
        int age = sc.nextInt();
        System.out.print("请输入学生家庭住址：");
        String address = sc.next();

        Student stu = new Student(id,name,age,address);
        stuList.add(stu);
        System.out.println("添加成功");
   }

   public static void delStu(ArrayList<Student> stuList){
       Scanner sc = new Scanner(System.in);
       System.out.print("请输入您想删除的学生的ID：");
       String id = sc.next();
       int index = getIndex(stuList,id);
       if(index==-1){
           System.out.println("该ID不存在！");
       }else{
           stuList.remove(index);
           System.out.println(id+"已删除成功！");
       }
   }

   public static void setStu(ArrayList<Student> stuList){
       Scanner sc = new Scanner(System.in);
       String id;
       int index;

       while(true){
           System.out.print("请输入您要修改的学生ID：");
           id = sc.next();
           index = getIndex(stuList,id);
           if(index!=-1) break;
           System.out.println("该ID不存在，请重新输入！");
       }
       Student stu = stuList.get(index);

       System.out.print("请输入学生姓名：");
       stu.setName(sc.next());
       System.out.print("请输入学生年龄：");
       stu.setAge(sc.nextInt());
       System.out.print("请输入学生住址：");
       stu.setAddress(sc.next());

       System.out.println("修改成功！");
   }

   public static void queryStu(ArrayList<Student> stuList){
       System.out.println("ID\t\t姓名\t年龄\t住址");
       for (int i = 0; i < stuList.size(); i++) {
           Student stu = stuList.get(i);
           System.out.println(stu.getId()+"\t"+stu.getName()+"\t"+stu.getAge()+"\t"+stu.getAddress());
       }
   }

   public static boolean isContains(ArrayList<Student> stuList,String id){
        return getIndex(stuList,id)>=0;
   }

   public static int getIndex(ArrayList<Student> stuList,String id){
       for (int i = 0; i < stuList.size(); i++) {
           String sid = stuList.get(i).getId();
           if(sid.equals(id)) return i;
       }
       return -1;
   }

   // 用户管理
    public static boolean userLogin(ArrayList<User> userList){
       Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户名：");
       String username = sc.next();
       int index = getUserIndex(userList,username);
       if(index==-1){
           System.out.println("用户未注册，请先注册！");
           return false;
       }
       String vertiCode = createVertificationCode();
        System.out.println(vertiCode);
        System.out.print("请输入验证码：");
        String vertiCodeUserInput = sc.next();
       if(!(vertiCodeUserInput.equals(vertiCode))){
           System.out.println("验证码错误！");
           return false;
       }
       User user = userList.get(index);
        for (int i = 0; i < 3; i++) {
            System.out.print("请输入密码：");
            String password = sc.next();
            if(user.getPassword().equals(password)){
                System.out.println("登录成功！");
                return true;
            }else{
                System.out.println("密码错误，请重新尝试！（您还有"+(2-i)+"次机会）");
            }
        }

       return false;
    }

    public static void userRegister(ArrayList<User> userList){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String username = sc.next();

        if(!(isUsernameLegal(userList,username))){
            System.out.println("用户名非法，注册失败！");
            return;
        }

        System.out.print("请输入密码：");
        String passwordFirst = sc.next();
        System.out.print("请再次输入密码：");
        String passwordSec = sc.next();
        if(!(passwordFirst.equals(passwordSec))){
            System.out.println("两次密码不一致，注册失败");
            return;
        }

        System.out.print("请输入身份证号：");
        String idCardNum = sc.next();
        if(!(isIdCardNumLegal(idCardNum))){
            System.out.println("身份证非法，注册失败！");
            return;
        }

        System.out.print("请输入手机号：");
        String phoneNum = sc.next();
        if(!(isPhoneNumLegal(phoneNum))){
            System.out.println("手机号非法，注册失败！");
            return;
        }

        User user = new User(username,passwordFirst,idCardNum,phoneNum);
        userList.add(user);
        System.out.println("注册成功！");
   }

    public static void userForgetPassword(ArrayList<User> userList){
       Scanner sc = new Scanner(System.in);

       System.out.print("请输入用户名：");
       String username = sc.next();
       int index = getUserIndex(userList,username);
       if(index==-1){
           System.out.println("未注册！");
           return;
       }
        System.out.print("请输入身份证号码：");
       String idCardNum = sc.next();
        System.out.print("请输入手机号码：");
        String phoneNum = sc.next();
        String password;

        User user = userList.get(index);
        if(user.getidCardNum().equals(idCardNum)){
            if(user.getphoneNum().equals(phoneNum)){
                System.out.print("请输入新密码：");
                password = sc.next();
                user.setPassword(password);
                System.out.println("密码修改成功！");

                return;
            }
        }

        System.out.println("账号信息不匹配，修改失败！");
    }

    public static boolean isUsernameLegal(ArrayList<User> userList,String username){
        int usernameLength = username.length();

        if(usernameLength<3 || usernameLength>15){
            System.out.println("用户名长度必须在3-15之内！");
            return false;
        }
        int index = getUserIndex(userList,username);
        if(index!=-1){
            System.out.println("用户名已存在！");
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            char oneWord = username.charAt(i);
            if(oneWord>=97 && oneWord<=122){
                return true;
            }
            if(oneWord>=65 && oneWord<=90){
                return true;
            }
        }

        return false;
    }

    public static boolean isIdCardNumLegal(String idCardNum){
       if(idCardNum.length()!=18){
           return false;
       }
       if(idCardNum.charAt(0)=='0'){
           return false;
       }
        for (int i = 0; i < 17; i++) {
            char oneNum = idCardNum.charAt(i);
            if(oneNum-48<0 || oneNum-48>9){
                return false;
            }
        }
        char lastWord = idCardNum.charAt(17);
        if(lastWord-48<0 && lastWord-48>9){
            if(lastWord!=88 || lastWord!=120){
                return false;
            }
        }

        return true;
    }

    public static boolean isPhoneNumLegal(String phoneNum){
       if(phoneNum.length()!=11){
           return false;
       }
       if(phoneNum.charAt(0)=='0'){
           return false;
       }
        for (int i = 0; i < phoneNum.length(); i++) {
            char oneNum = phoneNum.charAt(i);
            if(oneNum<'0' || oneNum>'9'){
                return false;
            }
        }

        return true;
    }

    public static void queryUser(ArrayList<User> userList){
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            System.out.println(user.getUsername()+","+user.getPassword()+","+user.getidCardNum()+","+user.getphoneNum());
        }
    }

    public static int getUserIndex(ArrayList<User> userList,String username){
       for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if(user.getUsername().equals(username)){
                return i;
            }
        }
       return -1;
    }

    public static String createVertificationCode(){
       StringBuilder sb = new StringBuilder();
       Random rd = new Random();

        for (int i = 0; i < 4; i++) {
            int upperOrLower = rd.nextInt(2);
            char letter;
            if(upperOrLower==0){ // 小写
                letter = (char) (rd.nextInt(26)+97);
                sb.append(letter);
            }else{
                letter = (char) (rd.nextInt(26)+65);
                sb.append(letter);
            }
        }
        int numIndex = rd.nextInt(5);
        int num = rd.nextInt(10);
        sb.insert(numIndex,num);
        String vertificationCode = sb.toString();

       return vertificationCode;
    }
}