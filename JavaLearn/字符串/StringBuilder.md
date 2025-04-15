# StringBuilder概述

StringBuilder可以看作是一个容器，创建之后里面的内容是可变的

- 作用：提高字符串的操作效率

![image-20250227125930125](https://pic.hibugs.net/NGBTEAM/image-20250227125930125.png)

在传统的字符串拼接中，上述的拼接从左往右，由于字符串是不可改变的，所以`s1+s2`会生成一个新的字符串`"aaabbb"`这个新的字符串又会和s3进行拼接，生成又一个新的字符串`aaabbbccc`，以此类推。这些字符串其实都是无用的，又非常的占内存。

上述的拼接操作若引入StringBuilder对象，则会高效且节省空间。StringBuilder容器是可变的，它可以把这些字符串都放进去，所以只会生成一个StringBuilder对象而不会生成多个无用的字符串对象

![image-20250227130252213](https://pic.hibugs.net/NGBTEAM/image-20250227130252213.png)

## 构造方法

|              方法名              |                    说明                    |
| :------------------------------: | :----------------------------------------: |
|      public StringBuilder()      | 创建一个空白可变字符串对象，不含有任何内容 |
| public StringBuilder(String str) |   根据字符串的内容，来创建可变字符串对象   |

## 成员方法

|                方法名                 |                        说明                         |
| :-----------------------------------: | :-------------------------------------------------: |
| public StringBuilder append(任意类型) |              添加数据，并返回对象本身               |
|    public StringBuilder reverse()     |                  反转容器中的内容                   |
|          public int length()          |             返回长度（字符出现的个数）              |
|       public String toString()        | 通过toString()就可以实现把StringBuilder转换为String |

## 链式编程

`StringBuilder.append()`返回的还是StringBuilder对象，还可以调用append()，所以如果有多个拼接，可以写成

`StringBuilder.append().append().append()...`链式变成的方式，而不用每一次append都先存入变量，再使用变量.append()

## 使用场景

1. 字符串的拼接
2. 字符串的反转
